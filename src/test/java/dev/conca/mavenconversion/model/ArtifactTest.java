package dev.conca.mavenconversion.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ArtifactTest {

    @Test
    public void testMavenSnippet() {
        Artifact a = new Artifact();
        
        a.setGroupId("org.apache.poi");
        a.setArtifactId("poi-ooxml-schemas");
        a.setVersion("3.9");
        
        assertThat(a.getMavenSnippet(), is("<dependency><groupId>org.apache.poi</groupId><artifactId>poi-ooxml-schemas</artifactId><version>3.9</version></dependency>"));
    }
    
    @Test
    public void testGradleSnippet() {
        Artifact a = new Artifact();
        
        a.setGroupId("org.apache.poi");
        a.setArtifactId("poi-ooxml-schemas");
        a.setVersion("3.9");
        
        assertThat(a.getGradleSnippet(), is("compile 'org.apache.poi:poi-ooxml-schemas:3.9'"));
    }

}
