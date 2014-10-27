package org.kay.framework.util.encrypt;

import static org.junit.Assert.assertEquals;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.junit.BeforeClass;
import org.junit.Test;

public class RSAEncryptUtil {

	public static void generateKeyPair() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair keyPair = generator.genKeyPair();

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			FileOutputStream fos = new FileOutputStream("RSA_PUBLIC_KEY.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(publicKey);

			fos = new FileOutputStream("RSA_PRIVATE_KEY.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(privateKey);

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
			FileInputStream fis = new FileInputStream("RSA_PUBLIC_KEY.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			RSAPublicKey pbk = (RSAPublicKey) ois.readObject();

			BigInteger e = pbk.getPublicExponent();
			BigInteger n = pbk.getModulus();

			byte[] ptext = message.getBytes("UTF8");
			BigInteger m = new BigInteger(ptext);

			BigInteger content = m.modPow(e, n);
			result = content.toString();

			FileOutputStream fos = new FileOutputStream("Encrypt_RSA.dat");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(result, 0, result.length());
			bw.close();

		} catch (FileNotFoundException e) {
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
			BigInteger c = new BigInteger(message);
			
			FileInputStream fis = new FileInputStream("RSA_PRIVATE_KEY.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			RSAPrivateKey prk = (RSAPrivateKey) ois.readObject();

			BigInteger e = prk.getPrivateExponent();
			BigInteger n = prk.getModulus();

			BigInteger m = c.modPow(e, n);
			result = new String(m.toByteArray());
			
		} catch (FileNotFoundException e) {
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
		RSAEncryptUtil.generateKeyPair();
	}

	@Test
	public void testEncrypt() {
		String encryptMsg = RSAEncryptUtil.encrypt("Hello World!");
		System.out.println(encryptMsg);
		String decryptMsg = RSAEncryptUtil.decrypt(encryptMsg);
		System.out.println(decryptMsg);
		assertEquals("Hello World!", decryptMsg);
	}
}
