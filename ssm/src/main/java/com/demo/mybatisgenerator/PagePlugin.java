package com.demo.mybatisgenerator;

import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 分页扩展
 * 
 * @author aliang
 * @version 1.0
 * @created 2015年8月10日 下午5:41:24
 */
public class PagePlugin extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {

		XmlElement parentElement = document.getRootElement();

		XmlElement page = new XmlElement("select");
		page.addAttribute(new Attribute("id", "page"));
		page.addAttribute(new Attribute("parameterType", "java.util.Map"));
		page.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		page.addElement(new TextElement("select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()+ " t"));
		page.addElement(new TextElement("<include refid=\"conditions\"/>"));

		XmlElement ordersIf = new XmlElement("if");
		ordersIf.addAttribute(new Attribute("test", "orders!=null"));
		ordersIf.addElement(new TextElement("order by"));

		XmlElement ordersforeach = new XmlElement("foreach");
		ordersforeach.addAttribute(new Attribute("item", "item"));
		ordersforeach.addAttribute(new Attribute("index", "index"));
		ordersforeach.addAttribute(new Attribute("collection", "orders"));
		ordersforeach.addAttribute(new Attribute("separator", ","));
		ordersforeach.addElement(new TextElement("t.${item}"));

		XmlElement orderType = new XmlElement("if");
		orderType.addAttribute(new Attribute("test", "orderType!=null"));
		orderType.addElement(new TextElement(" ${orderType}"));

		ordersIf.addElement(ordersforeach);
		ordersIf.addElement(orderType);
		page.addElement(ordersIf);

		page.addElement(new TextElement("LIMIT #{startPage},#{endPage}"));

		parentElement.addElement(page);

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
}
