import java.security.MessageDigest;

/**
 * Created by yezhangyuan on 2018-12-24.
 *
 * @author yezhangyuan
 */
public class Sha256Utils {

	public static String sha256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return bytesToHex(md.digest(data));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
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


	public static void main(String[] args){
		String str = "appId=22f781d6d85947618c93a5a75e3a51a1&frontToken=TiX0h/O3T1WQRjpug2mHbg==&nonceStr=kr3rdQmrocJk9RBm&timestamp=1578035487&url=http://alywechat.imcoming.com/payment";
		System.out.println(str);

//		String str = "appId=e76cc2cb68ee4aadafc265c0608813dd&frontToken=aPw/KWoHSoKgg2xoKO+LxQ==&nonceStr=Mqxsk1qjGj9S5SQD&timestamp=1575337561&url=http://h5.ehsry.cn/h5/pages/index/index";
//		String str = "1234";

		System.out.println(sha256(str.getBytes()));
	}

}
