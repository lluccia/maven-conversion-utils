package dev.conca.mavenconversion;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;

public class ChecksumTest {

	@Test
	public void testCalculateSHA1Sum() throws IOException {
		String sha1sum = Checksum
				.calculateSHA1Sum(new File("checksum-test.txt"));
		
		Assert.assertEquals("bd935fb8f3819d68c62b8f35d2e5e5d9b571e0ab", sha1sum);
	}
}