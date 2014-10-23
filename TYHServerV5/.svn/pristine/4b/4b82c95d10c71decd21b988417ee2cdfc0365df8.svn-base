package cn.youhui.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;


/**
 * RSA公钥/私钥/签名工具包
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * 
 */
public class RSA{

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
    
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成密钥对(公钥和私钥)
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();   
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /**
     * 用私钥对信息生成数字签名
     * 
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * 
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encode(signature.sign());
    }

    /**
     * 校验数字签名
     * 
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decode(sign));
    }

    /**
     * 私钥解密
     * 
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String datastr, String privateKey)
            throws Exception {
    	byte[] encryptedData = datastr.getBytes("ISO-8859-1");
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    /**
     * 公钥解密
     * 
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String datastr, String publicKey)
            throws Exception {
    	byte[] encryptedData = datastr.getBytes("ISO-8859-1");
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     * 
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String datastr, String publicKey)
            throws Exception {
    	byte[] data = datastr.getBytes();
    	byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(encryptedData, "ISO-8859-1");
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     * 
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String datastr, String privateKey)
            throws Exception {
    	byte[] data = datastr.getBytes();
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(encryptedData, "ISO-8859-1");
    }

    /**
     * 获取私钥
     * 
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encode(key.getEncoded());
    }

    /**
     * 获取公钥
     * 
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encode(key.getEncoded());
    }
    
    public static void main(String[] args) throws Exception {
//    	Map<String, Object> keyMap = genKeyPair();
//    	String pub = getPrivateKey(keyMap);
//    	String pri = getPublicKey(keyMap);
//    	System.out.println(pub);
//    	System.out.println(pri);
    	System.out.println(verify("appkey=123456&jfb_num=22&memo=随手优惠签到送集分宝&order_id=201304080011999999&outer_code=999999&zfb=zheshigezhifubao".getBytes(),"Wn1MRR+DguLEK4TlvhLFrlOulOh27Zxt2+OFVQmZ4nh2HFsW7gqcnXvKCzyqulscRZCY9ymUKx3VCqt8uaf6G9yVsP7KQH1dO6bzDcCnqUPvQ569IJWIKBO1SENLoB5Yx2iE2XmCEPD2Zwrxl272+kLYi7uOFR7JCgYIwOad9F0=","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFhVG1HqwOjCEt931lkvRTESJplwEgpSmeU32olTCtMQOBL/BZyl3Ai8alnmnFoE6mZ/zPbJotuMi6mFGB9ffvZhZL8Lu7KlkFNMzK/WQUwKXazTmOuFHWL2gWZpgPYxxGk5/kegdoU2Zd8875HyS8RzLYQ5+jck51T7oZt0tdcQIDAQAB"));
//    	String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ6dpoDLpUN7wtQmR7EfaIytgaPOT/9BAe5bc0NZLEj2Xr/lXiBP0xJInZN6ZlNH4S5ywHdwALro/ysUe3p6UGNvmCbpl+/iX4F4q0kOQO7urSdCRlAMMo791/ml3Xa51Vkvq33Sd5ZyGhvXHRQIOPqARGpvZ477crs6Y2kZRZXwIDAQAB";
//    	String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAInp2mgMulQ3vC1CZHsR9ojK2Bo85P/0EB7ltzQ1ksSPZev+VeIE/TEkidk3pmU0fhLnLAd3AAuuj/KxR7enpQY2+YJumX7+JfgXirSQ5A7u6tJ0JGUAwyjv3X+aXddrnVWS+rfdJ3lnIaG9cdFAg4+oBEam9njvtyuzpjaRlFlfAgMBAAECgYABpDLhQiWFkxQ+RagEfvY8Mo3X4hmnojP+6iUADhMtG7fO0zwacIzC5PhvCWaFldZaSz4O6K+rc+xqqwl7ehdQswwlpkColcF9n/dWbfl0l28HQZMXCO121/Wq9y7ktszBbyT4tMEUTG0zTNonaKAPUu/dEbVthfTU4eMNMWOEwQJBAOgZju/Jp5svDpSj2n6Q+F6nk2xphTFDJZNQVLLFv6pEtf9ZgXyGCUJSXHusFkfeCCxj2SrT/90oXuS3uTPFJxkCQQCYHWzM0tk4ReJ4FVO7t1VJfsDNMiQSSkM47/tdI0kWMnmJpoNEebU6orlsnkKXrBwpL4Xpl5DnxlHS5EoFnes3AkEAr0XVxkj/dFZV6XPPZXLuzPQ5hU4uUiRF9Phd7d8dvWREGEAsY7AIv/fYdWBgNwd6HqOMdSZBe26oMop3ppvEyQJAT5f1+ibJqnxGx/bA3ZRMeCxuze/SmA9rpTtNF6sC7y5yEeii/w2AGrJSu9cDmUFhatuHAndgRyaqYeM6KD49eQJBALHAluNfFbKPkzN+umD3/7TkJZxderjLk/atfxdXsPLXPZZTtmpIDIObUJd0dMxq4nVUupdVsy1Jt5CF55wcews=";
//    	long s = System.currentTimeMillis();
//	    String jm = encryptByPublicKey("63", pub);
//        System.out.println(jm);
//		long e = System.currentTimeMillis();
//		System.out.println(e -s );
//		System.out.println(jm);
//		System.out.println(decryptByPrivateKey(jm, pri));
//		System.out.println(System.currentTimeMillis()-e);
	}

}
