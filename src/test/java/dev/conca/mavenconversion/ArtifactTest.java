package dev.conca.mavenconversion;

import static org.junit.Assert.assertEquals;

import dev.conca.mavenconversion.Artifact;
import org.junit.Test;

public class ArtifactTest {

    @Test
    public void testMavenSnippet() {
        Artifact a = new Artifact();
        
        a.setGroupId("org.apache.poi");
        a.setArtifactId("poi-ooxml-schemas");
        a.setVersion("3.9");
        
        assertEquals(
            "<dependency><groupId>org.apache.poi</groupId><artifactId>poi-ooxml-schemas</artifactId><version>3.9</version></dependency>",
            a.getMavenSnippet());
    }
    
    @Test
    public void testGradleSnippet() {
        Artifact a = new Artifact();
        
        a.setGroupId("org.apache.poi");
        a.setArtifactId("poi-ooxml-schemas");
        a.setVersion("3.9");
        
        assertEquals("compile 'org.apache.poi:poi-ooxml-schemas:3.9'",
            a.getGradleSnippet());
    }

}
