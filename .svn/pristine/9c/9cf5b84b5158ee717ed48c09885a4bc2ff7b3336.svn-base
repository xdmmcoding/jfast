 package com.jfast.util;
 
 import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
 public class MD5Utils<main>
 {
   public static final int OXFF = 255;
   public static final int OX10 = 16;
 
   public static String crypt(String str)
     throws NoSuchAlgorithmException
   {
     if ((str == null) || (str.length() == 0)) {
       throw new IllegalArgumentException("String to encript cannot be null or zero length");
     }
 
     StringBuffer hexString = new StringBuffer();
 
     MessageDigest md = MessageDigest.getInstance("MD5");
     md.update(str.getBytes());
     byte[] hash = md.digest();
 
     for (byte aHash : hash) {
       if ((0xFF & aHash) < 16)
         hexString.append("0").append(Integer.toHexString(0xFF & aHash));
       else {
         hexString.append(Integer.toHexString(0xFF & aHash));
       }
     }
 
     return hexString.toString();
   }
 
   public static final String MD5(String s) {
     char[] hexDigits = { '0', '1', '2', '3', '4', 
       '5', '6', '7', '8', '9', 
       'a', 'b', 'c', 'd', 'e', 'f' };
     try {
       byte[] btInput = s.getBytes();
 
       MessageDigest mdInst = MessageDigest.getInstance("MD5");
 
       mdInst.update(btInput);
 
       byte[] md = mdInst.digest();
 
       int j = md.length;
       char[] str = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = md[i];
         str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
         str[(k++)] = hexDigits[(byte0 & 0xF)];
       }
       return new String(str);
     }
     catch (Exception e) {
       e.printStackTrace();
     }return null;
   }

   public static void main(String[] args) {
     System.out.println( MD5Utils.MD5("123456789").toUpperCase());
   }
 }

