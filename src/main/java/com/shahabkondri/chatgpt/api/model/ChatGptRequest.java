package com.shahabkondri.chatgpt.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * @author Shahab Kondri
 */
public final class ChatGptRequest {

	private final TextCompletionModel model;

	private final List<Message> messages;

	private final boolean stream;

	private ChatGptRequest(@JsonProperty("model") TextCompletionModel model,
			@JsonProperty("messages") List<Message> messages, @JsonProperty("stream") boolean stream) {
		this.model = model;
		this.messages = messages;
		this.stream = stream;
	}

	public ChatGptRequest(TextCompletionModel model, List<Message> messages) {
		this(model, messages, true);
	}

	public ChatGptRequest(TextCompletionModel model, Message message) {
		this(model, List.of(message));
	}

	public TextCompletionModel getModel() {
		return model;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public boolean isStream() {
		return stream;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (ChatGptRequest) obj;
		return Objects.equals(this.model, that.model) && Objects.equals(this.messages, that.messages)
				&& this.stream == that.stream;
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, messages, stream);
	}

	@Override
	public String toString() {
		return "ChatGptRequest[" + "model=" + model + ", " + "messages=" + messages + ", " + "stream=" + stream + ']';
	}

	public static final class Message {

		private final MessageRole role;

		private final String content;

		public Message(MessageRole role, String content) {
			this.role = role;
			this.content = content;
		}

		public Message(String content) {
			this(MessageRole.USER, content);
		}

		public MessageRole getRole() {
			return role;
		}

		public String getContent() {
			return content;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (obj == null || obj.getClass() != this.getClass()) {
				return false;
			}
			var that = (Message) obj;
			return Objects.equals(this.role, that.role) && Objects.equals(this.content, that.content);
		}

		@Override
		public int hashCode() {
			return Objects.hash(role, content);
		}

		@Override
		public String toString() {
			return "Message[" + "role=" + role + ", " + "content=" + content + ']';
		}

	}

}
