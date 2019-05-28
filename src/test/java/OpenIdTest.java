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
 * Created by yezhangyuan on 2018-10-26.
 *
 * @author yezhangyuan
 */
public class OpenIdTest {

	public static void main(String[] args) throws IOException {
		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/token");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "d43714e0246a435e87037f80495d2c6d");
		jsonParam.put("backendToken", "T5jkuW4DRPyR6wjYccKvKQ==");
		jsonParam.put("code", "VQP2zHXlSAGdNMwWrX+6xQ==");
		jsonParam.put("grantType", "authorization_code");

		System.out.println(jsonParam.toString());

		StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(respContent);

	}

}
