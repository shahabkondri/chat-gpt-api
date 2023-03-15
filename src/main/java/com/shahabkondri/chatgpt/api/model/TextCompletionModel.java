package com.shahabkondri.chatgpt.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Shahab Kondri
 */
public enum TextCompletionModel {

	/**
	 * Most capable GPT-3.5 model and optimized for chat. MAX TOKENS 4,096
	 */
	GPT_3_5_TURBO("gpt-3.5-turbo", 4096),

	/**
	 * More capable than any GPT-3.5 model, able to do more complex tasks, and optimized
	 * for chat. Will be updated with our latest model iteration. MAX TOKENS 8,192
	 */
	GPT_4("gpt-4", 8192),

	/**
	 * Same capabilities as the base gpt-4 mode but with 4x the context length. Will be
	 * updated with our latest model iteration. MAX TOKENS 32,768
	 */
	GPT_4_32_K("gpt-4-32k", 32768);

	@JsonValue
	private final String model;

	private final int maxToken;

	TextCompletionModel(String model, int maxTokens) {
		this.model = model;
		this.maxToken = maxTokens;
	}

	public String getModel() {
		return model;
	}

	public int getMaxToken() {
		return maxToken;
	}

}
