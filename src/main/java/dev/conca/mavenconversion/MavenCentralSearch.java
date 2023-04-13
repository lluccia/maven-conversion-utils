package dev.conca.mavenconversion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches for Maven artifacts in central repository
 *
 * @author Leandro Conca
 */
public class MavenCentralSearch {

    private static final String SHA1_SEARCH_URL_TEMPLATE = "https://search.maven.org/solrsearch/select?q=1:{sha1sum}&rows=20&wt=json";

    public List<Artifact> findArtifactsBySHA1(String sha1sum) {
        URI searchUri = URI.create(SHA1_SEARCH_URL_TEMPLATE.replace("{sha1sum}", sha1sum));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(searchUri).GET().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() != 200) {
            System.err.println("error calling api. httpstatus=" + response.statusCode());
        }

        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray docs = jsonObject.getJSONObject("response").getJSONArray("docs");
        List<Artifact> artifacts = new ArrayList<>();
        for (Object o : docs) {
            JSONObject doc = (JSONObject) o;
            artifacts.add(toArtifact(doc));
        }

        return artifacts;
    }

    private Artifact toArtifact(JSONObject doc) {
        Artifact a = new Artifact();
        a.setId(doc.getString("id"));
        a.setGroupId(doc.getString("g"));
        a.setArtifactId(doc.getString("a"));
        a.setVersion(doc.getString("v"));
        a.setTimestamp(doc.getLong("timestamp"));
        a.setClassifiers(doc.getJSONArray("ec").toList().stream()
                .map(Object::toString).toList());
        return a;
    }

}
