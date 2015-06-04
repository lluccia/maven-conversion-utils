package dev.conca.mavenconversion.model;

import java.util.LinkedHashMap;
import java.util.List;

public class ArtifactSearchResult {

	private LinkedHashMap<String, Object> responseHeader;
	private Response response;

	public class Response {
		Integer numFound;
		Integer start;
		List<Doc> docs;
		
		public Integer getNumFound() {
			return numFound;
		}
		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}
		public Integer getStart() {
			return start;
		}
		public void setStart(Integer start) {
			this.start = start;
		}
		public List<Doc> getDocs() {
			return docs;
		}
		public void setDocs(List<Doc> docs) {
			this.docs = docs;
		}
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public LinkedHashMap<String, Object> getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(LinkedHashMap<String, Object> reponseHeader) {
		this.responseHeader = reponseHeader;
	}

}
