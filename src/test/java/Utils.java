import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by yezhangyuan on 2018-05-14.
 *
 * @author yezhangyuan
 */
public class Utils
{

	public static String sha256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return bytesToHex(md.digest(data));
		} catch (Exception ex) {
//			logger.info("Never happen.", ex);
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

	public static String createNonceStr() {
			StringBuilder sb = new StringBuilder();
			String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
			int max = temp.length();
			Random rand = new Random();
			for (int i = 0; i < 16; i++) {
				int start = rand.nextInt(max);
				sb.append(temp.substring(start, start + 1));
			}
			return sb.toString();
		}

	public static void main(String[] args){
////		String str2 = "appId=2ad1a3275c4c413f8d603e4be7886785&frontToken=e3bUKdGhQZaPpy1mYHzpSw==&nonceStr=MjZRp8jFOL5E72Oj&timestamp=1532328524&url=https://coupon.yuelai.club";
////		String ss = "appId=80af75c1ea5748408db9e922bfb748db&nonceStr=ASWQWSQWQXQqwersd&secret=1ffb2075eefc4530a03bf1d04285d027&timestamp=1535356100";
//
////		String ss = "appId=6f5e05407c7d4ad88e28b3cf32352207&frontToken=Wo/4vYjITxmh35BzWdGg4w==&nonceStr=fQ2IJsnJsGA4vpwn&timestamp=1535446600&url=https://upw-dev.axinfu.com/fee/feeInfoSure";
//////		String s3 = "appId=a5949221470c4059b9b0b45a90c81527&frontToken=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&nonceStr=Wm3WZYTPz0wzccnW&"+
//////				"timestamp=1414587457&url=http://mobile.xxx.com?params=value";
		String ss = "appId=7c23a9cc21a043028c6cc945dcf01492&frontToken=J/T6IjYyQli3lkYUxR+6IA==&nonceStr=H45DIbcVLmDSv4oT&timestamp=1550510149&url=https://unionpay.ylzcf.com/finance/singNetCreditCeShi?code=mxQwTLs/SheXm8YttdFE3g==&state=up";
		System.out.println(sha256(ss.getBytes()));
//		System.out.println(sha256("5258".getBytes()));
		System.out.println(System.currentTimeMillis()/1000);
		String timestamp = Long.toString(System.currentTimeMillis()).substring(0,10);
		System.out.println(timestamp);

//
//////		System.out.println(sha256(s3.getBytes()));
////		ObjectMapper objectMapper = new ObjectMapper();
////		try {
////			ShareUserResponse response = objectMapper.readValue("{\"resp\":\"00\",\"msg\":\"success\",\"params\":{\"sn\":\"2a04530cf672434f92661715533b226a\"}}", ShareUserResponse.class);
////			System.out.println(response.getParams().getSn());
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//		System.out.println(createNonceStr());
//
//
	}


}
