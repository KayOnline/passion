package org.kay.framework.util.encrypt;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;

public class PBEEncryptUtil {

	public static void generateSalt() {
		try {
			byte[] salt = new byte[8];
			Random r = new Random();
			r.nextBytes(salt);

			FileOutputStream fos = new FileOutputStream("salt.dat");
			fos.write(salt);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String message, String password) {

		String result = "";

		try {
			PBEKeySpec pbks = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey secretKey = secretKeyFactory.generateSecret(pbks);

			FileInputStream fis = new FileInputStream("salt.dat");
			byte[] salt = new byte[fis.available()];
			fis.read(salt);
			fis.close();

			Cipher cp = Cipher.getInstance("PBEWithMD5AndDES");
			PBEParameterSpec ps = new PBEParameterSpec(salt, 1000);
			cp.init(Cipher.ENCRYPT_MODE, secretKey, ps);

			byte[] ptext = cp.doFinal(message.getBytes("UTF8"));
			Base64 encoder = new Base64();
			result = encoder.encodeAsString(ptext);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String decrypt(String message, String password) {
		
		String result = "";
		
		try {
			PBEKeySpec pbks = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey secretKey = secretKeyFactory.generateSecret(pbks);

			FileInputStream fis = new FileInputStream("salt.dat");
			byte[] salt = new byte[fis.available()];
			fis.read(salt);
			fis.close();

			Cipher cp = Cipher.getInstance("PBEWithMD5AndDES");
			PBEParameterSpec ps = new PBEParameterSpec(salt, 1000);
			cp.init(Cipher.DECRYPT_MODE, secretKey, ps);

			Base64 decoder = new Base64();
			byte[] ptext = decoder.decode(message.getBytes("UTF-8"));
			
			result = new String(cp.doFinal(ptext));
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@BeforeClass
	public static void before() {
		PBEEncryptUtil.generateSalt();
	}
	
	@Test
	public void testEncrypt() {
		String encryptMsg = PBEEncryptUtil.encrypt("Hello World", "kay123");
		System.out.println(encryptMsg);
		String decryptMsg = PBEEncryptUtil.decrypt(encryptMsg, "kay123");
		System.out.println(decryptMsg);
		assertEquals("Hello World", decryptMsg);
	}
}
