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

	private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIApSXeqcT3pKCBDKGpKqf+ByvrSdwMzDnmUb+6eVsms/GpbIhCHrGr+/fPRuq/COZuEsiNmkjusEeNgJCEoga/AzQ3xDklx6VZ7UDMfOVtK4KRsWHSMfYCtcXm2btppxcM6dOFwimtt1vPptgYfxsbdmtrxJw0ziccj0jAV4wpPAgMBAAECgYAHh+WMRZSv6aJ0+t1GGasRm4Pc5z8dDgP8uu8021MIOMUATuiahg5onyE3EYzhxQzziYGaOO3A2eSXMtAMrr+oCdwN7gqwjShgGkB/2cDvDnJ0wFHntvCYXjp13QEFJ8CO5fkYWLVxFtJ6VrdLUktUvhR+Fw4JLuTho/11lYdhGQJBAMFdi3RD2XEyoAoH4mkZ5siPfyW6gu5qkBBroAb3WJaAYxL0bwRmFYI+Q5YAmjYZwJlnm8AC3bMJREpFslP0NOUCQQCprNCXNQal6XuzyQGngy6eAOVGLKp/inGWyRW/wuFu6TJAGTAonbwTpNfeEfQ3aOJGgt/DHWOfvdVJ9BbraqMjAkEArX/2BRhsHrnCB74TVSK8hPDcsUms+af8I/+t0xJVFpWUUAmrI1NFsVuU4R8hP7HTstHYWm0359FEyS/IVrQkUQJAXaWG3t2iVLHf12OKaTTq5sPhxvBiDdCQTsOfIF5j474LQPtl7BTauBDUH7nTCz31HSugamTvFjxE2vNALyCE9wJAEE6G0W9IZDm6w+5nbiZ2mAhd0VBfMI2apa09/yMQGcqt2974bw/42chPoO9Vcwua+x3LsQ1stxl3+6jADQp7Fw==";

	private static String unpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlCK4TIi4XUijd2bEseATK1FRcypNwaHtXKU9ouV6lzYpxMy1UA1VUTMCfato31VV0Pj+LDFHTm62it05vIKbJ+at4LUE6BjskL5VTnxZXAyGKC4tvWFuUrMJYh2z57AnMtk697OYxJAxD9/il0mCRs2ftqvCM1VM3BlyeCurKJ2ZfGXBD9WujCOWIzbSFrZARkflJ/ZmOfoP7ks2Pi1wmWNHy2Hnc+qx/ohYIf8/t7rfC93nEpTdhO+Nbd9I8otwQz8gpIH5IOEq/8QgfKM86tMIiOdTDiNE+7Pm+7ZVJdZ98RuzcSeyDB42B7kbS8sAoGEyn4WlnPRKSyeIh2+JYQIDAQAB";

	private static String findUserUrl = "https://wallet.95516.com/app/access/bank/findUser";

	private static String bindUserUrl = "https://wallet.95516.com/app/access/bank/bindUser";

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		bindUserSend();
//		findUserSend();
	}

	/**
	 * findUser
	 * @return
	 * @throws Exception
	 */
	public static String findUserSend() throws Exception {
		String sn = "";
		Map<String,String> map = new HashMap<>();
		map.put("appId","5949221470");
		map.put("indUsrId","001001003");
		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
		map.put("chnl","1");
		map.put("timestamp",("" + System.currentTimeMillis()/1000));

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
		map.put("appId","5949221470");
		map.put("accType","01");//s
		map.put("cardNo","6277890012345671");//s
		map.put("mobile","18905565503");//s
		map.put("realNm","野子");//s
		map.put("certifId","340825199108241953");//s
		map.put("certType","01");//s
		map.put("indUsrId","001001009");//s
		map.put("nonceStr",ShareUserUtil.createNonceStr(16));
		map.put("chnl","1");
		map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));

		String waitForSign = MyHttpClient.coverMap2String(map);
		System.out.println("待签名：" + waitForSign);

		String sign = ShareUserUtil.sign(waitForSign, privateKey);
		map.put("signature", sign);

		String desKey = ShareUserUtil.get3Des();//3des密钥

		map.put("accType",ShareUserUtil.getEncryptedValue("01",desKey));//s
		map.put("cardNo",ShareUserUtil.getEncryptedValue("6277890012345671",desKey));//s
		map.put("mobile",ShareUserUtil.getEncryptedValue("18905565503",desKey));//s
		map.put("realNm",ShareUserUtil.getEncryptedValue("野子",desKey));//s
		map.put("certifId",ShareUserUtil.getEncryptedValue("340825199108241953",desKey));//s
		map.put("certType",ShareUserUtil.getEncryptedValue("01",desKey));//s

		map.put("symmetricKey", Base64Utils.encodeToString(ShareUserUtil.encryptRSA(unpublicKey,ShareUserUtil.hexToBytes(desKey))));

		sn = sendPost(bindUserUrl,map);

		System.out.println("sn结果："+ sn);

		return sn;
	}

	/**
	 * 發送請求
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





}
