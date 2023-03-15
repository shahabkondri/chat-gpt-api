package com.shahabkondri.chatgpt.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Shahab Kondri
 */
@Configuration
public class WebClientChatGptCustomizer implements WebClientCustomizer {

	private final OpenApiProperties properties;

	@Autowired
	public WebClientChatGptCustomizer(OpenApiProperties properties) {
		this.properties = properties;
	}

	@Override
	public void customize(WebClient.Builder webClientBuilder) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl());

		DefaultUriBuilderFactory builderFactory = new DefaultUriBuilderFactory(uriBuilder);

		webClientBuilder.uriBuilderFactory(builderFactory)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getApiKey());
	}

}