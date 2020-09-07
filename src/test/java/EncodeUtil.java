import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA公私钥生成工具
 *
 */
public class EncodeUtil {

	public static String rsaEncryptWithPk(String value, String publicKey) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] pubilcKeyBytes = HexUtil.hexToBytes(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(pubilcKeyBytes);
		PublicKey pubKey = keyFactory.generatePublic(publicX509);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = HexUtil.bytesToHex(resultBytes);
		return result;
	}

	public static String rsaDecryptWithPk(String value, String publicKey) throws Exception {
		byte[] valueBytes = HexUtil.hexToBytes(value);
		byte[] pubilcKeyBytes = HexUtil.hexToBytes(publicKey);
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(pubilcKeyBytes);
		PublicKey pubKey = keyf.generatePublic(publicX509);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = new String(resultBytes, "UTF-8");
		return result;
	}

	public static String rsaEncryptWithSk(String value, String privateKey) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] privateKeyBytes = HexUtil.hexToBytes(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, priKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
//		String result = Hex.encodeHexString(resultBytes);
		String result = HexUtil.bytesToHex(resultBytes);
		return result;
	}

	public static String rsaDecryptWithSk(String value, String privateKey) throws Exception {
		byte[] valueBytes = HexUtil.hexToBytes(value);
		byte[] privateKeyBytes = HexUtil.hexToBytes(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = new String(resultBytes, "UTF-8");
		return result;
	}

	public static String sign(String value, String privateKey) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] privateKeyBytes = HexUtil.hexToBytes(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
		Signature signature = Signature.getInstance("SHA1WithRSA");
		signature.initSign(priKey);
		signature.update(valueBytes);
		byte[] resultBytes = signature.sign();
		String result = HexUtil.bytesToHex(resultBytes);
		return result;
	}

	public static boolean verify(String value, String signStr, String publicKey) throws Exception {
		byte[] valueBytes = HexUtil.hexToBytes(value);
		byte[] publicKeyBytes = HexUtil.hexToBytes(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey pubKey = keyFactory.generatePublic(publicX509);
		Signature signature = Signature.getInstance("SHA1WithRSA");
		signature.initVerify(pubKey);
		signature.update(valueBytes);
		byte[] resultBytes = HexUtil.hexToBytes(signStr);
		boolean result = signature.verify(resultBytes);
		return result;
	}

	public static void generateRsaKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		byte[] publicKeyBytes = publicKey.getEncoded();
		byte[] privateKeyBytes = privateKey.getEncoded();
		System.out.println("私钥(Base64):" + Base64Utils.encodeToString(privateKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("公钥(Base64):" + Base64Utils.encodeToString(publicKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("私钥(十六进制):" + HexUtil.bytesToHex(privateKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("公钥(十六进制):" + HexUtil.bytesToHex(publicKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("私钥模:" + privateKey.getModulus());
		System.out.println("-----------------------------------------");
		System.out.println("公钥模:" + publicKey.getModulus());
		System.out.println("-----------------------------------------");
		System.out.println("公钥模(十六进制):" + publicKey.getModulus().toString(16));
		System.out.println("-----------------------------------------");
	}

	public static String desEncrypt(String value, String key) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] keyBytes = HexUtil.hexToBytes(key);
		SecretKey desKey = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String resuslt = HexUtil.bytesToHex(resultBytes);
		return resuslt;
	}

	public static String desDecrypt(String value, String key) throws Exception {
		byte[] valueBytes = HexUtil.hexToBytes(value);
		byte[] keyBytes = HexUtil.hexToBytes(key);
		SecretKey desKey = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, desKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = new String(resultBytes, "UTF-8");
		return result;
	}

	public static void main(String[] args) throws Exception {
//		String key = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
//		String aa = "hello";
//		String bb = desEncrypt(aa, key);
//		System.out.println(bb);
//		String cc = desDecrypt(bb, key);
//		System.out.println(cc);
		generateRsaKey();

	}


}
