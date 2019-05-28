import java.util.HashMap;
import java.util.Map;

/**
 * 支付密码验证流水号
 *
 */
public class VerifyPwdSeqId {


	public static void main(String[] args) throws Exception {
		//应用Id
		String appId="fb707ef************************06d85";

		//登录用户Id
		String openId="A+2pMHJdXtQ42kQsuqUyM4qPubW5hrr3ONwj7Dgnu2wCFGcrnYVZiUo5AbUZPnBm";

		//用户登录token
		String accessToken="+LdsFq/qfTKN16DRpGqIYIJMbhNLfDvgxuAOkBTYSWJHvX4jq10EzG2zj/nulhsNCdyrH3nUmOtz1Wa7ne1h93/ehDDglGfaq3SQXZ3lxz8=";

		//backendToken
		String backendToken="m1oE8//bTXuQJhY0CKNQWw==";

		//code
		String code = "9wnnOY6fQy+xFHcrMVCecA==";

		//私钥
		String signKey  = "";

		//调用地址
		String url = "https://open.95516.com/open/access/1.0/verifyPwdSeqId";

		//拼装参数Map
		Map<String, String> params = new HashMap<String, String>(0);
		params.put("appId", appId);
		params.put("openId", openId);

		//终端IP(选填)
		params.put("clientIp", "192.168.8.104");

		//业务操作流水号
		params.put("bizOrderId", "696722484692728");

		//业务类型
		params.put("bizType", "4h");

		//商户号
		params.put("merchantId", "210931734351806");

		//随机字符串
		String nonceStr = Utils.createNonceStr();
		params.put("nonceStr", nonceStr);

		//通知地址
		params.put("notifyUrl", "https://www.xxx.com");

		//时间戳
		String timestamp = String.valueOf(System.currentTimeMillis()/1000);
		params.put("timestamp", timestamp);

		//accessToken
		params.put("accessToken", accessToken);

		String waitForSign = MyHttpClient.coverMap2String(params);

		//签名
		String signature = RSASign.sign(waitForSign, signKey);
		params.put("signature", signature);

		//发送请求
		String resultStr = ShareUserTest.sendPost(url, params);
		System.out.println("-------支付密码验证流水号:"+resultStr+"---------");
	}
}
