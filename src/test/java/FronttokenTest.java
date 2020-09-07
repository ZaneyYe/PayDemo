import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yezhangyuan on 2019-10-18.
 *
 * @author yezhangyuan
 */
public class FronttokenTest {

	public static void main(String[] args) throws IOException, URISyntaxException {
//		HttpPost httpPost = new HttpPost("https://openapi.unionpay.com/upapi/upWallet/frontToken");
//		HttpGet httpGet = new HttpGet("https://openapi.unionpay.com/upapi/upWallet/frontToken");


		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();

		//测试demo
		jsonParam.put("app_id", "upapi_01362996_u1d9q");
		//测试demo密钥
		jsonParam.put("secret", "6a57bb422f5fe14daaeac293b0c7d75b");

		String str = Utils.createNonceStr();
		jsonParam.put("nonceStr", str);

		long datel = System.currentTimeMillis()/1000;
		String date = String.valueOf(datel);
		jsonParam.put("timestamp",date);

		String waitSign = "appId=upapi_01362996_u1d9q&nonceStr="+str+"&secret=6a57bb422f5fe14daaeac293b0c7d75b&timestamp=" + date;
		String sign = Utils.sha256(waitSign.getBytes());
		jsonParam.put("signature",sign);


		System.out.println(jsonParam.toString());

		URIBuilder uriBuilder = new URIBuilder("https://openapi.unionpay.com/upapi/upWallet/frontToken");
		uriBuilder.addParameter("app_id", "upapi_01362996_u1d9q");
		uriBuilder.addParameter("secret", "6a57bb422f5fe14daaeac293b0c7d75b");
		uriBuilder.addParameter("nonceStr", str);
		uriBuilder.addParameter("timestamp", date);
		uriBuilder.addParameter("signature", sign);
		HttpGet httpGet = new HttpGet(uriBuilder.build());


//		StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
//		entity.setContentEncoding("UTF-8");
//		entity.setContentType("application/json");
////		httpPost.setEntity(entity);

		HttpResponse resp = client.execute(httpGet);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
		System.out.println(respContent);
	}
}
