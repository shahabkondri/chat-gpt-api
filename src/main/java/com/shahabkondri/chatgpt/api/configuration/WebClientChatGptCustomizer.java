package com.shahabkondri.chatgpt.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * {@link WebClientCustomizer} that customizes the {@link WebClient} for interacting with
 * the ChatGPT API.
 * <p>
 * This class customizes the {@link WebClient.Builder} instance by setting the base URL,
 * content type, and authorization header. It uses {@link OpenApiProperties} to retrieve
 * the necessary configuration properties.
 *
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

		webClientBuilder.uriBuilderFactory(builderFactory).filter(new ChatGptResponseFilterFunction())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getApiKey());
	}

	/**
	 * An implementation of the {@link ExchangeFilterFunction} that filters the body of
	 * the {@link ClientResponse} to handle special cases in the response stream, such as
	 * removing or replacing unwanted content.
	 *
	 * <p>
	 * When the response content contains "[DONE]", an empty {@link DataBuffer} is
	 * returned instead. This prevents deserialization issues when parsing the response
	 * stream.
	 */
	private final static class ChatGptResponseFilterFunction implements ExchangeFilterFunction {

		@Override
		public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
			return next.exchange(request).flatMap(response -> {
				if (response.statusCode().is2xxSuccessful()) {
					Flux<DataBuffer> filteredBody = response.bodyToFlux(DataBuffer.class).filter(buffer -> {
						String content = buffer.toString(StandardCharsets.UTF_8);
						return !content.contains("data: [DONE]");
					});
					// Create a new ClientResponse instead of mutating the existing one
					ClientResponse.Builder builder = ClientResponse.create(response.statusCode())
							.headers(headers -> headers.addAll(response.headers().asHttpHeaders()))
							.cookies(cookies -> cookies.putAll(response.cookies()));
					return Mono.just(builder.body(filteredBody).build());
				}
				else {
					return Mono.just(response);
				}
			});
		}

	}

}