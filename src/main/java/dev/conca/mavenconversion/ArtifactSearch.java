package dev.conca.mavenconversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import dev.conca.mavenconversion.model.ArtifactSearchResult;
import dev.conca.mavenconversion.model.Doc;

/**
 * Searches for Maven artifacts in central repository
 * 
 * @author Leandro Conca
 *
 */
public class ArtifactSearch {

	private RestTemplate restTemplate;

	private final String searchBySha1UrlTemplate = "https://search.maven.org/solrsearch/select?q=1:\"{sha1sum}\"&rows=20&wt=json";

	public ArtifactSearch() {
		restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());

		restTemplate.setMessageConverters(messageConverters);
	}

	public List<Doc> findArtifactsBySHA1(String sha1sum) {
		ArtifactSearchResult searchresult = restTemplate.getForObject(
				searchBySha1UrlTemplate, ArtifactSearchResult.class, sha1sum);
		return searchresult.getResponse().getDocs();
	}

}
