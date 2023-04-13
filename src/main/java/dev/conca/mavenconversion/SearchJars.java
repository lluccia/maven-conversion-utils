package dev.conca.mavenconversion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SearchJars {

    private static Collection<Path> jarFiles;
    private static final Map<Path, String> fileChecksums = new HashMap<>();
    private static final Map<Path, List<Artifact>> mavenArtifacts = new HashMap<>();

    private static final MavenCentralSearch mavenCentralSearch = new MavenCentralSearch();

    public static void main(String[] args) throws IOException {

        System.err.println("looking for jar files...");
        findJarFiles(args[0]);
        System.err.println("Found " + jarFiles.size() + " files...");

        System.err.println("calculating checksums...");
        calculateChecksums();

        System.err.println("searching maven central...");
        searchMavenCentral();

        System.err.println("creating report...");
        printResults();

    }

    private static void findJarFiles(String basePath) throws IOException {
        JarFinder finder = new JarFinder();
        Path startingDir = Paths.get(basePath);
        Files.walkFileTree(startingDir, finder);
        jarFiles = finder.getJarPaths();
    }

    private static void calculateChecksums() throws IOException {
        for (Path path : jarFiles) {
            InputStream is = new FileInputStream(path.toFile());
            fileChecksums.put(path, Checksum.calculateSHA1Sum(is));
        }
    }

    private static void searchMavenCentral() {
        for (Entry<Path, String> entry : fileChecksums.entrySet()) {
            Path filePath = entry.getKey();
            String checkSum = entry.getValue();

            List<Artifact> docs = mavenCentralSearch.findArtifactsBySHA1(checkSum);

            mavenArtifacts.put(filePath, docs);
        }
    }

    private static void printResults() {
        String header = "FILE_PATH" + "\t"
                + "CHECKSUM" + "\t"
                + "MAVEN_ID" + "\t"
                + "MAVEN_SNIPPET" + "\t"
                + "GRADLE_SNIPPET" + "\t"
                + "TIMESTAMP" + "\t"
                + "CLASSIFIERS";

        System.out.println(header);

        for (Entry<Path, List<Artifact>> entry : mavenArtifacts.entrySet()) {
            Path filePath = entry.getKey();
            List<Artifact> docs = entry.getValue();

            if (docs.isEmpty()) {
                String artifactDetails = filePath + "\t"
                        + fileChecksums.get(filePath) + "\t"
                        + "No artifacts found";
                System.out.println(artifactDetails);
            } else {
                for (Artifact doc : docs) {
                    String artifactDetails = filePath + "\t"
                            + fileChecksums.get(filePath) + "\t"
                            + doc.getId() + "\t"
                            + doc.getMavenSnippet() + "\t"
                            + doc.getGradleSnippet() + "\t"
                            + Instant.ofEpochMilli(doc.getTimestamp()) + "\t"
                            + doc.getClassifiers().stream().collect(
                            Collectors.joining(", ", "[", "]"));

                    System.out.println(artifactDetails);
                }
            }
        }
    }
}
