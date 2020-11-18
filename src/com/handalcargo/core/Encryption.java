package com.handalcargo.core;

import java.util.Base64;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

// I tried lol. It's better than nothing.
public class Encryption {
	
	private static final String unicodeFormat = "UTF8";
	private static final String encryptionScheme = "DESede";
    private static final String encryptionKey = "TomYumSukiyakiSamgyeopsalBakmiFettuccine";
    
    private static Cipher cipher;
    private static SecretKey key;
	
	public static void initialize() {
		try {
			// Convert the Encryption Key string into a keySpec.
			byte[] arrayBytes = encryptionKey.getBytes(unicodeFormat);
			KeySpec keySpec = new DESedeKeySpec(arrayBytes);
			
			// Generate the key from the keySpec.
			SecretKeyFactory secretKeyGenerator = SecretKeyFactory.getInstance(encryptionScheme);
			key = secretKeyGenerator.generateSecret(keySpec);
			
			// Instantiate the cipher.
			cipher = Cipher.getInstance(encryptionScheme);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String encrypt(String password) {
		String encryptedPassword = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			byte[] plainText = password.getBytes(unicodeFormat);
			byte[] encryptedText = cipher.doFinal(plainText);
			
			encryptedPassword = Base64.getEncoder().encodeToString(encryptedText);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return encryptedPassword;
	}
	
	public static String decrypt(String encryptedPassword) {
		String password = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			byte[] encryptedText = Base64.getDecoder().decode(encryptedPassword);
			byte[] plainText = cipher.doFinal(encryptedText);
			
			password = new String(plainText, unicodeFormat);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return password;
	}
}
