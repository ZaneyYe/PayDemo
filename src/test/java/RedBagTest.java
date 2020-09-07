import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yezhangyuan on 2019-09-11.
 *
 * @author yezhangyuan
 */
public class RedBagTest {

	private static String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCzzsKH+th8VOpPVZjQK5BPE0tjufxhPo296QCyKdFTIR+buJcWVW6aYOtFiTrmc/87deH0fYP1WAt6v4IA6F5DHOvqPKZ0qHjgwTXFEF0gHKVXPaeX3N1Ds6DImGxYnuAq6etwgIIo/lcp/3Tef1W3ywe/6jbYLkHKd89I8X1/4ARxa5kvtrlyMmsjSL6c75aL5toTtbg4/UHELHb8exPTlLC7NOLdmv5P/C7vkBdrirJPK7TvBG4+grHOdhdzjHY8oKujQQ95VFHX7UBxVWK9O2XXyLKD5MS0qCK3OLVj6TdJQLOFX3wfhplrg1kpGPL3NJ3SOZyc0IS7Frk2fFG/AgMBAAECggEBAJ5gmlyoCRPKv/BNVyLVnHDqb9dRtQn6zWLf32sPBoAQhwTk3MAhVCDLCbDgJW9j8Qbw2Kw/+oGaodHm6Z3fD1hSG0LhVUUgOAjQZopD1aM5WDG17u8G7iZ6DD9kctrEZnOmQ2l+6CcvkJs/ljN9OYWAYj0bEl/srqqmSYDYpVCCCZht++Kq+VWTY609JYZcaJ3qt8yKYgTSHwBhO7UVoz4NxcImY2cFXofFjOAT/p5//9SdvOXW9Joz0p7E/i7EIRQk/liOvU6I8KSEt3YsfmZih+FzRoX0lW/R8T+tWpPPA1rV+bxgclp7snSvFTctT+odwT9F7c2UyTLRq0Wc1EECgYEA24zIp/rs0vzSZ+15hFNTPFZXQISgYKQ0a6q3GpmN6Aa+OSbdGL7Fnl6CcMgldRJlFr7Tkr0qQSR4FChXjq5Gb5WrNDxCFn12BqRLwk0CRwzdT7KGzMhuxzkmh61aHH1M/oHUTC/wkRc2G8F6/GCrEt1kZuVGgO/J5GMBtgSuz5ECgYEA0ajd7ENUsKCMaqAo6+IqjXJhyAVEurOERGFjkXyUdQBbKhHBvxSfDRjh4GaUx2TVipW9JDXBZtiHkMSNXPccHAbESG532LhKZhHb0Y4zLOsfrft8YgYXvKCA74ftyiFT+8jBwI8ns6W0B2ocwPr5XGjDGXKd0HlCzWce8iaMBE8CgYEAuwxPq+W5POxO6mJFJDrFcGNzBLG53XwoeQ5B37SxVNvh1tlIhxuJTCri4Ol0GDDq1R0RLjRhuowyjaHtLs9FjS2rZd5sw7JyCqiRLuHjLdtE9fB4riGZV7xWGkPTwO/z1+WOlLZHM6Xd4HnPqRNGzNS4BH4TuigRDrZXL9b/hDECgYEAz7vYv2rrM+Kvnko4oZfC/8lQdyY5Nh/+st4oEJP2aINE0MctSA2/lfOMgpJBYdtX9ettr74Pdb6fe+I0CuPRFzfhLb8+wh2/oaiaO+UAccY56mIemOVeiYgimLL++eLR2szMpOVyuf46BYqvGMrTXity+/jjwxcTCH/fWqC05OMCgYBcwu6XRdHbbWBDtqZQPmeAzGN8SMqD6E2YuGk+6n4Gpit/JWKfcwM1sPXCcf6IZ5Raorb3tmYGUgW+DPGG3xfEPs/gA4y8IfaSPcc+t7K10rvfvgO0r7z2sxEp2/enzwvZxd+NM6QPwFatSWm1x/5KBcc4QCHjackDzZja6tdFyQ==";

	private static String unpublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs87Ch/rYfFTqT1WY0CuQTxNLY7n8YT6NvekAsinRUyEfm7iXFlVummDrRYk65nP/O3Xh9H2D9VgLer+CAOheQxzr6jymdKh44ME1xRBdIBylVz2nl9zdQ7OgyJhsWJ7gKunrcICCKP5XKf903n9Vt8sHv+o22C5BynfPSPF9f+AEcWuZL7a5cjJrI0i+nO+Wi+baE7W4OP1BxCx2/HsT05SwuzTi3Zr+T/wu75AXa4qyTyu07wRuPoKxznYXc4x2PKCro0EPeVRR1+1AcVVivTtl18iyg+TEtKgitzi1Y+k3SUCzhV98H4aZa4NZKRjy9zSd0jmcnNCEuxa5NnxRvwIDAQAB";

	@org.junit.Test
	public void test1() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "d43714e0246a435e87037f80495d2c6d");
		map.put("insAcctId", "T00T00000000888527");
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		System.out.println(uuidStr);

		map.put("transNumber", uuidStr);
		map.put("openId", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
		map.put("mobile", "");
		map.put("pan","");
		map.put("pointAt","3");
		map.put("remark","");

		String str = Utils.createNonceStr();
		map.put("nonceStr", str);
		String date = String.valueOf(System.currentTimeMillis()/1000);
		map.put("timestamp",date);

		String waitToSign = Utils.coverMap2String(map);

		String sign = ShareUserUtil.sign(waitToSign, privateKey);

		boolean rest = ShareUserUtil.signValidate(waitToSign, sign, unpublicKey);

		System.out.println(waitToSign);
		System.out.println(sign);
		System.out.println(rest);

		map.put("signature",sign);
		map.put("pointAt","IBfY7eXXq0I=");


		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/red.packet.give");
		String res = client.sendPost(map);

		System.out.println(res);

	}



	@org.junit.Test
	public void test2() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "d43714e0246a435e87037f80495d2c6d");
		map.put("qualNum", "92853ae5-296b-9e4d-f6af-863f01ffbacb");
		map.put("qualType", "open_id");
		map.put("qualValue", "7uKwZ1ESYacWq+rvfoJ2r9O43iJYCIZjpuqrRcaWSUIoQv81mSFjuJ80sX6k6eVm6/+u1wshTG2rKPaHDhhVRjJl90bu9mc0");
		map.put("backendToken","6p3q3kXRQJiYINZLUAS+1A==");
//		String str = Utils.createNonceStr();
//		map.put("nonceStr", str);
//		String date = String.valueOf(System.currentTimeMillis()/1000);
//		map.put("timestamp",date);
//
//		String waitToSign = Utils.coverMap2String(map);

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/qual.select");
		String res = client.sendPost(map);
		System.out.println(res);
	}


	@org.junit.Test
	public void test3() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "d43714e0246a435e87037f80495d2c6d");
		map.put("qualNum", "92853ae5-296b-9e4d-f6af-863f01ffbacb");
		map.put("qualType", "open_id");
		map.put("qualValue", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
		map.put("count", "3");
		map.put("transNumber", "201909120001");
		map.put("startDate", "20190912");
		map.put("endDate", "20190913");
		String str = Utils.createNonceStr();
		map.put("nonceStr", str);
		String date = String.valueOf(System.currentTimeMillis()/1000);
		map.put("timestamp",date);

		String waitToSign = Utils.coverMap2String(map);

		String sign = ShareUserUtil.sign(waitToSign, privateKey);

		boolean rest = ShareUserUtil.signValidate(waitToSign, sign, unpublicKey);

		System.out.println(waitToSign);
		System.out.println(sign);
		System.out.println(rest);

		map.put("signature",sign);

		map.put("qualValue", "7uKwZ1ESYacWq+rvfoJ2r9O43iJYCIZjpuqrRcaWSUIoQv81mSFjuJ80sX6k6eVm6/+u1wshTG2rKPaHDhhVRjJl90bu9mc0");
		map.put("count", "IBfY7eXXq0I=");

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/qual.send");
		String res = client.sendPost(map);
		System.out.println(res);
	}


	@org.junit.Test
	public void test4() throws Exception {
		Map map = new HashMap<>();
		map.put("appId", "d43714e0246a435e87037f80495d2c6d");
		map.put("activityNumber", "1320190910000368");
		map.put("orderAmount", "2");
		map.put("qualNum", "92853ae5-296b-9e4d-f6af-863f01ffbacb");
		map.put("qualType", "open_id");
		map.put("qualValue", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
		map.put("certId", "");
		map.put("icTerminal", "");
		map.put("qrCode", "");
		map.put("transNumber", "201909120001");
		String str = Utils.createNonceStr();
		map.put("nonceStr", str);
		String date = String.valueOf(System.currentTimeMillis()/1000);
		map.put("timestamp",date);

		String waitToSign = Utils.coverMap2String(map);

		String sign = ShareUserUtil.sign(waitToSign, privateKey);

		boolean rest = ShareUserUtil.signValidate(waitToSign, sign, unpublicKey);

		System.out.println(waitToSign);
		System.out.println(sign);
		System.out.println(rest);

		map.put("signature",sign);

		map.put("qualValue", "7uKwZ1ESYacWq+rvfoJ2r9O43iJYCIZjpuqrRcaWSUIoQv81mSFjuJ80sX6k6eVm6/+u1wshTG2rKPaHDhhVRjJl90bu9mc0");
//		map.put("count", "IBfY7eXXq0I=");

		MyHttpClient client = new MyHttpClient("https://open.95516.com/open/access/1.0/qual.reduce");
		String res = client.sendPost(map);
		System.out.println(res);
	}


}
