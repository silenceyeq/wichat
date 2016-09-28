package com.wichat.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESHelper {
	/**
	 * AES密钥
	 */
	private static String keyWord = "u1city.net201306";

	/**
	 * 默认密钥向量
	 */
	private static byte[] _key1 = {  0x19, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF, 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

	/**
	 * AES加密算法
	 */
	public static String AESEncrypt(String plainText) {
		String encryptedValue = null;
		try {
			Key key = new SecretKeySpec(keyWord.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] byteContent = plainText.getBytes("utf-8");
			try {
				cipher.init(Cipher.ENCRYPT_MODE, key,
						new IvParameterSpec(_key1));
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			}
			byte[] encValue = cipher.doFinal(byteContent);
			encryptedValue = new String(new Base64().encode(encValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedValue;
	}	
	
	public static String AESDecrypt(String plainText){
		String decryptValue = null;
		try {
			Key key = new SecretKeySpec(keyWord.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] byteContent = new Base64().decode(plainText);
			try {
				cipher.init(Cipher.DECRYPT_MODE, key,
						new IvParameterSpec(_key1));
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			}
			byte[] decValue = cipher.doFinal(byteContent);
			decryptValue = new String(decValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptValue;
	}
	
	
	public static void main(String[] args) {
		String i = AESEncrypt("1wichat");
		System.out.println(i);
		String k = AESDecrypt(i);
		System.out.println(k);
	}
}
