package dev.conca.mavenconversion;

import java.util.ArrayList;
import java.util.List;

public class Artifact {

	private String id;

	private String groupId;

	private String artifactId;

	private String version;

	private Long timestamp;

	private List<String> classifiers = new ArrayList<>();

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

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getClassifiers() {
		return classifiers;
	}

	public void setClassifiers(List<String> classifiers) {
		this.classifiers = classifiers;
	}

	public String getMavenSnippet() {
		return 	"<dependency>" +
					"<groupId>" + groupId + "</groupId>" +
					"<artifactId>" + artifactId + "</artifactId>" +
					"<version>" + version + "</version>" +
				"</dependency>";
	}

    public String getGradleSnippet() {
        return "compile '" + groupId + ":" + artifactId + ":" + version + "'";
    }
}
