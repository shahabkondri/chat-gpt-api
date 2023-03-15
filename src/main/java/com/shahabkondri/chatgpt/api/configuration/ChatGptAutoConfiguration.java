package com.shahabkondri.chatgpt.api.configuration;

import com.shahabkondri.chatgpt.api.client.ChatGptClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author Shahab Kondri
 */
@AutoConfiguration
@EnableConfigurationProperties({ OpenApiProperties.class })
@Import({ WebClientChatGptCustomizer.class })
public class ChatGptAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public ChatGptClient chatGptClient(WebClient.Builder builder) {
		WebClient webClient = builder.build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
				.build();
		return factory.createClient(ChatGptClient.class);
	}

}
