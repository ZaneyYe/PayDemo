import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yezhangyuan on 2020-06-11.
 *
 * @author yezhangyuan
 */
public class RegBagTest2020 {

	private static String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCzzsKH+th8VOpPVZjQK5BPE0tjufxhPo296QCyKdFTIR+buJcWVW6aYOtFiTrmc/87deH0fYP1WAt6v4IA6F5DHOvqPKZ0qHjgwTXFEF0gHKVXPaeX3N1Ds6DImGxYnuAq6etwgIIo/lcp/3Tef1W3ywe/6jbYLkHKd89I8X1/4ARxa5kvtrlyMmsjSL6c75aL5toTtbg4/UHELHb8exPTlLC7NOLdmv5P/C7vkBdrirJPK7TvBG4+grHOdhdzjHY8oKujQQ95VFHX7UBxVWK9O2XXyLKD5MS0qCK3OLVj6TdJQLOFX3wfhplrg1kpGPL3NJ3SOZyc0IS7Frk2fFG/AgMBAAECggEBAJ5gmlyoCRPKv/BNVyLVnHDqb9dRtQn6zWLf32sPBoAQhwTk3MAhVCDLCbDgJW9j8Qbw2Kw/+oGaodHm6Z3fD1hSG0LhVUUgOAjQZopD1aM5WDG17u8G7iZ6DD9kctrEZnOmQ2l+6CcvkJs/ljN9OYWAYj0bEl/srqqmSYDYpVCCCZht++Kq+VWTY609JYZcaJ3qt8yKYgTSHwBhO7UVoz4NxcImY2cFXofFjOAT/p5//9SdvOXW9Joz0p7E/i7EIRQk/liOvU6I8KSEt3YsfmZih+FzRoX0lW/R8T+tWpPPA1rV+bxgclp7snSvFTctT+odwT9F7c2UyTLRq0Wc1EECgYEA24zIp/rs0vzSZ+15hFNTPFZXQISgYKQ0a6q3GpmN6Aa+OSbdGL7Fnl6CcMgldRJlFr7Tkr0qQSR4FChXjq5Gb5WrNDxCFn12BqRLwk0CRwzdT7KGzMhuxzkmh61aHH1M/oHUTC/wkRc2G8F6/GCrEt1kZuVGgO/J5GMBtgSuz5ECgYEA0ajd7ENUsKCMaqAo6+IqjXJhyAVEurOERGFjkXyUdQBbKhHBvxSfDRjh4GaUx2TVipW9JDXBZtiHkMSNXPccHAbESG532LhKZhHb0Y4zLOsfrft8YgYXvKCA74ftyiFT+8jBwI8ns6W0B2ocwPr5XGjDGXKd0HlCzWce8iaMBE8CgYEAuwxPq+W5POxO6mJFJDrFcGNzBLG53XwoeQ5B37SxVNvh1tlIhxuJTCri4Ol0GDDq1R0RLjRhuowyjaHtLs9FjS2rZd5sw7JyCqiRLuHjLdtE9fB4riGZV7xWGkPTwO/z1+WOlLZHM6Xd4HnPqRNGzNS4BH4TuigRDrZXL9b/hDECgYEAz7vYv2rrM+Kvnko4oZfC/8lQdyY5Nh/+st4oEJP2aINE0MctSA2/lfOMgpJBYdtX9ettr74Pdb6fe+I0CuPRFzfhLb8+wh2/oaiaO+UAccY56mIemOVeiYgimLL++eLR2szMpOVyuf46BYqvGMrTXity+/jjwxcTCH/fWqC05OMCgYBcwu6XRdHbbWBDtqZQPmeAzGN8SMqD6E2YuGk+6n4Gpit/JWKfcwM1sPXCcf6IZ5Raorb3tmYGUgW+DPGG3xfEPs/gA4y8IfaSPcc+t7K10rvfvgO0r7z2sxEp2/enzwvZxd+NM6QPwFatSWm1x/5KBcc4QCHjackDzZja6tdFyQ==";

	private static String unpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs87Ch/rYfFTqT1WY0CuQTxNLY7n8YT6NvekAsinRUyEfm7iXFlVummDrRYk65nP/O3Xh9H2D9VgLer+CAOheQxzr6jymdKh44ME1xRBdIBylVz2nl9zdQ7OgyJhsWJ7gKunrcICCKP5XKf903n9Vt8sHv+o22C5BynfPSPF9f+AEcWuZL7a5cjJrI0i+nO+Wi+baE7W4OP1BxCx2/HsT05SwuzTi3Zr+T/wu75AXa4qyTyu07wRuPoKxznYXc4x2PKCro0EPeVRR1+1AcVVivTtl18iyg+TEtKgitzi1Y+k3SUCzhV98H4aZa4NZKRjy9zSd0jmcnNCEuxa5NnxRvwIDAQAB";

	/**
	 * 1 积分赠送接口
	 */
	@org.junit.Test
	public void jiFenSend() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "xxx");
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);
		map.put("transSeqId", uuidStr);
		String str = Utils.createNonceStr();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(df.format(new Date()));
		map.put("transTs",df.format(new Date()));
		map.put("nonceStr", str);
		//机构代码
		map.put("insAcctId", "P200331213460228"); //T00T00000000888527
		//赠送因子
//		map.put("openId", "2X/rjxxiADyrD?xLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
		map.put("mobile", "18905565507");//18905565507  DjVfXYrJBBmcs3SAWG/i5A==
//		map.put("cardNo","6226091211291863"); //6214851215798427  eoIV84zl6yD+EAXUbxJnUTJl90bu9mc0

		//账户主体类型，2位，可选：01-手机号 02-卡号 03-用户（三选一）
		map.put("acctEntityTp","01");
		//积分id
		map.put("pointId","4120030617124119");//4120030617124119 4120022416275888
		//积分数额
		map.put("pointAt","2");
		//有效起始结束时间
		map.put("validBeginTs","");
		map.put("validEndTs","");
		//业务信息
		String str1 = "{\"campaignId\":\"001\",\"campaignName\":\"云闪付测试009\"}";
		map.put("busiInfo",str1);
		map.put("transDigest","云闪付积分赠送-手机号");
		map.put("remark","积分赠送001");
		String date = String.valueOf(System.currentTimeMillis()/1000);
		map.put("timestamp",date);

		String waitToSign = Utils.coverMap2String(map);
		System.out.println(waitToSign);


		String sign = ShareUserUtil.sign(waitToSign, privateKey);
		map.put("signature",sign);

		boolean rest = ShareUserUtil.signValidate(waitToSign, sign, unpublicKey);
		System.out.println("自验签结果：" +  rest);

		//积分金额
//		map.put("pointAt","IBfY7eXXq0I=");
		map.put("mobile", CryptTest.encodeKey("18905565507","54ec5768768032cead37ce8ff713a84554ec5768768032ce"));
//		map.put("cardNo", CryptTest.encodeKey("6226091211291863","54ec5768768032cead37ce8ff713a84554ec5768768032ce"));

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/point.acquire");
		String res = client.sendPost(map);

		System.out.println(res);

	}


	/**
	 * 2 直接抽奖接口
	 */
	@org.junit.Test
	public void draw() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "xxx");
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);
		map.put("transSeqId", uuidStr);
		String str = Utils.createNonceStr();
		map.put("nonceStr", str);

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		map.put("transTs",df.format(new Date()));
		//订单金额
		map.put("orderAt", "");
		//赠送因子
//		map.put("openId", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
//		map.put("mobile", "18905565507");
		map.put("cardNo","6214851215798427");
		//赠送维度
		map.put("acctEntityTp","02");
//		map.put("acctEntityTp","01");
		//设备号
		map.put("icTmn","");
		//证件号可选
		map.put("certId","");
		//抽奖活动编号
		//抽票券（用户、卡维度） 1320200611276954
		//抽积分（用户维度） 1320200611276965
//		map.put("activityNo","1320200611276954");
		map.put("activityNo","1320200613278135");
		//活动码，非必填
		map.put("qrCode","");
		String date = String.valueOf(System.currentTimeMillis()/1000);
		map.put("timestamp",date);

		String waitToSign = Utils.coverMap2String(map);

		String sign = ShareUserUtil.sign(waitToSign, privateKey);
		map.put("signature",sign);

		boolean rest = ShareUserUtil.signValidate(waitToSign, sign, unpublicKey);
		System.out.println("自验签结果：" +  rest);


//		map.put("pointAt","IBfY7eXXq0I=");
//		map.put("mobile", CryptTest.encodeKey("18905565507","54ec5768768032cead37ce8ff713a84554ec5768768032ce"));
		map.put("cardNo", CryptTest.encodeKey("6214851215798427","54ec5768768032cead37ce8ff713a84554ec5768768032ce"));

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/maktg.draw");
		String res = client.sendPost(map);
		System.out.println(res);
	}


	/**
	 * 3 直接抽奖结果查询
	 */
	@org.junit.Test
	public void drawQuery() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "xxx");
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);
		map.put("transSeqId", uuidStr);

		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		map.put("transTs",df.format(new Date()));

		map.put("transSeqId", "09e9f57ac0794823ae008d6fec48624f");
		map.put("transTs","20200612");

		//后台令牌
		map.put("backendToken","9lruCSnaRf2kWf5EEHnDOQ==");

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/maktg.queryDraw");
		String res = client.sendPost(map);
		System.out.println(res);

	}


	/**
	 * 4 直接赠送优惠券
	 */
	@org.junit.Test
	public void couponDownload() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "d43714e0246a435e87037f80495d2c6d");
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);
		map.put("transSeqId", uuidStr);

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		map.put("transTs",df.format(new Date()));

		//优惠券id
		//卡维度券：3102020061121243
		//用户维度券：3102020061121234
		map.put("couponId", "3102020061121234"); //用户维度券：3102020061121234
		//赠送因子
//		map.put("openId", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
		map.put("mobile", "18905565507");
//		map.put("cardNo","6214851215798427");
		//赠送维度
		map.put("acctEntityTp","03");
		//优惠券数量
		map.put("couponNum", "1");
		String str = Utils.createNonceStr();
		map.put("nonceStr", str);
		//时间戳
		String date = String.valueOf(System.currentTimeMillis()/1000);
		map.put("timestamp",date);

		String waitToSign = Utils.coverMap2String(map);

		String sign = ShareUserUtil.sign(waitToSign, privateKey);
		map.put("signature",sign);

		boolean rest = ShareUserUtil.signValidate(waitToSign, sign, unpublicKey);
		System.out.println("自验签结果：" +  rest);

		map.put("mobile", CryptTest.encodeKey("18905565507","54ec5768768032cead37ce8ff713a84554ec5768768032ce"));
//		map.put("cardNo", CryptTest.encodeKey("6214851215798427","54ec5768768032cead37ce8ff713a84554ec5768768032ce"));

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/coupon.download");
		String res = client.sendPost(map);
		System.out.println(res);
	}

	/**
	 * 5 直接赠送优惠券结果查询
	 */
	@org.junit.Test
	public void couponDownQuery() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "");
		//原交易流水号
		map.put("origTransSeqId", "");
		//原请求日期
		map.put("origTransTs","20200613");

		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);
		map.put("transSeqId", uuidStr);

		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		map.put("transTs",df.format(new Date()));

		map.put("backendToken","9lruCSnaRf2kWf5EEHnDOQ==");

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/coupon.query");
		String res = client.sendPost(map);
		System.out.println(res);
	}


	/**
	 *  6 优惠券活动剩余名额查询
	 */
	@org.junit.Test
	public void activityQuery() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "xxxx");
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);
		map.put("transSeqId", uuidStr);

		//活动编号
		map.put("activityNo","");//3102020061121243
		//活动类型
		map.put("activityType","11");

		map.put("backendToken","mg3/e5rEQWKE0a0rCF+S7A==");
		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/activity.quota");
		String res = client.sendPost(map);
		System.out.println(res);
	}



	/**
	 *  7 用户状态查询
	 */
	@org.junit.Test
	public void userStateQuery() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "");
		map.put("openId", "");
		//标记时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("makeTime",df.format(new Date()));
		map.put("backendToken","mg3/e5rEQWKE0a0rCF+S7A==");
		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/user.status");
		String res = client.sendPost(map);
		System.out.println(res);
	}







}
