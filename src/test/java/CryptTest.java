import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Base64;

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
		// byte[] valueByte = BytesUtil.hexToBytes(value);
		byte[] valueByte = Base64.decode(value);
		byte[] sl = decrypt3DES(valueByte, ByteUtils.fromHexString(key));
		String result = new String(sl);
		return result;
	}

	public static byte[] decrypt3DES(byte[] input, byte[] key) throws Exception {
		Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
		return c.doFinal(input);
	}


	public static void main(String[] args) throws Exception {
//		String r1 = "a8dc3bc7ea9b4abfbc7f8ff7c1adb049a8dc3bc7ea9b4abf";
		String r1 = "4fbc2c0732a84c23fee901b0b3920ec24fbc2c0732a84c23";
//		String ori = "JAGn6kdNEd+2AgE9OZ/tWA==";
		String ori = "fHBQyB7I8WBGNdnhdesEjg==";
		System.out.println(getDecryptedValue(ori, r1));

		String s1 = "abc";
		String s2 = "a" + "b" + "c";
		String a = "a";
		String s3 = a + "b" + "c";
		System.out.println(s1 == s2);
//		System.out.println(s1 == s3);
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("a").append("b").append("c");
//		System.out.println(s1 == sb.toString());
//
//		StringBuffer sb2 = new StringBuffer();
//		sb2.append("a").append("b").append("c");
//		System.out.println(s1 == sb2.toString());


		String s4 = " abc";
		System.out.println(s1 == s4);

		String c1 = "中国";
		String c2 = "中国";
		String c3 = new String("中国");
		System.out.println(c1 == c2);  //true
		System.out.println(c1 == c3); //false
		System.out.println(c1 == c3.intern()); //true

		System.out.println(System.currentTimeMillis()/1000);

	}



}
