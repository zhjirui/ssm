package com.demo.mybatisgenerator;

import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
* 扩展mybatis 自动生成代码插件
* 扩展方法有：getAll、deleteByIds、get
*/
public class ExtendSqlPlugin extends PluginAdapter{

    public boolean validate(List<String> warnings) {
        return true;  
    }

@Override  
public boolean sqlMapDocumentGenerated(Document document,   IntrospectedTable introspectedTable) {  
    XmlElement parentElement = document.getRootElement();  
    String columnsString="";
    String columnsVlueString="";
    //查询公用条件
    XmlElement conditionsSql = new XmlElement("sql");  
    conditionsSql.addAttribute(new Attribute("id",   "conditions"));  
    XmlElement trim = new XmlElement("trim");  
    trim.addAttribute(new Attribute("prefix",   "where"));  
    trim.addAttribute(new Attribute("prefixOverrides",   "and|or"));  
    trim.addElement(new TextElement("1=1"));
    List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
    for (IntrospectedColumn column:columns) {
    	 XmlElement ifXml = new XmlElement("if");  
    	 ifXml.addAttribute(new Attribute("test", column.getJavaProperty()+"!=null"));  
    	 ifXml.addElement(new TextElement("and  t."+column.getActualColumnName()+" = #{"+column.getJavaProperty()+"}"));
    	 trim.addElement(ifXml);
	}
    conditionsSql.addElement(trim);
    
    //修改公用条件
    XmlElement setSql = new XmlElement("sql");  
    setSql.addAttribute(new Attribute("id",   "sets"));  
    XmlElement setTrim = new XmlElement("trim");  
    setTrim.addAttribute(new Attribute("prefix",   "SET"));  
    setTrim.addAttribute(new Attribute("suffixOverrides",   ","));  
    List<IntrospectedColumn> nonPrimaryKeyColumns = introspectedTable.getNonPrimaryKeyColumns();
    for (IntrospectedColumn column:nonPrimaryKeyColumns) {
    	 XmlElement ifXml = new XmlElement("if");  
    	 ifXml.addAttribute(new Attribute("test", column.getJavaProperty()+"!=null"));  
    	 ifXml.addElement(new TextElement("t."+column.getActualColumnName()+" = #{"+column.getJavaProperty()+"},"));
    	 setTrim.addElement(ifXml);
    	 columnsString+=(column.getActualColumnName()+",");
    	 columnsVlueString+="#{item."+column.getJavaProperty()+",jdbcType="+column.getJdbcTypeName()+"},";
	}
    setSql.addElement(setTrim);

    //getAll
    XmlElement getAll = new XmlElement("select");  
    getAll.addAttribute(new Attribute("id",   "getAll"));  
    getAll.addAttribute(new Attribute("resultMap",  "BaseResultMap"));
    getAll.addElement(new TextElement("select * from "+introspectedTable.getFullyQualifiedTableNameAtRuntime()));
   
    //deleteByIds
    XmlElement deleteByIds = new XmlElement("delete");  
    deleteByIds.addAttribute(new Attribute("id",   "deleteByIds"));  
    String id=introspectedTable.getPrimaryKeyColumns().size()>0?introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName():"id";
    deleteByIds.addElement(new TextElement(" delete from "+introspectedTable.getFullyQualifiedTableNameAtRuntime()+"  t where t."+id+" in (#{ids})"));
    
    //get
    XmlElement get = new XmlElement("select");  
    get.addAttribute(new Attribute("id",   "get"));  
    get.addAttribute(new Attribute("parameterType", "java.util.Map"));
    get.addAttribute(new Attribute("resultMap",  "BaseResultMap"));
    get.addElement(new TextElement("select *  from "+introspectedTable.getFullyQualifiedTableNameAtRuntime()+" t"));
    get.addElement(new TextElement("<include refid=\"conditions\"/>"));
    
    //getCount
    XmlElement getCount = new XmlElement("select");  
    getCount.addAttribute(new Attribute("id",   "getCount"));  
    getCount.addAttribute(new Attribute("parameterType", "java.util.Map"));
    getCount.addAttribute(new Attribute("resultType",  "java.lang.Long"));
    getCount.addElement(new TextElement(" select count(*) from "+introspectedTable.getFullyQualifiedTableNameAtRuntime() +" t"));
    getCount.addElement(new TextElement("<include refid=\"conditions\"/>"));

    //saveBatch
    XmlElement saveBatch = new XmlElement("insert");  
    saveBatch.addAttribute(new Attribute("id",   "saveBatch"));  
    saveBatch.addAttribute(new Attribute("parameterType", "java.util.List"));
    saveBatch.addElement(new TextElement("insert into "+introspectedTable.getFullyQualifiedTableNameAtRuntime() +"("+columnsString+")"));
    XmlElement saveBatchforeach = new XmlElement("foreach");
    saveBatchforeach.addAttribute(new Attribute("collection", "list"));
    saveBatchforeach.addAttribute(new Attribute("item", "item"));
    saveBatchforeach.addAttribute(new Attribute("index", "index"));
    saveBatchforeach.addAttribute(new Attribute("separator", "UNION ALL"));
    saveBatchforeach.addElement(new TextElement("SELECT"));
    saveBatchforeach.addElement(new TextElement(columnsVlueString));
    saveBatchforeach.addElement(new TextElement("from dual"));
    saveBatch.addElement(saveBatchforeach);
    
    parentElement.addElement(conditionsSql);
    parentElement.addElement(setSql);  
    parentElement.addElement(getAll);  
    parentElement.addElement(deleteByIds);  
    parentElement.addElement(get);  
    parentElement.addElement(getCount);  
    parentElement.addElement(saveBatch);  
    return super.sqlMapDocumentGenerated(document, introspectedTable);
} 
}
