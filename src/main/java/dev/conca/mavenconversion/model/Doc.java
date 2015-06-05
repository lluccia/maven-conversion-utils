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

	
	
	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getEc() {
		return ec;
	}

	public void setEc(List<String> ec) {
		this.ec = ec;
	}

	public String getMavenSnippet() {
		return "<dependency><groupId>" + 
				groupId + "</groupId><artifactId>" +
				artifactId + "</artifactId><version>" +
				version + "</version></dependency>";
	}
}
