package cn.youhui.utils;
 
import java.security.Key;  
import java.security.Security;
 
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;  
 
public class AES256{  
     
         /** 
         * 密钥算法 
         * java6支持56位密钥，bouncycastle支持64位 
         * */  
        public static final String KEY_ALGORITHM="AES";  
           
        /** 
         * 加密/解密算法/工作模式/填充方式 
         *  
         * JAVA6 支持PKCS5PADDING填充方式 
         * Bouncy castle支持PKCS7Padding填充方式 
         * */  
        public static final String CIPHER_ALGORITHM="AES/ECB/PKCS7Padding";  
           
 
        /** 
         * 转换密钥 
         * @param key 二进制密钥 
         * @return Key 密钥 
         * */  
        public static Key toKey(byte[] key) throws Exception{  
            //实例化DES密钥  
            //生成密钥  
            SecretKey secretKey=new SecretKeySpec(key,KEY_ALGORITHM);  
            return secretKey;  
        }  
           
        /** 
         * 加密数据 
         * @param data 待加密数据 
         * @param key 密钥 
         * */  
        public static String encrypt(String key, String data) throws Exception{  
            //还原密钥  
            Key k=toKey(key.getBytes());  
            /** 
             * 实例化 
             * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现 
             * Cipher.getInstance(CIPHER_ALGORITHM,"BC") 
             */  
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM, "BC");  
            //初始化，设置为加密模式  
            cipher.init(Cipher.ENCRYPT_MODE, k);  
            //执行操作  
            return new String(Base64.encode(cipher.doFinal(data.getBytes("UTF-8"))));  
        }  
        /** 
         * 解密数据 
         * @param data 待解密数据 
         * @param key 密钥 
         * */  
        public static String decrypt(String key, String data) throws Exception{  
            Key k =toKey(key.getBytes());  
            /** 
             * 实例化 
             * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现 
             * Cipher.getInstance(CIPHER_ALGORITHM,"BC") 
             */  
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);  
            //初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, k);  
            //执行操作  
            return new String(cipher.doFinal(Base64.decode(data)),"UTF-8");  
        }  
        
        public static void main(String[] args) {
			try {
//				System.out.println(encrypt("suishou774999810", "13142800149"));
				System.out.println(decrypt("suishou774999810", "znCuvekuPqDPVzc/Yy4UmA=="));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }