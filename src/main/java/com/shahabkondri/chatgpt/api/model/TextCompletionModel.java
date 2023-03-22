package com.shahabkondri.chatgpt.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the text completion models available in the OpenAI API.
 *
 * @author Shahab Kondri
 */
public enum TextCompletionModel {

	/**
	 * Most capable GPT-3.5 model, optimized for chat. Max tokens: 4,096.
	 */
	GPT_3_5_TURBO("gpt-3.5-turbo", 4096),

	/**
	 * More capable than any GPT-3.5 model, able to perform more complex tasks, and
	 * optimized for chat. Will be updated with the latest model iteration. Max tokens:
	 * 8,192.
	 */
	GPT_4("gpt-4", 8192),

	/**
	 * Same capabilities as the base GPT-4 model but with 4x the context length. Will be
	 * updated with the latest model iteration. Max tokens: 32,768.
	 */
	GPT_4_32_K("gpt-4-32k", 32768);

	@JsonValue
	private final String model;

	private final int maxTokens;

	TextCompletionModel(String model, int maxTokens) {
		this.model = model;
		this.maxTokens = maxTokens;
	}

	/**
	 * Retrieves the model identifier for the text completion model.
	 * @return A String representing the identifier of the text completion model, such as
	 * "gpt-3.5-turbo", "gpt-4", or "gpt-4-32k".
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Retrieves the maximum number of tokens supported by the text completion model.
	 * @return An integer representing the maximum number of tokens allowed for the text
	 * completion model, such as 4096, 8192, or 32768.
	 */

	public int getMaxTokens() {
		return maxTokens;
	}

}
