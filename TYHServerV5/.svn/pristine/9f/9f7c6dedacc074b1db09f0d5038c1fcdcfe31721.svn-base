package cn.youhui.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.youhui.common.Config;

/**
 * 
* @description 
* android和java通用的AES加密方法
* @author chenlong
 */
public class AES {
	public static final String VIPARA = "0102030405060708";
	public static final String bm = "UTF-8";

	public static String encrypt(String dataPassword, String cleartext) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));
		return new String(Base64.encode(encryptedData));
	}

	public static String decrypt(String dataPassword, String encrypted) throws Exception {
		byte[] byteMi = Base64.decode(encrypted);
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] decryptedData = cipher.doFinal(byteMi);
		return new String(decryptedData, bm);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(decrypt(Config.AES_PWD, "OyIZHpvv8Rw4RKLvR8mv0Q=="));
	}
}
