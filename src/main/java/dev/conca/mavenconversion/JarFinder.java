package dev.conca.mavenconversion;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JarFinder extends SimpleFileVisitor<Path> {

	private List<Path> jarPaths = new ArrayList<>();

	private void find(Path file) {
		Path fileName = file.getFileName();
		if (fileName != null
				&& fileName.toString().toLowerCase().endsWith(".jar")) {
			jarPaths.add(file);
			System.err.println("Found: " + file.toString());
		}
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		find(file);
		return CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		System.err.println(exc);
		return CONTINUE;
	}

	public Collection<Path> getJarPaths() {
		return Collections.unmodifiableCollection(jarPaths);
	}
}
