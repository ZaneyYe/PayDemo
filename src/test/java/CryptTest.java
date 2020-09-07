import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
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
		Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
//		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
		byte[] ivBytes = new byte[]{0x00, 0x7F, 0x00, 0x7F, 0x00, 0x7F, 0x00, 0x7F};
		IvParameterSpec ivps = new IvParameterSpec(ivBytes);
		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"), ivps);
		return c.doFinal(input);
	}

//	byte[] b = decrypt(Base64Utils.decodeFromString(openId), BytesUtil.hexToBytes(key));
//		if (b == null) {
//		return null;
//	}
//	String data = new String(b);

	//3DES加密
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
//		String value = encodeKey("15918597871", "2c97d9fd3d8cbc3b1089cd15d63bf4a22c97d9fd3d8cbc3b");
//		System.out.println(value);
//		String v1 = encodeKey("fgId+boSsBjN40ISNi+CU4Iygh+su1Y/Zy7lH0NmzVk5KqnR6uj/RKE0HuftItxQ", "cbbf76f7eca764ef9467f1761a023da4cbbf76f7eca764ef");
//		System.out.println("openId: " + v1);
//		String v2 = encodeKey("6212261001037336119", "cbbf76f7eca764ef9467f1761a023da4cbbf76f7eca764ef");
//		System.out.println("cardNo: " + v2);
//		String v3 = encodeKey("18821263670", "cbbf76f7eca764ef9467f1761a023da4cbbf76f7eca764ef");
//		System.out.println("mobile: " + v3);
//		String v4 = encodeKey("321323198801292369", "cbbf76f7eca764ef9467f1761a023da4cbbf76f7eca764ef");
//		System.out.println("IDCard: " + v4);
//		String v5 = encodeKey("3", "cbbf76f7eca764ef9467f1761a023da4cbbf76f7eca764ef");
//		System.out.println("money(3): " + v5);


//		String v1 = encodeKey("", "54ec5768768032cead37ce8ff713a84554ec5768768032ce");
//		System.out.println("openId: " + v1);
//		String v2 = encodeKey("6214851212363951", "54ec5768768032cead37ce8ff713a84554ec5768768032ce");
//		System.out.println("cardNo: " + v2);
		String v3 = encodeKey("18905565507", "54ec5768768032cead37ce8ff713a84554ec5768768032ce");
		System.out.println("mobile: " + v3);
		String v4 = encodeKey("340825199108241958", "54ec5768768032cead37ce8ff713a84554ec5768768032ce");
		System.out.println("IDCard: " + v4);


		String v5 = encodeKey("6214851215798427", "54ec5768768032cead37ce8ff713a84554ec5768768032ce");
		System.out.println("cardNo: " + v5);

//		System.out.println(getDecryptedValue("ZCvnOrSjX26ZYFOmJaJommzt+Qzk6RM4t7yfmxjVimS49Ut461orhMi+NaAlHTPQ","29c43ebc7938d6797f9123b029cd3d9429c43ebc7938d679"));
//		System.out.println(getDecryptedValue("WJZv85tbk1U=","0e1c7fda1392100783a2c41fe0ba4fbf0e1c7fda13921007"));
//		System.out.println(getDecryptedValue("YF4ws9dTmCrZhhtTBjNgCYxQpxxoO9IG","0e1c7fda1392100783a2c41fe0ba4fbf0e1c7fda13921007"));


	}




}
