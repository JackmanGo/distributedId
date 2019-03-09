package com.wang.idworker.spring.schema.parser;

import com.wang.idworker.generator.CompressUUIDGenerator;
import com.wang.idworker.generator.SnowflakeGenerator;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * generator:xxx 标签解析
 * @author wangxi
 * @since 1.2.0
 */
public class GeneratorBeanDefinitionParser extends BaseBeanDefinitionParser {
	
	private String generatorType;
	
	/**
	 * generator:xxx 标签解析
	 * @param generatorType 解析类型
	 */
	public GeneratorBeanDefinitionParser(String generatorType) {
		this.generatorType = generatorType;
	}
	
	@Override
    protected AbstractBeanDefinition parseInternal(final Element element, final ParserContext parserContext) {
		Class<?> generatorClass = null;
		if ("snowflake".equals(generatorType)) {
			generatorClass = SnowflakeGenerator.class;
		} else if ("compress-uuid".equals(generatorType)) {
			generatorClass = CompressUUIDGenerator.class;
		} else {
			throw new IllegalArgumentException("unknown registryType");
		}
		BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(generatorClass);
        // snowflake 生成策略
        if (generatorClass.isAssignableFrom(SnowflakeGenerator.class)) {
        	result.addConstructorArgValue(GeneratorRegisteryBuilder.buildWorkerNodeRegisterBeanDefinition(element, parserContext));
        	result.setInitMethodName("init");
        }
        return result.getBeanDefinition();
    }

}
