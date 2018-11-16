import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yezhangyuan on 2018-04-23.
 *
 * @author yezhangyuan
 */
public class CryptTest {

	//用于将返回字段的值进行3DES解密
	public static String getDecryptedValue(String value, String key) throws Exception {
		if (null == value || "".equals(value)) {
			return "";
		}
		byte[] valueByte = Base64.decode(value);
		byte[] sl = decrypt3DES(valueByte, hexToBytes(key));
		String result = new String(sl);
		return result;
	}

	public static byte[] decrypt3DES(byte[] input, byte[] key) throws Exception {
		Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
		return c.doFinal(input);
	}

	public static byte[] encode3Des(byte[] input, byte[] key) throws Exception {
		Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
		return c.doFinal(input);
	}

	public static String encodeKey(String value,String key) throws Exception {
		if(StringUtils.isBlank(value)){
			return "";
		}
		byte[] s1 = encode3Des(value.getBytes(),hexToBytes(key));
		String res = Base64Utils.encodeToString(s1);
		return res;
	}


	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

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


	public static void main(String[] args) throws Exception {
//		String value = encodeKey("abcABDddd123456", "23f297855110975e8a3ece3dab8f19d323f297855110975e");
		String key = getDecryptedValue("n8vxLUGCJt+RXf3kWRnmag==", "23f297855110975e8a3ece3dab8f19d323f297855110975e");

//		String v1 = getDecryptedValue("9vKtgD7Bqic=","")
		System.out.println(key);
	}




}
