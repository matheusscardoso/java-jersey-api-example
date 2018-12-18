package br.rest.java.domain.entities;

import java.util.UUID;

public class Note {

	private String id;
	private String title;
	private String description;

	// *** Methods ***
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	private String GenerateId() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 7);
	}	

	public Note(String title, String description) {
		this.id = GenerateId();
		this.title = title;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "id: " + this.id + "; title: " + this.title + "; description: " + this.description;
	}

}
