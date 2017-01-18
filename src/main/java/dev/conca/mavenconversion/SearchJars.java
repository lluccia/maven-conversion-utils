package dev.conca.mavenconversion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import dev.conca.mavenconversion.mavencentral.MavenCentralSearch;
import dev.conca.mavenconversion.model.Artifact;

public class SearchJars {

	private static Collection<Path> jarFiles;
	private static Map<Path, String> fileChecksums = new HashMap<>();
	private static Map<Path, List<Artifact>> mavenArtifacts = new HashMap<>();

	private static MavenCentralSearch mavenCentralSearch = new MavenCentralSearch();

	private static File outputFile = new File("output.txt");
	
	public static void main(String[] args) throws IOException {
	    
		System.out.println("looking for jar files...");
		findJarFiles(args[0]);
		System.out.println("Found " + jarFiles.size() + " files...");
		
		System.out.println("calculating checksums...");
		calculateChecksums();

		System.out.println("searching maven central...");
		searchMavenCentral();

		System.out.println("creating report...");
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
			fileChecksums.put(path, Checksum.calculateSHA1Sum(path.toFile()));
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

	private static void printResults() throws IOException {
		String header = "FILE_PATH" + "\t"
				+ "CHECKSUM" + "\t"
				+ "MAVEN_ID" + "\t"
				+ "MAVEN_SNIPPET" + "\t"
				+ "GRADLE_SNIPPET" + "\t"
				+ "TIMESTAMP" + "\t"
				+ "CLASSIFIERS";
		
		FileUtils.write(outputFile, header + "\n");
		
		for (Entry<Path, List<Artifact>> entry : mavenArtifacts.entrySet()) {
			Path filePath = entry.getKey();
			List<Artifact> docs = entry.getValue();

			if (docs.size() == 0) {
				String artifactDetails = filePath + "\t"
						+ fileChecksums.get(filePath) + "\t"
						+ "No artifacts found";
				System.out.println(artifactDetails);
				FileUtils.write(outputFile, artifactDetails + "\n", true);
			} else {
				for (Artifact doc : docs) {
					String artifactDetails = filePath + "\t"
							+ fileChecksums.get(filePath) + "\t"
							+ doc.getId() + "\t"
							+ doc.getMavenSnippet() + "\t"
							+ doc.getGradleSnippet() + "\t"
							+ new DateTime(doc.getTimestamp()).toString() + "\t"
							+ "[" + StringUtils.join(doc.getClassifiers(), ", ") + "]";
					
					System.out.println(artifactDetails);
					FileUtils.write(outputFile, artifactDetails + "\n", true);
				}
			}
		}
	}
};
