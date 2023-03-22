package com.shahabkondri.chatgpt.api.client;

import com.shahabkondri.chatgpt.api.model.ChatGptRequest;
import com.shahabkondri.chatgpt.api.model.ChatGptResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

/**
 * ChatGPT API client interface.
 * <p>
 * This interface defines the API methods for interacting with the ChatGPT service.
 *
 * @author Shahab Kondri
 */
public interface ChatGptClient {

	/**
	 * Retrieves chat completions from the ChatGPT API by sending a POST request with the
	 * provided {@link ChatGptRequest}.
	 * <p>
	 * The method sends a POST request to the "/v1/chat/completions" endpoint and expects
	 * a stream of {@link ChatGptResponse} objects in the response body. The response is
	 * represented as a {@link Flux<ChatGptResponse>} to handle the stream of ChatGPT
	 * responses asynchronously.
	 * @param request the {@link ChatGptRequest} containing the required parameters for
	 * generating chat completions
	 * @return a {@link Flux} of {@link ChatGptResponse} objects representing the
	 * generated chat completions
	 */
	@PostExchange(value = "/v1/chat/completions", accept = { MediaType.APPLICATION_JSON_VALUE })
	Flux<ChatGptResponse> completions(@RequestBody ChatGptRequest request);

}
