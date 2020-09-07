import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yezhangyuan on 2019-07-05.
 *
 * @author yezhangyuan
 */
public class OpenIdEncry {

	private static final Logger logger = LoggerFactory.getLogger(OpenIdEncry.class);

	private static byte[] ivBytes = new byte[]{0x00, 0x7F, 0x00, 0x7F, 0x00, 0x7F, 0x00, 0x7F};

	public static String getOpenId(String appId, String userId, String key) {
		String data = userId + "@" + appId;
		logger.info("key:" + key);
		return Base64Utils.encodeToString(encrypt(data.getBytes(), BytesUtil.hexToBytes(key)));
	}

	private static byte[] encrypt(byte[] input, byte[] keyBytes) {
		try {
			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			IvParameterSpec ivps = new IvParameterSpec(ivBytes);
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "DESede"), ivps);
			return c.doFinal(input);
		} catch (Exception ex) {
			logger.error("Never happen. ", ex);
			return null;
		}
	}


	public static String getUserIdByOpenId(String openId, String key) {
		byte[] b = decrypt(Base64Utils.decodeFromString(openId), BytesUtil.hexToBytes(key));
		if (b == null) {
			return null;
		}
		String data = new String(b);
		if (!data.matches("c\\d{11}@[0-9a-zA-Z+/=]+")) {
			return null;
		}
		return data.substring(0, data.lastIndexOf("@"));
	}

	private static byte[] decrypt(byte[] input, byte[] keyBytes) {
		try {
			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			IvParameterSpec ivps = new IvParameterSpec(ivBytes);
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "DESede"), ivps);
			return c.doFinal(input);
		} catch (Exception ex) {
			logger.error("Never happen. ", ex);
			return null;
		}
	}


	public static void main(String[] args){
		String openIdKey = "c1269168ea737f07d0bc9d29a2649d37c1269168ea737f07";
//		System.out.println(getUserIdByOpenId("fv3iUy4ROLgufa3pjJf6+MzlFkWD5+jIxyeeg1UEGdOEmfRB8cS3oZyraVjyb+k9",openIdKey));
		System.out.println(getUserIdByOpenId("ZCvnOrSjX26ZYFOmJaJommzt+Qzk6RM4t7yfmxjVimS49Ut461orhMi+NaAlHTPQ","29c43ebc7938d6797f9123b029cd3d9429c43ebc7938d679"));

//		System.out.println(getUserIdByOpenId("u7pFXAyS6CI3ILYTB0WGPBrrVPMCk/oOq7GraGwi1RHV/QmFrI44GcGoeddN37NR","7652b64c1f98a70285e67fe6c72fbae57652b64c1f98a702"));

//		System.out.println(getOpenId("fd6505537cfd4efd84414c38e8072ef2","c00100262364","dc9b019161ef70673ec4ea1680344c32dc9b019161ef7067"));

	}

}
