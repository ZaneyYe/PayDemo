import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * Created by yezhangyuan on 2018-09-27.
 *
 * @author yezhangyuan
 */
public class ShareUserUtil {

	//用于将返回字段的值进行3DES加密
	public static String getEncryptedValue(String value, String key) throws Exception {
		if (null == value || "".equals(value)) {
			return "";
		}
		byte[] valueByte = value.getBytes("UTF-8");
		byte[] sl = des3EncodeECB(ByteUtils.fromHexString(key),valueByte);
		String result = Base64Utils.encodeToString(sl);
		return result;
	}

	public static byte[] encrypt3DES(byte[] input, byte[] key) throws Exception {
		Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
		return c.doFinal(input);
	}


	/**
	 * 3ds加密
	 *
	 * @param key
	 * @param data
	 * @return
	 */
	public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	//用于将返回字段的值进行3DES解密
	public static String getDecryptedValue(String value, String key) throws Exception {
		if (null == value || "".equals(value)) {
			return "";
		}
		//返回结果base64解码
		byte[] valueByte = Base64Utils.decodeFromString(value);
		//3Des解密
		byte[] sl = decrypt3DES(valueByte, ByteUtils.fromHexString(key));
		String result = new String(sl);
		return result;
	}

	public static byte[] decrypt3DES(byte[] input, byte[] key) throws Exception {
		Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
		return c.doFinal(input);
	}


	/**
	 * 将16进制的字符串转换成bytes
	 *
	 * @param hex
	 * @return 转化后的byte数组
	 */
	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

	/**
	 * 将16进制的字符数组转换成byte数组
	 *
	 * @param hex
	 * @return 转换后的byte数组
	 */
	public static byte[] hexToBytes(char[] hex) {
		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127) {
				value -= 256;
			}
			raw[i] = (byte) value;
		}
		return raw;
	}

	/**
	 * 将byte数组转换成16进制字符串
	 *
	 * @param bytes
	 * @return 16进制字符串
	 */
	public static String bytesToHex(byte[] bytes) {
		String hexArray = "0123456789abcdef";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			int bi = b & 0xff;
			sb.append(hexArray.charAt(bi >> 4));
			sb.append(hexArray.charAt(bi & 0xf));
		}
		return sb.toString();
	}

	public static byte[] readAll(InputStream in) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		for (int i = in.read(); i != -1; i = in.read()) {
			bout.write(i);
		}
		return bout.toByteArray();
	}


	//对map中的字段按照字典排序，并返回k1=v1&k2=v2&k3=v3...的字符串样式
	public static String sortMapToString(HashMap map) {
		StringBuilder result = new StringBuilder();
		Collection<String> keySet = map.keySet();
		List<String> list = new ArrayList<String>(keySet);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			result.append(list.get(i)).append("=").append(map.get(list.get(i))).append("&");
		}
		return result.substring(0, result.length()-1);
	}


	public static String get3Des() {
		return bytesToHex(getKey(System.currentTimeMillis()));
	}


	public static byte[] getKey(long seed){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("DESede");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(seed);
			kgen.init(112, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] key = new byte[24];
			System.arraycopy(secretKey.getEncoded(), 0, key, 0,24);
			return key;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	//加密
	public static byte[] encryptRSA(String pkStr, byte[] input) throws Exception {
		byte[] encodedKey = Base64Utils.decodeFromString(pkStr);
		KeySpec keySpec = new X509EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pk = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, pk);
		byte[] result = cipher.doFinal(input);
		return result;
	}

	//解密
	public static byte[] decryptRSA(String skStr, byte[] input) throws Exception {
		byte[] encodedKey = Base64Utils.decodeFromString(skStr);
		KeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey sk = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, sk);
		byte[] result = cipher.doFinal(input);
		return result;
	}

	//RSA签名和验证签名
	public static final String SIGN_ALGORITHMS = "SHA256WithRSA";

	public static String sign(String value, String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(keySpec);
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(value.getBytes("UTF-8"));
		byte[] signed = signature.sign();
		String result = Base64Utils.encodeToString(signed);
		return result;
	}

	public static boolean signValidate(String value, String sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64Utils.decodeFromString(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initVerify(pubKey);
		signature.update(value.getBytes());
		byte[] bytes = Base64Utils.decodeFromString(sign);
		return signature.verify(bytes);
	}

	public static String createNonceStr(int length) {
		String sl = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < length; i++) {
			bf.append(sl.charAt(new Random().nextInt(sl.length())));
		}
		return bf.toString();
	}

	public static void main(String[] args) throws Exception {
//		String prikey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANUMjIW5jSDWjKwGg11Km4+ntKLQHhRFTMK7qGy0xbM4TkxeT9zid8+3oHfjMgJF8iI3Bf8Vc0WyGqCnvMGWm+ZkQz+/EI3PXFPkXw6nGOybfrvIncSUrMUUfk7QL5fYc4joiUgiBzaaEmSlc4Bm8bwB0aCbGXZkKRJXzc67YVe5AgMBAAECgYBpjQcNtYkjDMiy7aMllDIP8QGAbGWKm27VR43BFaqhCTNZiJw+jgDX61zYKLp8/9RfeS1CXIk4rW2dzJyquIhRrAQr+PDNJC0RxF9LwHFqUmwoNp/z8FjWigsrKU7u30JE9FvYg6u1Q2nVfOYiuNOM030lItK3HlWds+n7ccbQkQJBAPWv28PL50jbCVoBruWgUnAjN2b815CUs0x8Qy6sK9xeT4h5c/rtwrYPOsNu5tXvxOh9uXC/JDHKnq8NjCD1fC0CQQDd/fb0XPktnz6fpPS39g2vhUisl7RZHyKDVDMg0p6f/dJH+/wNRKr4bqs0pFuCHqTI+D/RgRLWQ9VoBbAQmWU9AkBkV1R8HPTy7nJdTj6uDdJddiSUbUNlCzZHUKDnmO247NOyu1fA7gYN8R5g3xRr36ceBOsNvyL7KvOwLupCM8BpAkEAmojOvzFxrNasS4oTaXR59nO8MZmxzFCmP/H/XZwBHLMfDUlXHw1sT2ELAaxgn2YhpF1cX8WQjWPPTYiYFhYSqQJAfErvo/GyefwXvkvWHNTCfuEhEqwRgp9O0PDILdsLcSUsViJ1Q33dIg5CnVCGjeOIo3Ogv1RoyD8cqltr1uQCqg==";
//		String str = "appId=jsnsilwcef&cardNo=123456789&certType=00&certifId=140103700322331&nonceStr=xDnIu8hfqGIFhtm4&realNm=刘鸿&registerMobile=13711111111&reserveMobile=13711111111&timestamp=1562235015&userId=WY35100101";
//        String signValue = sign(str,prikey);
//        System.out.println(signValue);
//		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVDIyFuY0g1oysBoNdSpuPp7Si0B4URUzCu6hstMWzOE5MXk/c4nfPt6B34zICRfIiNwX/FXNFshqgp7zBlpvmZEM/vxCNz1xT5F8Opxjsm367yJ3ElKzFFH5O0C+X2HOI6IlIIgc2mhJkpXOAZvG8AdGgmxl2ZCkSV83Ou2FXuQIDAQAB";
//        System.out.println(signValidate(str,signValue,pubKey));

		System.out.println(get3Des());

//		String v = "YWlRKdx9Vi2Uls7pX/ePGQ==";
//		String res = getDecryptedValue(v,"23492c42e6027d85fc807e61ed96bca0a2122e95e3860fc4");
//		System.out.println(res);
//
//		System.out.println(getEncryptedValue("6226220311257306","23492c42e6027d85fc807e61ed96bca0a2122e95e3860fc4").getBytes().length);
//
//		byte[] keys = hexToBytes("26613b3119c2087a1c8c2302d3ead37326613b3119c2087a");
//		System.out.println(keys.length);
	}

}
