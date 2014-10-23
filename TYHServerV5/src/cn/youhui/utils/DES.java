package cn.youhui.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密
 */
public class DES {
	/** 指定加密算法为DESede */
	private static String ALGORITHM = "DES";
	
	public static String encode(String rawKey,String stringData){
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(rawKey.getBytes());
			
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			
			SecretKey key = keyFactory.generateSecret(dks);
			
			Cipher en_cipher = Cipher.getInstance(ALGORITHM);
			en_cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			return byte2hex(en_cipher.doFinal(stringData.getBytes())) ;
		}catch (Exception e){
			e.printStackTrace() ;
		}
		return null ;
	}
	
	public static String decode(String rawKey, String content){
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(rawKey.getBytes());
			
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			
			SecretKey key = keyFactory.generateSecret(dks);
			
			Cipher de_cipher = Cipher.getInstance(ALGORITHM);
			de_cipher.init(Cipher.DECRYPT_MODE, key, sr);
			return new String(de_cipher.doFinal(hex2byte(content))) ;
		}catch (Exception e){
			e.printStackTrace() ;
		}
		return null ;
	}
	
	// 二进制转字符串
	public static String byte2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		for (int i = 0; i < b.length; i++) {
			tmp = Integer.toHexString(b[i] & 0XFF);
			if (tmp.length() == 1) {
				sb.append("0" + tmp);
			} else {
				sb.append(tmp);
			}
		}
		return sb.toString();
	}
	
	// 字符串转二进制
	public static byte[] hex2byte(String str) {
		if (str == null) {
			return null;
		}
		
		str = str.trim();
		int len = str.length();
		
		if (len == 0 || len % 2 == 1) {
			return null;
		}
		
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String key = "12312345";
//		String s = encode(key, "012345000#678123");
//		String ss = "164cddc4f51647da6e1e2ec33b13549c1e622955d3443921b7bfb540e001b3f8b66a58b556dd451d920bcda40dfc923853c12809000344651245fae4791ddfecfb08f6fe40fcc4fe94f335f1074fed590b59b410468dc85ba9bcaff8398d93689d5c2c04b150c18d5146b2a4c047e7e1";
		System.out.println(encode(key, ""));
	}
}
