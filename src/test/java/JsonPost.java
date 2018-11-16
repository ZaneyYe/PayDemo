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
		jsonParam.put("appId", "98528189d94d40a6a96c8a30ba361752");
		jsonParam.put("secret", "eb86fa83dfb94e2faa0ce65faeceb7df");
		String str = Utils.createNonceStr();
		jsonParam.put("nonceStr", str);


		String date = String.valueOf(System.currentTimeMillis()/1000);
		jsonParam.put("timestamp",date);

		String waitSign = "appId=98528189d94d40a6a96c8a30ba361752&nonceStr="+str+"&secret=eb86fa83dfb94e2faa0ce65faeceb7df&timestamp=" + date;
		String sign = Utils.sha256(waitSign.getBytes());
		jsonParam.put("signature",sign);

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
