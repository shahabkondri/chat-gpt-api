package com.shahabkondri.chatgpt.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * {@link ConfigurationProperties properties} for OpenAI API.
 * <p>
 * This class is used to map configuration properties with the prefix "openai" to their
 * respective fields.
 *
 * @author Shahab Kondri
 */
@ConfigurationProperties(prefix = "openai")
public record OpenApiProperties(@DefaultValue("https://api.openai.com") String baseUrl, String apiKey) {
}