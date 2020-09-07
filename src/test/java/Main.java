import org.springframework.util.Base64Utils;

/**
 * Created by yezhangyuan on 2018-05-11.
 *
 * @author yezhangyuan
 */
public class Main {

	public static void main(String[] args) throws Exception {
//
//		URLDecoder  decoder = new URLDecoder();
//		long time3 = 1527393533000l + 5000 * 1000;
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
//		Date date2 = new Date();
//		date2.setTime(time3);
//		System.out.println(simpleDateFormat.format(date2));
//		HTTPLongClient4 client4 = new HTTPLongClient4(1000);
//		Map<String,Object> map = new HashMap<>();
//		map.put("k1","v2");
//		String s = client4.post("https://coupon.yuelai.club/unionpaycallback.do", map);
//		System.out.println(s);

//		System.out.println("gOUHCNjESCypYiB6TRCqoQ==".length());
//		String str = "pAT+dGVP9zWcqhsplVfCvmY3vKjXYOffwuuWgloL/0czSxlvDbdmzd0ok0v8GQ5B5nTJzuUzJ5Dh1nBZpscIdR8VaJSUhjg6zW/3rBtzjgVqAokde6/dVxTVGG6C2kMS7BJnnLoz6EeG4W3JlFZ4L8q3Gno8kRFHHB7jsQDiY9w8lq82Ef/Zrg/A7cPskoc31D8QwlniAXWdMpmrXEAodqG1vxTvwGpRIlbxSumrhfB6dMytuanTT9GE5xTdPe0xrjdoo4F1NEQ+SiZwVmI4Vl3p0XyJCei4+J1WZrE7Vss86Q+VxKbkk0/68XaR4LIPDMvMlmxUW/Q9SQ/1hlVYdw==";
//		byte[] bytes = Base64Utils.decodeFromString(str);
//		String toHex = Utils.bytesToHex(bytes);
//		System.out.println(toHex.toUpperCase());


//		String str2 = "Cbv1Pv4sLL5l8TvbY8brdXd4801ufJTwpQQppMK9cQ7Ls+DNpgZlyWrbZxerkMLZLL+ugyBnpCKAffIryuCEVYnoU/z7qHvS15gcvAuMZgILiuOOCD0zykQ/esLr1bzdhJhkXWqm9QvoXrxEMrXX5zjv9Mldq0NtBNPZ0Igc8/M=";
//		System.out.println(str2.length());
//		byte[] bss = Base64Utils.decodeFromString(str2);
//		System.out.println(bss.length);


//		String des = ShareUserUtil.get3Des();
//		String str = "china";
//		System.out.println(str.getBytes().length);
//		String encryptedValue = ShareUserUtil.getEncryptedValue(str, des);
//		System.out.println(encryptedValue.getBytes().length);


//		String url = URLEncoder.encode("http://101.90.11.90:9090?city=人脸识别");
//		String uurl = URLEncoder.encode("http://101.90.11.90:9090?city=人脸识别", "utf-8");
//		System.out.println(url);
//		System.out.println(uurl);
		byte[] bytes = Base64Utils.decodeFromString("dG9rZW5fdHDH_rXAyKjP3s67zby87LLpyqew3A");
		System.out.println(new String(bytes));

	}

}
