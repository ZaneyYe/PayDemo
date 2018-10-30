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
		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/oauth.getCardList");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "973db1f168824087bad7a41336c34195");
		jsonParam.put("openId", "0Fg54bz8v4MgAQZJ/zY2E7wRF7GWdQTjp5VUMN1klWqGhjrkqlBM7FVu26ZQW5EA");
//		String str = Utils.createNonceStr();
		jsonParam.put("accessToken", "QAKAuWQneOmjuQB6rHPrLAuokAiwxW66ZPP+B43Q+2NjTdQwEXglEUVdxoD7tMMejeZT0oyH784XeEYVioBXL4j+dj9hvJLyQwHw2MwzaPk=");
		jsonParam.put("backendToken","1NIaDMVyQLapfujl6I8lkA==");
		jsonParam.put("cardTp","00");
		jsonParam.put("needPay","1");
		jsonParam.put("needSameName","0");

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
