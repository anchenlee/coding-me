package cn.youhui.utils;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author Eric
 * 
 */
public class DESede {
	
	private static Logger log = Logger.getLogger(DESede.class) ;
	
	/** 指定加密算法为DESede */
	private static String ALGORITHM = "DESede";
	
	/** 指定加密密钥 **/
	static byte[] rawKeyData = "YiadXLd(a.;@#!@3A^&EEwOP".getBytes();
	
	/** 声明 ***/
	static SecureRandom sr = null ;
	static DESedeKeySpec dks = null ;
	static SecretKeyFactory keyFactory = null ;
	static SecretKey key = null ;
	
	static {
		/** 初始化加密算法 **/
		sr = new SecureRandom();
		try {
			dks = new DESedeKeySpec(rawKeyData);
			keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			key = keyFactory.generateSecret(dks);
		} catch (Exception e){
			log.error(e, e) ;
			e.printStackTrace();
			throw new RuntimeException("DESede SecretKeyFactory initialized failed !") ;
		}
	}
	
	private static final ThreadLocal<Cipher> enHolder = new ThreadLocal<Cipher>(){
		@Override
		protected Cipher initialValue() {
			// TODO Auto-generated method stub
			Cipher en_cipher ;
			try {
				en_cipher = Cipher.getInstance(ALGORITHM);
				en_cipher.init(Cipher.ENCRYPT_MODE, key, sr);
				return en_cipher ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DESede Cipher initialized failed !") ;
			}
		}
	} ;
	
	private static final ThreadLocal<Cipher> deHolder = new ThreadLocal<Cipher>(){
		@Override
		protected Cipher initialValue() {
			// TODO Auto-generated method stub
			Cipher de_cipher ;
			try {
				de_cipher = Cipher.getInstance(ALGORITHM);
				de_cipher.init(Cipher.DECRYPT_MODE, key, sr);
				return de_cipher ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("DESede Cipher initialized failed !") ;
			}
		}
	} ;
	
	/***
	 * 
	 * 
	 * 
	 * @param stringData
	 * @return the encrypted string , otherwise <i>null</i>
	 */
	public static String encrypt(String stringData){
		try {
			return byte2hex(enHolder.get().doFinal(stringData.getBytes())) ;
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			log.error(e, e) ;
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			log.error(e, e) ;
		} catch (Exception e){
			log.error(e, e) ;
		}
		return null ;
	}
	
	/***
	 * 
	 * 
	 * @param hexStringData
	 * @return the original string ,otherwise <i>null</i>
	 */
	public static String decrypt(String hexStringData){
		try {
			return new String(deHolder.get().doFinal(hex2byte(hexStringData))) ;
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			log.error(e, e) ;
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			log.error(e, e) ;
		} catch (Exception e){
			log.error(e, e) ;
		}
		
		return null ;
	}

	private static String byte2hex(byte[] b) {
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

	private static byte[] hex2byte(String str) {
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
				b[i / 2] = (byte) Integer
						.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
	    String s  = DESede.encrypt("{\"trade_id\":20140106123456,\"trade_type\":\"lottery\",\"summray\":\"购买了什么呢\"}");
	    System.out.println(s);
	    System.out.println(DESede.decrypt(s));
    }
}
