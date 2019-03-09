package com.wang.idworker.spring.schema.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.wang.idworker.spring.schema.parser.RegistryBeanDefinitionParser;

/**
 * IdworkerNamespaceHandler
 * @author wangxi
 * @since 1.0.0
 */
public class IdworkerNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("registry", new RegistryBeanDefinitionParser("registry"));
		registerBeanDefinitionParser("generator", new RegistryBeanDefinitionParser("generator"));
	}

}
