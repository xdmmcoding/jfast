package com.jfast.util;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {
	
	private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥
	
	public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG+O7Zy8cArEODGiFQeB7zOxe2VAXZvT4clfUxVem4Cam+gb4JlHk4gAbpmJmti1bnz9LYcIidVEkrgnbeW6JJblpErQDCFyW0+pqtgCtzZ37nZ6TQU58XipQ2KXdvD8iAUiR/E2WigzckAUx/6wKLoy02clcegwMRb9o/knUILQIDAQAB";
	public static String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAIb47tnLxwCsQ4MaIVB4HvM7F7ZUBdm9PhyV9TFV6bgJqb6BvgmUeTiABumYma2LVufP0thwiJ1USSuCdt5bokluWkStAMIXJbT6mq2AK3NnfudnpNBTnxeKlDYpd28PyIBSJH8TZaKDNyQBTH/rAoujLTZyVx6DAxFv2j+SdQgtAgMBAAECgYEAgtKRoCS1tF9D6LTRCZAEX7ktGJqEOTIGG+r1h2ZAbRNgpbhXnC1++VaOXKh+fAmMqI91TDh3Ehd5/GQuFwQ2XQxdimHdwNMqfX2tXAVo/RLF3YOZwGZq7HZeOa/39WQHcHEKS9sJf9qqwwBn0sRPE4gdO0GGf1hQj2qsBS/GGwECQQDdnd8mudIIBUhTsRmsErw0/DWnMLsmdoXTCDtOvHKQrQpkt+Y69RHTSXvtgrXFKrxGjxFvgdmKUiVgjwGA2LshAkEAm+m9gQV0Mb1rGIBaTs0LZSYjTlcQ5VAFNNTuuyj4Zug+hxrlL7Gn4DC6EB4WJ6xdvYvwakt3/08UlurHT4YXjQJBALbr7hr62b/BDHf4ACaluC9L1cVQVcljnwYXD0zoAvAjxCZqHGChk7sANohOkVZS9eCPimatmS+wqiVPJICDY8ECQFLeNFPsIysAw3hdWCKEByd20tMjoZ5QHsRK3KxrhUOk8v76Q5BK+7BEFdDVPO9PRO+m/lxwylHPpWDlXpQc8E0CQQDGnlPW/D/E8f3CtqdKZsmV7slvVh4P9XoXnFfA4Jm2xqsYCDgzJ1vmzNzTBbyvFvPEivaRev/jEYwwR3Q/2SHY";
	
	public static void main(String[] args) throws Exception {
		//生成公钥和私钥
		genKeyPair();
		//加密字符串
		String message = "df723820";
		System.out.println("随机生成的公钥为:" + keyMap.get(0));
		System.out.println("随机生成的私钥为:" + keyMap.get(1));
		String messageEn = encrypt(message,keyMap.get(0));
		System.out.println(message + "\t加密后的字符串为:" + messageEn);
		String messageDe = decrypt(messageEn,keyMap.get(1));
		System.out.println("还原后的字符串为:" + messageDe);
	}

	/** 
	 * 随机生成密钥对 
	 * @throws NoSuchAlgorithmException 
	 */  
	public static void genKeyPair() throws NoSuchAlgorithmException {  
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
		// 初始化密钥对生成器，密钥大小为96-1024位  
		keyPairGen.initialize(1024,new SecureRandom());  
		// 生成一个密钥对，保存在keyPair中  
		KeyPair keyPair = keyPairGen.generateKeyPair();  
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥  
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥  
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));  
		// 得到私钥字符串  
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));  
		// 将公钥和私钥保存到Map
		keyMap.put(0,publicKeyString);  //0表示公钥
		keyMap.put(1,privateKeyString);  //1表示私钥
	}  
	/** 
	 * RSA公钥加密 
	 *  
	 * @param str 
	 *            加密字符串
	 * @param publicKey 
	 *            公钥 
	 * @return 密文 
	 * @throws Exception 
	 *             加密过程中的异常信息 
	 */  
	public static String encrypt( String str, String publicKey ) throws Exception{
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		//RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	/** 
	 * RSA私钥解密
	 *  
	 * @param str 
	 *            加密字符串
	 * @param privateKey 
	 *            私钥 
	 * @return 铭文
	 * @throws Exception 
	 *             解密过程中的异常信息 
	 */  
	public static String decrypt(String str, String privateKey) throws Exception{
		//64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);  
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));  
		//RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}

}

