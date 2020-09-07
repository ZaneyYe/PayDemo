import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by yezhangyuan on 2018-04-23.
 *
 * @author yezhangyuan
 */
public class JsonPost2 {

	public static void main(String[] args) throws IOException {
//		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/user.mobile");
		HttpPost httpPost = new HttpPost("http://202.101.25.188:10533/open/access/1.0/user.auth");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
//		jsonParam.put("appId", "d43714e0246a435e87037f80495d2c6d");
//		jsonParam.put("appId", "a5949221470c4059b9b0b45a90c81527");
		jsonParam.put("appId", "0c99b92ae08a4367ac6251494ba398c5");

		jsonParam.put("openId", "Hii4fc8Ymn2NLrfvpopFhLI5xCsPjWIYrnDjCH80HZn/GAE1nigt8xn1MamWPBtz");
//		String str = Utils.createNonceStr();
		jsonParam.put("accessToken", "wKuk7BM3l07S84jmwvaMcvzPogJL7nT+63mPpmtbqjGFQNONCZPmyRKuu3QnKEDfhb29WDsI1PcJPrQERVwKt8Y/nRzcf9gj");
		jsonParam.put("backendToken","thUQqKIpT5qe1fM0PI47dg==");
//		jsonParam.put("cardTp","00");
//		jsonParam.put("needPay","1");
//		jsonParam.put("needSameName","1");

//		String date = String.valueOf(System.currentTimeMillis()/1000);
//		jsonParam.put("timestamp",date);
//
//		String waitSign = "appId=c18dccad2e9e47cfb3bcda13011415e0&nonceStr="+str+"&secret=37dee01ed5244852aa860e11862a6c1f&timestamp=" + date;
//		String sign = Utils.sha256(waitSign.getBytes());
//		jsonParam.put("signature",sign);

		System.out.println(jsonParam.toString());

		StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
//		System.out.println();

		HttpResponse resp = client.execute(httpPost);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
		System.out.println(respContent);

	}
}
