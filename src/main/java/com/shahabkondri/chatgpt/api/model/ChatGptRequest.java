package com.shahabkondri.chatgpt.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a request to the ChatGPT API for generating completions in a chat format.
 * <p>
 * The request includes the following properties:
 * <ul>
 * <li>{@code model} - The ID of the model to use for generating chat completions.</li>
 * <li>{@code messages} - The list of messages in the chat format to generate completions.
 * {@link ChatGptRequest.Message} for.</li>
 * <li>{@code stream} - If true, partial message deltas will be sent as they become
 * available (defaults to false).</li>
 * <li>{@code temperature} - The sampling temperature to use, between 0 and 2 (defaults to
 * 1).</li>
 * <li>{@code topP} - An alternative to sampling with temperature, called nucleus sampling
 * (defaults to 1).</li>
 * <li>{@code numCompletions} - The number of chat completion choices to generate for each
 * input message (defaults to 1).</li>
 * <li>{@code presencePenalty} - A penalty for new tokens based on their presence in the
 * text so far (defaults to 0).</li>
 * <li>{@code frequencyPenalty} - A penalty for new tokens based on their frequency in the
 * text so far (defaults to 0).</li>
 * <li>{@code user} - A unique identifier representing the end-user (optional).</li>
 * </ul>
 *
 * @author Shahab Kondri
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// @formatter:off
public record ChatGptRequest(@JsonProperty("model") TextCompletionModel model,
							 @JsonProperty("messages") List<Message> messages,
							 @JsonProperty("stream") boolean stream,
							 @JsonProperty("temperature") Double temperature,
							 @JsonProperty("top_p") Double topP,
							 @JsonProperty("n") Integer numCompletions,
							 @JsonProperty("presence_penalty") Double presencePenalty,
							 @JsonProperty("frequency_penalty") Double frequencyPenalty,
							 @JsonProperty("user") String user) { // @formatter:on

	/**
	 * Creates a new {@link ChatGptRequest} with the specified model and list of messages.
	 * Sets default values for other parameters.
	 * @param model the ID of the model to use for generating chat completions
	 * @param messages the list of messages in the chat format to generate completions for
	 */
	public ChatGptRequest(TextCompletionModel model, List<Message> messages) {
		this(model, messages, true, 1.0, 1.0, 1, 0.0, 0.0, null);
	}

	/**
	 * Creates a new {@link ChatGptRequest} with the specified model and a single message.
	 * Sets default values for other parameters.
	 * @param model the ID of the model to use for generating chat completions
	 * @param message the message in the chat format to generate completions for
	 */
	public ChatGptRequest(TextCompletionModel model, Message message) {
		this(model, List.of(message));
	}

	@Override
	public String toString() {
		return "ChatGptRequest[" + "model=" + model + ", " + "messages=" + messages + ", " + "stream=" + stream + ']';
	}

	/**
	 * Represents a message in the chat format used by the ChatGPT API.
	 * @param role the role of the message sender, e.g., {@link MessageRole#USER},
	 * {@link MessageRole#SYSTEM} or {@link MessageRole#ASSISTANT}
	 * @param content the content of the message
	 * @author Shahab Kondri
	 */
	public record Message(MessageRole role, String content) {

		/**
		 * Creates a new Message with the specified content and a default role of
		 * {@link MessageRole#USER}.
		 * @param content the content of the message
		 */
		public Message(String content) {
			this(MessageRole.USER, content);
		}

		@Override
		public String toString() {
			return "Message[" + "role=" + role + ", " + "content=" + content + ']';
		}

	}

}
