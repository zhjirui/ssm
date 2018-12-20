package com.demo.mybatisgenerator;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * maper 继承BaseMapper
 */
public class BaseMaperPlugin extends PluginAdapter {

	private List<FullyQualifiedJavaType> baseMapers = new ArrayList();
	private Integer num = 0;

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		baseMapers.add(new FullyQualifiedJavaType(
				" com.xxbb2.admin.dao.BaseMapper"));
		baseMapers.get(num).addTypeArgument(
				new FullyQualifiedJavaType(introspectedTable
						.getBaseRecordType()));

		interfaze.addImportedType(baseMapers.get(num));
		interfaze.addSuperInterface(baseMapers.get(num));

		num++;
		return super.clientGenerated(interfaze, topLevelClass,
				introspectedTable);
	}
}
