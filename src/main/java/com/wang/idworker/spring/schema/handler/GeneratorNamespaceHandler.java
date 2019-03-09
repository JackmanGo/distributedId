package com.wang.idworker.spring.schema.handler;

import com.wang.idworker.spring.schema.parser.GeneratorBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * IdworkerNamespaceHandler
 * @author wangxi
 * @since 1.0.0
 */
public class GeneratorNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("snowflake", new GeneratorBeanDefinitionParser("snowflake"));
		registerBeanDefinitionParser("compress-uuid", new GeneratorBeanDefinitionParser("compress-uuid"));
	}

}
