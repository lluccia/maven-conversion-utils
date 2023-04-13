package dev.conca.mavenconversion;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class ChecksumTest {
	@Test
	public void testCalculateSHA1Sum() throws IOException {
		InputStream inputStream = ChecksumTest.class.getClassLoader().getResourceAsStream("checksum-test.txt");
		
		String sha1sum = Checksum.calculateSHA1Sum(inputStream);
		
		Assert.assertEquals("45fce86e4350c472b8e87eb0f3773328b374cbdc", sha1sum);
	}
}