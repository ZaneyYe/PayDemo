import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by xu xinyuan on 2019-03-21.
 *
 * @author xu xinyuan
 */
public class CardGetter {
	public static void main(String[] args) throws IOException, JSONException {
		// HttpPost httpPost = new
		// HttpPost("https://open.95516.com/open/access/1.0/contract.status");

		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/oauth.getCardList");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "d43714e0246a435e87037f80495d2c6d");
		jsonParam.put("openId", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
		jsonParam.put("backendToken", "dvdN9mCoQ4aEkX2hDlhx9Q==");
		jsonParam.put("accessToken",
				"RoYGwYe60HfZtl/MuS+7nz5pCY1VaYMRhV1HMVi5FGcaSH8VbD36PbHaXL3cRH45a3w9KXtumIQJ/J1Q6bxadgRobyDJh7SC");
		jsonParam.put("cardTp", "00");
		jsonParam.put("needSameName", "0");
		jsonParam.put("needPay", "0");
		// jsonParam.put("content", "hello！");
		// jsonParam.put("url", "http://172.20.38.241");
		System.out.println(jsonParam.toString());
		// 解决中文乱码问题
		StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
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
