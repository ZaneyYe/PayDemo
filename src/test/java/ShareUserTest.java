import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2018-09-27.
 *
 * @author yezhangyuan
 */
public class ShareUserTest {

	//生产测试私钥
	private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIApSXeqcT3pKCBDKGpKqf+ByvrSdwMzDnmUb+6eVsms/GpbIhCHrGr+/fPRuq/COZuEsiNmkjusEeNgJCEoga/AzQ3xDklx6VZ7UDMfOVtK4KRsWHSMfYCtcXm2btppxcM6dOFwimtt1vPptgYfxsbdmtrxJw0ziccj0jAV4wpPAgMBAAECgYAHh+WMRZSv6aJ0+t1GGasRm4Pc5z8dDgP8uu8021MIOMUATuiahg5onyE3EYzhxQzziYGaOO3A2eSXMtAMrr+oCdwN7gqwjShgGkB/2cDvDnJ0wFHntvCYXjp13QEFJ8CO5fkYWLVxFtJ6VrdLUktUvhR+Fw4JLuTho/11lYdhGQJBAMFdi3RD2XEyoAoH4mkZ5siPfyW6gu5qkBBroAb3WJaAYxL0bwRmFYI+Q5YAmjYZwJlnm8AC3bMJREpFslP0NOUCQQCprNCXNQal6XuzyQGngy6eAOVGLKp/inGWyRW/wuFu6TJAGTAonbwTpNfeEfQ3aOJGgt/DHWOfvdVJ9BbraqMjAkEArX/2BRhsHrnCB74TVSK8hPDcsUms+af8I/+t0xJVFpWUUAmrI1NFsVuU4R8hP7HTstHYWm0359FEyS/IVrQkUQJAXaWG3t2iVLHf12OKaTTq5sPhxvBiDdCQTsOfIF5j474LQPtl7BTauBDUH7nTCz31HSugamTvFjxE2vNALyCE9wJAEE6G0W9IZDm6w+5nbiZ2mAhd0VBfMI2apa09/yMQGcqt2974bw/42chPoO9Vcwua+x3LsQ1stxl3+6jADQp7Fw==";
	//pm
//	private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIApSXeqcT3pKCBDKGpKqf+ByvrSdwMzDnmUb+6eVsms/GpbIhCHrGr+/fPRuq/COZuEsiNmkjusEeNgJCEoga/AzQ3xDklx6VZ7UDMfOVtK4KRsWHSMfYCtcXm2btppxcM6dOFwimtt1vPptgYfxsbdmtrxJw0ziccj0jAV4wpPAgMBAAECgYAHh+WMRZSv6aJ0+t1GGasRm4Pc5z8dDgP8uu8021MIOMUATuiahg5onyE3EYzhxQzziYGaOO3A2eSXMtAMrr+oCdwN7gqwjShgGkB/2cDvDnJ0wFHntvCYXjp13QEFJ8CO5fkYWLVxFtJ6VrdLUktUvhR+Fw4JLuTho/11lYdhGQJBAMFdi3RD2XEyoAoH4mkZ5siPfyW6gu5qkBBroAb3WJaAYxL0bwRmFYI+Q5YAmjYZwJlnm8AC3bMJREpFslP0NOUCQQCprNCXNQal6XuzyQGngy6eAOVGLKp/inGWyRW/wuFu6TJAGTAonbwTpNfeEfQ3aOJGgt/DHWOfvdVJ9BbraqMjAkEArX/2BRhsHrnCB74TVSK8hPDcsUms+af8I/+t0xJVFpWUUAmrI1NFsVuU4R8hP7HTstHYWm0359FEyS/IVrQkUQJAXaWG3t2iVLHf12OKaTTq5sPhxvBiDdCQTsOfIF5j474LQPtl7BTauBDUH7nTCz31HSugamTvFjxE2vNALyCE9wJAEE6G0W9IZDm6w+5nbiZ2mAhd0VBfMI2apa09/yMQGcqt2974bw/42chPoO9Vcwua+x3LsQ1stxl3+6jADQp7Fw==";

	//生产银联公钥
//	private static String unpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlCK4TIi4XUijd2bEseATK1FRcypNwaHtXKU9ouV6lzYpxMy1UA1VUTMCfato31VV0Pj+LDFHTm62it05vIKbJ+at4LUE6BjskL5VTnxZXAyGKC4tvWFuUrMJYh2z57AnMtk697OYxJAxD9/il0mCRs2ftqvCM1VM3BlyeCurKJ2ZfGXBD9WujCOWIzbSFrZARkflJ/ZmOfoP7ks2Pi1wmWNHy2Hnc+qx/ohYIf8/t7rfC93nEpTdhO+Nbd9I8otwQz8gpIH5IOEq/8QgfKM86tMIiOdTDiNE+7Pm+7ZVJdZ98RuzcSeyDB42B7kbS8sAoGEyn4WlnPRKSyeIh2+JYQIDAQAB";
	private static String unpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlCK4TIi4XUijd2bEseATK1FRcypNwaHtXKU9ouV6lzYpxMy1UA1VUTMCfato31VV0Pj+LDFHTm62it05vIKbJ+at4LUE6BjskL5VTnxZXAyGKC4tvWFuUrMJYh2z57AnMtk697OYxJAxD9/il0mCRs2ftqvCM1VM3BlyeCurKJ2ZfGXBD9WujCOWIzbSFrZARkflJ/ZmOfoP7ks2Pi1wmWNHy2Hnc+qx/ohYIf8/t7rfC93nEpTdhO+Nbd9I8otwQz8gpIH5IOEq/8QgfKM86tMIiOdTDiNE+7Pm+7ZVJdZ98RuzcSeyDB42B7kbS8sAoGEyn4WlnPRKSyeIh2+JYQIDAQAB";
	//老的pm公钥
	//private static String unpublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLiTzEpxu73u9UlY+BS+jfoX0py7nw2Rd8ynk77syp00rmPRQ2pCYWR2n6UIKid/NYMZhsPCs9thDOXtvXhAQoHlRfNE5nSs3eBY6SI5LCkxq2wFfTOMlxICsbECnvsjawb3g0PVUR6QHDaNXRMb2Yux1tZXjirzq3dlNbjoPXEQIDAQAB";
	//pm新公钥
//	private static String unpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAstprER5yjp65fXYLzoFtQOMs2m8xaE781ChE8gZCr9TqF+KdaAXudeV7Zk8Mv7Riwlwm8yMV+kcA00OamYBgIq5fWC2Gyj3M3SeLD0JolZz/RN0w4np//3hre/CWtiQ5K3dFxrGA2lqKX14MytOy606W+oBXMX5TkO+oKlfYFaZnjjNA7wanHc2aAzxKJ+A0N+qwYcAB6j8vJ9ZNITgOYwrMHtRPpivthy7KxU8fB6cuTGOAdPsPlA6Z849u2scdE80GIanS16tClCszNEN3MAMIOcr77GzFT0bdRFR0ObUH8j/ETuvOP2tQEu09GSZiS5dWBVwZfWculteLdhnXAwIDAQAB";

	private static String findUserUrl = "https://wallet.95516.com/app/access/bank/findUser";
//	private static String findUserUrl = "http://101.231.204.80:8086/app/access/bank/findUser";
//
	//绑定用户的url
//	private static String bindUserUrl = "https://wallet.95516.com/app/access/bank/bindUser";

	private static String bindUserUrl = "http://101.231.204.80:8086/app/access/bank/bindUser";

	private static String quickBindUrl = "http://101.231.204.80:8086/app/access/bank/quickBindCard";

	private static String bindUrl = "http://101.231.204.80:8086/app/access/bank/bindCard";

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		bindUserSend();
//		findUserSend();
//		quickBind();
//		bindCard();
//		String sign = sign();
//		System.out.println(sign);
//		System.out.println(new String("叶子".getBytes(),"UTF-8"));
	}

	public static String sign() throws Exception {
		String sn = "";
		Map<String,String> map = new HashMap<>();
		map.put("appId","yyrEqdswDF");
//		map.put("appId","5949221470");
//		map.put("indUsrId","0010010092");
		map.put("indUsrId","2047980155034");
//		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
		map.put("nonceStr","78TaA2xIMAUDiotK");
		map.put("chnl","1");
		map.put("timestamp",("" + System.currentTimeMillis()/1000));
//		map.put("timestamp",("" + 1583931153));

		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println("待签名：" + waitForSign);

		String sign = ShareUserUtil.sign(waitForSign, privateKey);
		map.put("signature", sign);
		return  sign;
	}



	/**
	 * findUser
	 * @return
	 * @throws Exception
	 */
	public static String findUserSend() throws Exception {
		String sn = "";
		Map<String,String> map = new HashMap<>();
		map.put("appId","yyrEqdswDF");
//		map.put("appId","5949221470");
		map.put("indUsrId","0010010093");
//		map.put("indUsrId","hs1234");
//		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
		map.put("nonceStr","78TaA2xIMAUDiotK");
		map.put("chnl","1");
		map.put("timestamp",("" + System.currentTimeMillis()/1000));
//		map.put("timestamp",("" + 1583931153));

		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println("待签名：" + waitForSign);

		String sign = ShareUserUtil.sign(waitForSign, privateKey);
		map.put("signature", sign);

		sn = sendPost(findUserUrl,map);
		return sn;
	}

	/**
	 * bindUser
	 * @return
	 * @throws Exception
	 */
	public static String bindUserSend() throws Exception {
		String sn = "";
		Map<String,String> map = new HashMap<>();
		map.put("appId","5949221479");
//		map.put("appId","5949221470");
//		map.put("appId","yyrEqdswDF");
		map.put("accType","01");//s
		map.put("cardNo","6222530117166731");//s
		map.put("mobile","18905565507");//s
		map.put("realNm","叶樟源");//s
		map.put("certifId","340825199108241958");//s
		map.put("certType","03");//s
		map.put("indUsrId","0010010093");
		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
		map.put("chnl","1");
		map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));

		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println("待签名：" + waitForSign);

		String sign = ShareUserUtil.sign(waitForSign, privateKey);
		map.put("signature", sign);

		String desKey = ShareUserUtil.get3Des();//3des密钥

		map.put("accType",ShareUserUtil.getEncryptedValue("01",desKey));//s
		map.put("cardNo",ShareUserUtil.getEncryptedValue("6222530117166731",desKey));//s
		map.put("mobile",ShareUserUtil.getEncryptedValue("18905565507",desKey));//s
		map.put("realNm",ShareUserUtil.getEncryptedValue("叶樟源",desKey));//s
		map.put("certifId",ShareUserUtil.getEncryptedValue("340825199108241958",desKey));//s
		map.put("certType",ShareUserUtil.getEncryptedValue("03",desKey));//s

		map.put("symmetricKey", Base64Utils.encodeToString(ShareUserUtil.encryptRSA(unpublicKey,ShareUserUtil.hexToBytes(desKey))));

		sn = sendPost(bindUserUrl,map);

		System.out.println("sn结果："+ sn);

		return sn;
	}


	/**
	 * quickBindCard
	 * @return
	 * @throws Exception
	 */
	public static String quickBind() throws Exception {
		String sn = "";
		Map<String,String> map = new HashMap<>();
		map.put("appId","5949221479");
		map.put("userId","0010010094");
		map.put("realNm","野子什么鬼");//s
		map.put("accType","01");//s
		map.put("cardNo","6201234511110910");//s
		map.put("registerMobile","18905565503");//s
		map.put("reserveMobile","18905565503");
		map.put("certType","03");//s
		map.put("certifId","340825199108119589");//s
		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
//		map.put("chnl","1");
		map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));

		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println("待签名：" + waitForSign);

		String sign = ShareUserUtil.sign(waitForSign, privateKey);
		map.put("signature", sign);

		String desKey = ShareUserUtil.get3Des();//3des密钥

		map.put("accType",ShareUserUtil.getEncryptedValue("01",desKey));//s
		map.put("cardNo",ShareUserUtil.getEncryptedValue("6201234511110910",desKey));//s
		map.put("reserveMobile",ShareUserUtil.getEncryptedValue("18905565503",desKey));//s
		map.put("registerMobile",ShareUserUtil.getEncryptedValue("18905565503",desKey));//s
		map.put("realNm",ShareUserUtil.getEncryptedValue("野子什么鬼",desKey));//s
		map.put("certifId",ShareUserUtil.getEncryptedValue("340825199108119589",desKey));//s
		map.put("certType",ShareUserUtil.getEncryptedValue("03",desKey));//s

		map.put("symmetricKey", Base64Utils.encodeToString(ShareUserUtil.encryptRSA(unpublicKey,ShareUserUtil.hexToBytes(desKey))));

		sn = sendPost(quickBindUrl,map);

		System.out.println("sn结果："+ sn);

		return sn;
	}


	/**
	 * 发送請求
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String targetUrl,Map<String,String> map) throws IOException {
		String sn = "";
		MyHttpClient client = new MyHttpClient(targetUrl);
		String params = objectMapper.writeValueAsString(map);
		System.out.println("请求参数：:" + params);

		String res = client.excuteResult(params);
		System.out.println(res);
		ShareUserResponse shareUserResponse = objectMapper.readValue(res, ShareUserResponse.class);
		if(shareUserResponse.getResp().equals("00")){
			sn = shareUserResponse.getParams().getSn();
		}else{
			System.out.println(shareUserResponse.getMsg());
		}
		return sn;
	}

	public static void bindCard() throws Exception {
		String sn = "";
		Map<String,String> map = new HashMap<>();
		map.put("appId","5949221470");
		map.put("userId","11113333");
		map.put("realNm","张三33");//s
		map.put("accType","01");//s
		map.put("cardNo","6225963369467100");//s
		map.put("registerMobile","18821003131");//s
		map.put("reserveMobile","18821003131");
		map.put("certType","01");//s
		map.put("certifId","532801199508129911");//s
		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
		map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));

		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println("待签名：" + waitForSign);

		String sign = ShareUserUtil.sign(waitForSign, privateKey);
		map.put("signature", sign);

		String desKey = ShareUserUtil.get3Des();//3des密钥

//    desKey = "8f1fb3e620b06d465d79689eb6feda1f8f1fb3e620b06d46";
//    System.out.println(Arrays.toString(ShareUserUtil.hexToBytes(desKey)));//字节数组打印

		System.out.println("3des：" + desKey);

		map.put("accType",ShareUserUtil.getEncryptedValue("01",desKey));//s
		map.put("cardNo",ShareUserUtil.getEncryptedValue("6225963369467100",desKey));//s
		map.put("reserveMobile",ShareUserUtil.getEncryptedValue("18821003131",desKey));//s
		map.put("registerMobile",ShareUserUtil.getEncryptedValue("18821003131",desKey));//s
		map.put("realNm",ShareUserUtil.getEncryptedValue("张三33",desKey));//s
		map.put("certifId",ShareUserUtil.getEncryptedValue("532801199508129911",desKey));//s
		map.put("certType",ShareUserUtil.getEncryptedValue("01",desKey));//s

		map.put("symmetricKey", Base64Utils.encodeToString(ShareUserUtil.encryptRSA(unpublicKey,ShareUserUtil.hexToBytes(desKey))));

		System.out.println("symmetricKey:" + Base64Utils.encodeToString(ShareUserUtil.encryptRSA(unpublicKey,ShareUserUtil.hexToBytes(desKey))));
		sendPost(bindUrl,map);

	}


}
