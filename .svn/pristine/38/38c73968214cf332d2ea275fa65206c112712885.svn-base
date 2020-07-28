package com.jfast.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class AuthDecrypt {
	private static Cipher requestDecryptCipher = null;

	@SuppressWarnings("unused")
	public static String DecryptRequest(String request, String keyStoreFile, String keyStorePassword, String keyAlias,
			String keyPassword) {
		int templen = 0;
		int alllen = 0;
		try {
			if (requestDecryptCipher == null) {
				KeyStore ks = KeyStore.getInstance("JKS", "SUN");
				ks.load(new FileInputStream(keyStoreFile), keyStorePassword.toCharArray());
				RSAPrivateCrtKey issuerPrivateKey = (RSAPrivateCrtKey) ks.getKey(keyAlias, keyPassword.toCharArray());
				RSAPrivateKeySpec spec = new RSAPrivateKeySpec(issuerPrivateKey.getModulus(),
						issuerPrivateKey.getPrivateExponent());
				Key fakePublicKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
				requestDecryptCipher = Cipher.getInstance("RSA");
				requestDecryptCipher.init(Cipher.DECRYPT_MODE, fakePublicKey);
			}
			byte[] encryptedData = Base64.decodeBase64(request.getBytes());
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			alllen = inputLen;
			synchronized (requestDecryptCipher) {
				while (inputLen - offSet > 0) {
					if (inputLen - offSet > 128) {
						cache = requestDecryptCipher.doFinal(encryptedData, offSet, 128);
					} else {
						templen = inputLen - offSet;
						cache = requestDecryptCipher.doFinal(encryptedData, offSet, inputLen - offSet);
					}
					out.write(cache, 0, cache.length);
					i++;
					offSet = i * 128;
				}
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return new String(decryptedData, 0, decryptedData.length, "UTF8");
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String DecryptRequest(String request) {
		try {
			byte[] encryptedData = Base64.decodeBase64(request.getBytes());
			Cipher cipher = CipherPool.getCipherPool();
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > 128) {
					cache = cipher.doFinal(encryptedData, offSet, 128);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * 128;
			}
			byte[] decryptedData = out.toByteArray();
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			CipherPool.returnCipher(cipher);
			return new String(decryptedData, 0, decryptedData.length, "UTF8");
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DecryptRequest("YWenYK+IpRGKId29qRl91LKSvr8LiCFlKIBgQp6v/c7P98VmK44CK4E8frrNkE0fF9CF84gRo5hSARD+IC6vadKqYl1dLkE7Ml5/BqRj6Xk/HiaM0mDc3SDrum99lmzIpiifhE1eWxFoMvU0QJGJ1p/bblM/OEj7e5uOj+dg3Xg=",
		"/clientcert.jks", "123456", "readerex client", "123456"));
	}
}
