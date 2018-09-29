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
//		byte[] sl = encrypt3DES(valueByte, ByteUtils.fromHexString(key));
//		String result = Base64.encodeBase64String(sl);
		byte[] sl = des3EncodeECB(ByteUtils.fromHexString(key),valueByte);
		String result = Base64Utils.encodeToString(sl);
		return result;
	}

	public static byte[] encrypt3DES(byte[] input, byte[] key) throws Exception {
		Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
		return c.doFinal(input);
	}

	public static void main(String[] args) throws Exception {
		String desKey = get3Des();
		System.out.println(desKey);
		//desKey: d5191604388585156e67cbb3807a4c58d519160438858515
		System.out.println(getEncryptedValue("wade",desKey));
		System.out.println(getDecryptedValue("zEQMDyxcDWU=","d5191604388585156e67cbb3807a4c58d519160438858515"));
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
//         byte[] valueByte = BytesUtil.hexToBytes(value);
		byte[] valueByte = Base64Utils.decodeFromString(value);
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
//		return Hex.encodeHexString(getKey(System.currentTimeMillis()));
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
			System.arraycopy(secretKey.getEncoded(), 0, key, 0, 24);
//			byte[] key = new byte[16];
//			System.arraycopy(secretKey.getEncoded(), 0, key, 0, 16);
			return key;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	//加密
	public static byte[] encryptRSA(String pkStr, byte[] input) throws Exception {
//  		byte[] encodedKey = DatatypeConverter.parseBase64Binary(pkStr);
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
//		byte[] encodedKey = Base64.decodeBase64(skStr);
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
//        byte[] keyBytes = BytesUtil.hexToBytes(privateKey);
//		byte[] keyBytes = Base64.decodeBase64(privateKey);
		byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(keySpec);
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(value.getBytes("UTF-8"));
		byte[] signed = signature.sign();
//		String result = Base64.encodeBase64String(signed);
		String result = Base64Utils.encodeToString(signed);
//        String result = BytesUtil.bytesToHex(signed);
		return result;
	}

	public static boolean signValidate(String value, String sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        byte[] encodedKey = BytesUtil.hexToBytes(publicKey);
//		byte[] encodedKey = Base64.decodeBase64(publicKey);
		byte[] encodedKey = Base64Utils.decodeFromString(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initVerify(pubKey);
		signature.update(value.getBytes());
//		byte[] bytes = Base64.decodeBase64(sign);
		byte[] bytes = Base64Utils.decodeFromString(sign);
//        byte[] bytes = BytesUtil.hexToBytes(sign);
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

}
