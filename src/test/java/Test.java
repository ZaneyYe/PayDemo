import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yezhangyuan on 2018-04-23.
 *
 * @author yezhangyuan
 */
public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
//		String reqUrl = "https://open.95516.com/open/access/1.0/backendToken";
//		String encoding = "UTF-8";
//		Map<String, Object> data = new HashMap<>();
//		long millis = System.currentTimeMillis();
////		String randomStr = createNonceStr(16);
//
//		data.put("appId", "06bdeffec7844f748797dc5ed7501f93");
////		data.put("appId", "826a5f8484fc4854a2918ba7f86fb11a");
////		data.put("secret", "388f9cb4a0df474883a32bec19da747f");
//		data.put("secret", "c05572c28330487199c3677ad940d6e7");
//		data.put("nonceStr", "e80bff971b8641a481bdfae69f93b349");
//		data.put("timestamp", "1524454508");
//		// 设置签名域值
//		data.put("signature", "c0f9f35c8e2ddbac4805f8da28a690c1c2e7fe559ee9bc0d549ee4421f1ec237");
//		HTTPLongClient4 client4 = new HTTPLongClient4();
//		String result = client4.post(reqUrl, data);
//		System.out.println(result);

//		URLEncoder encoder = new URLEncoder();


		String r1 = "5btest1a2b3c4d5f7f738xmdifoo985674195b97f28c761f";
		String ori = "abcABDddd123456";
		String vlu = encodeKey(ori,r1);
		System.out.println(vlu);


	}

	/**
	 45      * 解密函数
	 46      * @param src 密文的字节数组
	 47      * @return
	 48      */
	  //定义加密算法，有DES、DESede(即3DES)、Blowfish
	 private static final String Algorithm = "DESede";
	 private static final String PASSWORD_CRYPT_KEY = "75b6d9ba02da6db30219cd1a4007efa175b6d9ba02da6db3";



	public static byte[] decryptMode(byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(ByteUtils.fromHexString(PASSWORD_CRYPT_KEY), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);    //初始化为解密模式
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static byte[] encodeMode(byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(ByteUtils.fromHexString(PASSWORD_CRYPT_KEY), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);    //初始化为解密模式
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}



	public static String encodeKey(String value,String key) throws UnsupportedEncodingException {
		if(StringUtils.isBlank(value)){
			return "";
		}
		byte[] s1 = encode3Des(value.getBytes(),ByteUtils.fromHexString(key));
		String res = Base64Utils.encodeToString(s1);
		return res;
	}


	private static byte[] encode3Des(byte[] input, byte[] key) throws UnsupportedEncodingException {
		try {
			SecretKey deskey = new SecretKeySpec(key, Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);    //初始化为加密模式
			return c1.doFinal(input);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}


	private static byte[] decrypt3DES(byte[] input, byte[] key) throws UnsupportedEncodingException {
		try {
			SecretKey deskey = new SecretKeySpec(key, Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);    //初始化为解密模式
			return c1.doFinal(input);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 根据字符串生成密钥字节数组
	 * @param keyStr 密钥字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	  public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
		  byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
		  byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组
		  /*
          * 执行数组拷贝
          * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
	     if(key.length > temp.length){
	             //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
	             System.arraycopy(temp, 0, key, 0, temp.length);
	         }else{
	             //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
	             System.arraycopy(temp, 0, key, 0, key.length);
	         }
	     return key;
	 }

	/*
* 根据字符串生成密钥字节数组
* @param keyStr 密钥字符串
* @return
* @throws UnsupportedEncodingException
*/
	public static byte[] build3DesKey(byte[] temp) throws UnsupportedEncodingException {
		byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
		  /*
          * 执行数组拷贝
          * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
		if(key.length > temp.length){
			//如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		}else{
			//如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}


}