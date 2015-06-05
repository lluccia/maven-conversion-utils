package dev.conca.mavenconversion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import dev.conca.mavenconversion.model.Doc;

public class SearchJars {

	private static Collection<Path> jarFiles;
	private static Map<Path, String> fileChecksums = new HashMap<>();
	private static Map<Path, List<Doc>> mavenArtifacts = new HashMap<>();

	private static ArtifactSearch artifactSearch = new ArtifactSearch();

	public static void main(String[] args) throws IOException {
		System.out.println("looking for jar files...");
		findJarFiles("/git/AGI");

		System.out.println("calculating checksums...");
		calculateChecksums();

		System.out.println("searching maven central...");
		searchMavenCentral();

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

			List<Doc> docs = artifactSearch.findArtifactsBySHA1(checkSum);

			mavenArtifacts.put(filePath, docs);
		}
	}

	private static void printResults() {
		for (Entry<Path, List<Doc>> entry : mavenArtifacts.entrySet()) {
			Path filePath = entry.getKey();
			List<Doc> docs = entry.getValue();

			if (docs.size() == 0) {
				System.out.println(filePath + "\tNo artifacts found");
			} else {
				for (Doc doc : docs) {
					System.out.println(filePath + "\t" + doc.getId() + "\t"
							+ doc.getMavenSnippet() + "\t"
							+ new DateTime(doc.getTimestamp()).toString()
							+ "\t" + StringUtils.join(doc.getEc()));
				}
			}
		}
	}
};
