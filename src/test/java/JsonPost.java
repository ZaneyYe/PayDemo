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
public class JsonPost {

	public static void main(String[] args) throws IOException {
		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/backendToken");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "7ff6d728928c4d9f8f7e5a8ccd5e0d81");
		jsonParam.put("secret", "0b40b177b27143f59cc293c055bb69e7");
		jsonParam.put("nonceStr","5tL554bOi4565G57");


		String date = String.valueOf(System.currentTimeMillis()/1000);
		jsonParam.put("timestamp",date);

		String waitSign = "appId=7ff6d728928c4d9f8f7e5a8ccd5e0d81&nonceStr=5tL554bOi4565G57&secret=0b40b177b27143f59cc293c055bb69e7&timestamp=" + date;
		String sign = Utils.sha256(waitSign.getBytes());
		jsonParam.put("signature",sign);

		System.out.println(jsonParam.toString());

		StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		System.out.println();

		HttpResponse resp = client.execute(httpPost);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
		System.out.println(respContent);

	}
}
