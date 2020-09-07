import org.apache.commons.lang3.StringUtils;
import sdkUtil.SDKConstants;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

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
			if (SDKConstants.param_signature.equals(en.getKey().trim()) || StringUtils.isBlank(en.getValue())){
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




	public static void main(String[] args){
////		String str2 = "appId=2ad1a3275c4c413f8d603e4be7886785&frontToken=e3bUKdGhQZaPpy1mYHzpSw==&nonceStr=MjZRp8jFOL5E72Oj&timestamp=1532328524&url=https://coupon.yuelai.club";
////		String ss = "appId=80af75c1ea5748408db9e922bfb748db&nonceStr=ASWQWSQWQXQqwersd&secret=1ffb2075eefc4530a03bf1d04285d027&timestamp=1535356100";
//
////		String ss = "appId=6f5e05407c7d4ad88e28b3cf32352207&frontToken=Wo/4vYjITxmh35BzWdGg4w==&nonceStr=fQ2IJsnJsGA4vpwn&timestamp=1535446600&url=https://upw-dev.axinfu.com/fee/feeInfoSure";
//////		String s3 = "appId=a5949221470c4059b9b0b45a90c81527&frontToken=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&nonceStr=Wm3WZYTPz0wzccnW&"+
//////				"timestamp=1414587457&url=http://mobile.xxx.com?params=value";
		String ss = "appId=97f61a4806e948fa81322c0681cd63d6&nonceStr=cbS4zbu1RqD1kNCg&secret=46119c9674354f218479721eed504b1e&timestamp=1566786821";
		System.out.println(sha256(ss.getBytes()));
////		System.out.println(sha256("5258".getBytes()));
//		System.out.println(System.currentTimeMillis()/1000);
//		String timestamp = Long.toString(System.currentTimeMillis()).substring(0,10);
//		System.out.println(timestamp);

//		System.out.println(Base64Utils.decodeFromString("v4J9hr5xKf7paZeUGuxv9PF2utPyPodc/3ZBhPgIB78l9DWLzPqUWQ="));

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
