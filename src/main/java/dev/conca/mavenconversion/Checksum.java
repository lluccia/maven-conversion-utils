package dev.conca.mavenconversion;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {
	
	public static String calculateSHA1Sum(InputStream inputStream) throws IOException {

		MessageDigest md;
		StringBuilder sb = new StringBuilder("");

		try {
			md = MessageDigest.getInstance("SHA1");

			byte[] dataBytes = new byte[1024];

			int nread = 0;

			while ((nread = inputStream.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			;

			byte[] mdbytes = md.digest();

			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
						.substring(1));

			}
			
			inputStream.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
}