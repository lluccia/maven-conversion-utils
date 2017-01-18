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
		
		Assert.assertEquals("bd935fb8f3819d68c62b8f35d2e5e5d9b571e0ab", sha1sum);
	}
}