package com.jfast.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CipherPool {
	private static final Logger logger = LoggerFactory.getLogger(CipherPool.class);
	static Stack<Cipher> freePool = new Stack<>();
	static Set<Cipher> occupiedPool = new HashSet<>();
	private static int maxPoolSize = 1000;
	private static int cipherNum = 0;
	static String keyStoreFile = "clientcert.jks";
	static String keyStorePassword = "123456";
	static String keyAlias = "readerex client";
	static String keyPassword = "123456";

	public static Cipher getCipherPool() {
		Cipher cipher = null;

		while (isFull()) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cipher = getCipherFromPool();
		if (cipher == null) {
			cipher = createCipher();
		}
		if(cipher == null)
			logger.error("tmd i m null");
		return cipher;
	}

	public static Cipher createCipher() {
		Cipher requestDecryptCipher = null;
		try {
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			ks.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+keyStoreFile), keyStorePassword.toCharArray());
			RSAPrivateCrtKey issuerPrivateKey = (RSAPrivateCrtKey) ks.getKey(keyAlias, keyPassword.toCharArray());
			RSAPrivateKeySpec spec = new RSAPrivateKeySpec(issuerPrivateKey.getModulus(),
					issuerPrivateKey.getPrivateExponent());
			Key fakePublicKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
			requestDecryptCipher = Cipher.getInstance("RSA");
			requestDecryptCipher.init(Cipher.DECRYPT_MODE, fakePublicKey);
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
		}
		logger.error("cipher create");
		return requestDecryptCipher;
	}

	private synchronized static boolean isFull() {
		return ((freePool.size() == 0) && (cipherNum >= maxPoolSize));
	}

	private static Cipher getCipherFromPool() {
		Cipher cipher = null;
		if (freePool.size() > 0) {
			logger.error("free 有：" + freePool.size());
			cipher = freePool.pop();
			logger.error("free size：" + freePool.size());
			occupiedPool.add(cipher);
			logger.error("occupiedPool size：" + occupiedPool.size());
		} else {
			logger.error("free 为 空");
		}
		return cipher;
	}

	public static synchronized void returnCipher(Cipher cipher) {
		logger.error("cipher return");
		if (cipher == null) {
			throw new NullPointerException();
		}
		logger.error("occupiedPool return size：" + occupiedPool.size());
		occupiedPool.remove(cipher);
		logger.error("occupiedPool return remove size：" + occupiedPool.size());
		freePool.push(cipher);
		logger.error("freePool size：" + freePool.size());
	}
}
