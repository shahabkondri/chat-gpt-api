package com.shahabkondri.chatgpt.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Shahab Kondri
 */
public enum MessageRole {

	SYSTEM("system"), USER("user"), ASSISTANT("assistant");

	@JsonValue
	private final String role;

	MessageRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
