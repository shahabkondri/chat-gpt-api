package com.shahabkondri.chatgpt.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Shahab Kondri
 */
@ConfigurationProperties(prefix = "openai")
public class OpenApiProperties {

	private String baseUrl = "https://api.openai.com";

	private String apiKey;

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

}
