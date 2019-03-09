package com.wang.idworker.spring.schema.parser;

import com.wang.idworker.generator.SnowflakeGenerator;
import com.wang.idworker.registry.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.wang.idworker.config.ZookeeperConfiguration;
import com.wang.idworker.spring.common.ZookeeperBeanDefinitionTag;

/**
 * idworker:registry 标签解析
 * @author wangxi
 * @since 1.0.0
 */
public class RegistryBeanDefinitionParser extends BaseBeanDefinitionParser {
	
	private String registryType;
	
	public RegistryBeanDefinitionParser(String registryType) {
		this.registryType = registryType;
	}

	@Override
    protected AbstractBeanDefinition parseInternal(final Element element, final ParserContext parserContext) {
		if ("registry".equals(registryType)) {
			BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(ZookeeperRegistryCenter.class);
			result.addConstructorArgValue(buildZookeeperConfigurationBeanDefinition(element, parserContext));
			// Spring 启动初始化ZK连接
			result.setInitMethodName("init");
			return result.getBeanDefinition();
		} else if ("generator".equals(registryType)) {
			Class<?> generatorClass = GeneratorRegisteryBuilder.getGeneratorClass(element);
	        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(generatorClass);
	        // snowflake 生成策略
	        if (generatorClass.isAssignableFrom(SnowflakeGenerator.class)) {
	        	result.addConstructorArgValue(GeneratorRegisteryBuilder.buildWorkerNodeRegisterBeanDefinition(element, parserContext));
	        	result.setInitMethodName("init");
	        }
	        return result.getBeanDefinition();
		}
		throw new IllegalArgumentException("unknown registryType");
    }
    
    /**
     * zookeeper 链接喷纸解析
     * @param element element
     * @param parserContext parserContext
     * @return
     */
    private AbstractBeanDefinition buildZookeeperConfigurationBeanDefinition(final Element element, final ParserContext parserContext) {
        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(ZookeeperConfiguration.class);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.SERVER_LISTS, "serverLists", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.NAMESPACE, "namespace", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.BASE_SLEEP_TIME_MS, "baseSleepTimeMilliseconds", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.MAX_SLEEP_TIME_MS, "maxSleepTimeMilliseconds", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.MAX_RETRIES, "maxRetries", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.SESSION_TIMEOUT_MS, "sessionTimeoutMilliseconds", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.CONNECTION_TIMEOUT_MS, "connectionTimeoutMilliseconds", element, result);
        addPropertyValueIfNotEmpty(ZookeeperBeanDefinitionTag.DIGEST, "digest", element, result);
        return result.getBeanDefinition();
    }
    
	

}
