package com.wang.idworker.config;

/**
 * Application 配置
 * 
 * @author wangxi
 * @since 1.0.0
 */
public class ApplicationConfiguration {

	/**
	 * 分组名字，默认default
	 */
	private String group = "default";
	/**
	 * 生成策略，默认snowflake
	 * 
	 * @since 1.2.0
	 */
	private String strategy = "snowflake";

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
}
