package dev.conca.mavenconversion.mavencentral;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import dev.conca.mavenconversion.model.Artifact;

/**
 * Searches for Maven artifacts in central repository
 * 
 * @author Leandro Conca
 *
 */
public class MavenCentralSearch {

	private RestTemplate restTemplate;

	private static final String SHA1_SEARCH_URL_TEMPLATE = "https://search.maven.org/solrsearch/select?q=1:\"{sha1sum}\"&rows=20&wt=json";

	public MavenCentralSearch() {
		restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());

		restTemplate.setMessageConverters(messageConverters);
	}

	public List<Artifact> findArtifactsBySHA1(String sha1sum) {
		ArtifactSearchResult searchresult = restTemplate.getForObject(
				SHA1_SEARCH_URL_TEMPLATE, ArtifactSearchResult.class, sha1sum);
		return searchresult.getResponse().getDocs();
	}

}
