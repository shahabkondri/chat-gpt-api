package com.shahabkondri.chatgpt.api.model;

import java.util.List;

/**
 * @author Shahab Kondri
 */
public record ChatGptResponse(String id, String object, long created, String model, List<Choice> choices) {

	public record Choice(Delta delta, int index, String finishReason) {
	}

	public record Delta(String content) {
	}
}
