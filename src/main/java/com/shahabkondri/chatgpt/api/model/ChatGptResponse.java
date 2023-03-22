package com.shahabkondri.chatgpt.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Represents a response from the ChatGPT API after generating completions for chat
 * messages.
 * <p>
 * The response includes the following properties:
 * <ul>
 * <li>{@code id} - A unique identifier for the chat completion.</li>
 * <li>{@code object} - A string representing the type of the object returned, e.g.,
 * "chat.completion".</li>
 * <li>{@code created} - The timestamp when the chat completion was created.</li>
 * <li>{@code model} - The ID of the model used for generating chat completions.</li>
 * <li>{@code choices} - A list of generated chat completion choices, each containing a
 * {@link Choice} object.</li>
 * </ul>
 *
 * @author Shahab Kondri
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ChatGptResponse(String id, String object, long created, String model, List<Choice> choices) {

	/**
	 * Represents a generated chat completion choice.
	 * <ul>
	 * <li>{@code delta} - The generated chat message {@link Delta}, containing the
	 * content of the message.</li>
	 * <li>{@code index} - The index of the choice in the list of generated choices.</li>
	 * <li>{@code finishReason} - The reason for stopping the message generation, e.g.,
	 * "stop".</li>
	 * </ul>
	 */
	public record Choice(Delta delta, int index, String finishReason) {
	}

	/**
	 * Represents the content of a generated chat message.
	 * <ul>
	 * <li>{@code content} - The content of the generated chat message.</li>
	 * </ul>
	 */
	public record Delta(String content) {
	}
}
