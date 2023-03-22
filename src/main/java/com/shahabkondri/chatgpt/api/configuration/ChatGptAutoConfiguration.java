package com.shahabkondri.chatgpt.api.configuration;

import com.shahabkondri.chatgpt.api.client.ChatGptClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link ChatGptClient}.
 * <p>
 * This class automatically configures the ChatGPT API client and its dependencies. It
 * sets up the necessary beans, imports the required classes, and enables the relevant
 * configuration properties.
 *
 * @author Shahab Kondri
 */
@AutoConfiguration
@EnableConfigurationProperties({ OpenApiProperties.class })
@Import({ WebClientChatGptCustomizer.class })
public class ChatGptAutoConfiguration {

	/**
	 * Configures and provides a {@link ChatGptClient} bean using the provided
	 * {@link WebClient.Builder}.
	 * @param builder the {@link WebClient.Builder} used to configure the WebClient
	 * @return the configured {@link ChatGptClient} instance
	 */
	@Bean
	@ConditionalOnMissingBean
	public ChatGptClient chatGptClient(WebClient.Builder builder) {
		WebClient webClient = builder.build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
				.build();
		return factory.createClient(ChatGptClient.class);
	}

}
