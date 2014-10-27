package org.kay.framework.util.encrypt;

import static org.junit.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;

public class DESedeEncryptUtil {

	public static final String ENCRYPTION_ALGORITHM_3DES = "DESede";

	public static void generateSecretKey() {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM_3DES);
			generator.init(168);
			SecretKey secretKey = generator.generateKey();

			FileOutputStream fos = new FileOutputStream("key.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(secretKey);
			oos.close();
			fos.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String message) {

		String result = "";
		try {
			FileInputStream fis = new FileInputStream("key.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			SecretKey secretKey = (SecretKey) ois.readObject();
			ois.close();
			fis.close();

			// ESede/ECB/PKCS5Padding
			Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_3DES);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] ptext = cipher.doFinal(message.getBytes("UTF-8"));

			Base64 encoder = new Base64();
			result = encoder.encodeAsString(ptext);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String decrypt(String message) {

		String result = "";

		try {
			FileInputStream fis = new FileInputStream("key.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			SecretKey secretKey = (SecretKey) ois.readObject();
			ois.close();
			fis.close();

			// ESede/ECB/PKCS5Padding
			Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_3DES);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			Base64 decoder = new Base64();
			byte[] ptext = decoder.decode(message.getBytes("UTF-8"));
			result = new String(cipher.doFinal(ptext));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@BeforeClass
	public static void before() {
		DESedeEncryptUtil.generateSecretKey();
	}
	
	@Test
	public void testGenerateSecretKey() {
		
		String encryptMsg = DESedeEncryptUtil.encrypt("Hello World");
		System.out.println(encryptMsg);
		String decryptMsg = DESedeEncryptUtil.decrypt(encryptMsg);
		System.out.println(decryptMsg);
		assertEquals("Hello World", decryptMsg);

	}

}
