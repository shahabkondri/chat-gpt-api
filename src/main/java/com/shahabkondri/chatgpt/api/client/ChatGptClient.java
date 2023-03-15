package com.shahabkondri.chatgpt.api.client;

import com.shahabkondri.chatgpt.api.model.ChatGptRequest;
import com.shahabkondri.chatgpt.api.model.ChatGptResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

/**
 * @author Shahab Kondri
 */
public interface ChatGptClient {

	@PostExchange("/v1/chat/completions")
	Flux<ChatGptResponse> completions(@RequestBody ChatGptRequest request);

}
