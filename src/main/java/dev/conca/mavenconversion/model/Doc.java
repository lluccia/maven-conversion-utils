package dev.conca.mavenconversion.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Doc {

	private String id;

	@JsonProperty("g")
	private String groupId;

	@JsonProperty("a")
	private String artifactId;

	@JsonProperty("v")
	private String version;

	@JsonProperty("p")
	private String packaging;

	private Date timestamp;

	private List<String> tags;

	@JsonProperty("ec")
	private List<String> ec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMavenSnippet() {
		return "<dependency><groupId>" + 
				groupId + "</groupId><artifactId>" +
				artifactId + "</artifactId><version>" +
				version + "</version></dependency>";
	}
}
