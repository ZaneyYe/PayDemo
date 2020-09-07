import org.springframework.util.Base64Utils;
import sdkUtil.SDKConstants;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yezhangyuan on 2018-09-07.
 *
 * @author yezhangyuan
 */
public class RSASign {

	public static final String SIGN_ALGORITHMS = "SHA256WithRSA";

	public static String sign(String value, String privateKey) throws Exception {
		byte[] keyBytes = BytesUtil.hexToBytes(privateKey);
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

	public static boolean signValidate(String value, String sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//		byte[] encodedKey = Base64.decodeBase64(publicKey);
		byte[] encodedKey = Base64Utils.decodeFromString(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initVerify(pubKey);
		signature.update(value.getBytes());
		byte[] bytes = Base64Utils.decodeFromString(publicKey);
		return signature.verify(bytes);
	}

	public static byte[] encrypt(String pkStr, byte[] input) throws Exception {
		byte[] encodedKey = Base64Utils.decodeFromString(pkStr);
		KeySpec keySpec = new X509EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pk = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, pk);
		byte[] result = cipher.doFinal(input);
		return result;
	}

	public static byte[] decrypt(String skStr, byte[] input) throws Exception {
		byte[] encodedKey = Base64Utils.decodeFromString(skStr);
		KeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey sk = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, sk);
		byte[] result = cipher.doFinal(input);
		return result;
	}

	/**
	 * 将Map中的数据转换成key1=value1&key2=value2的形式 不包含签名域signature
	 *
	 * @param data
	 *            待拼接的Map数据
	 * @return 拼接好后的字符串
	 */
	public static String coverMap2String(Map<String, String> data) {
		TreeMap<String, String> tree = new TreeMap<String, String>();
		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			if (SDKConstants.param_signature.equals(en.getKey().trim())) {
				continue;
			}
			tree.put(en.getKey(), en.getValue());
		}
		it = tree.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			sf.append(en.getKey() + SDKConstants.EQUAL + en.getValue()
					+ SDKConstants.AMPERSAND);
		}
		return sf.substring(0, sf.length() - 1);
	}

	public static void main(String[] args) throws Exception {
		Map<String,String> map = new HashMap<>();
		map.put("appId","99eb1213e48e492fb53d176abc1a01ba");
		map.put("trid","62009000052");//s
		map.put("operateTime","2019-08-20 21:26:03");//s
		map.put("openId","vQTHx6g178xWtSArOTYzoo2l8UXRNxEVvtLkONssm11xbno/xO1efg==");//s
		map.put("contractCode","");//s
		map.put("timestamp","1566354978");//s
		map.put("nonceStr","0q47jqojU3YJyQO0");
		map.put("planId","");

		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0QRJ81dxUdJNXoJwx81dvExIWP9zGhVVdYWKgOajcQI/5F1Qt67ipEL+pSh30P9roPBv6LWHb42z/htmPUrKXJ4f/WspXkbfBZsERe8XT8NZRnSdR3iZ9RqJKMzgjOetuoeFzTQ5QBalQKfQN9g58FEY0wrGH8DbrRzRImsnOVl0vvdIrqvTji+vD6GzZ8egSz9HZ0e9fQKG4dI1nuH145OfHY/fNe23oWINbXfFpVWiw+WgTTf8XzjVERD3qAT4i3cwB8RdhNlk3ysW0EJrt2/WOJiI2NNK3xzXohqPYdUDRA4aWbRPtIma5EtBcnLFm76mXwkTlk9PJm7CJA3c2QIDAQAB";
		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println(waitForSign);

		boolean result = signValidate(waitForSign,"DxIk4WFa/UvxCgmedkDohlnBohmjqfg8MLFJMq1bOKg+ncaLB9xf5/Bpd7vmtNEoTtfx7m+cUSM8pNFAGVEaLjZLQ7pzuonoOro8eDzpU4Fm2g2U15D9XkUsabHZYLBi/3TIf2iy3RdxhY3y+Ji94PWMMRFIplQdC6/RHIUViivej4QxIj9D3G7/ECQXMPgfPq7NgN4JMHgNYOrWDMF742aEcL0nsv80aA7E5U075Sec6tqaTz3RSBlEopYjyUhp7f4UYbfb/gREaj0HVsPuuh1KrNBxIal7Do26QG+wOZfdD1Fj6nBzqVbH0QhjDplXeq4668lCVtOJzYECxyPGRA==",publicKey);

	}


}
