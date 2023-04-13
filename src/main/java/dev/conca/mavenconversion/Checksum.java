package dev.conca.mavenconversion;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * calculates sha1 checksum of a byte stream
 */
public class Checksum {

	private Checksum() {}

	public static String calculateSHA1Sum(InputStream inputStream) throws IOException {

		MessageDigest md;
		StringBuilder sb = new StringBuilder();

		try {
			md = MessageDigest.getInstance("SHA1");

			byte[] dataBytes = new byte[1024];

			int nread;

			while ((nread = inputStream.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}

			byte[] mdbytes = md.digest();

			for (byte mdbyte : mdbytes) {
				sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16)
						.substring(1));

			}
			
			inputStream.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}