import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
//		HttpPost httpPost = new HttpPost("http://202.101.25.188:10533/open/access/1.0/backendToken");
//		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/return.tax");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		//人脸测试
		jsonParam.put("appId", "d43714e0246a435e87037f80495d2c6d");
//		jsonParam.put("appId", "0c99b92ae08a4367ac6251494ba398c5");
		//测试demo
//		jsonParam.put("appId", "a5949221470c4059b9b0b45a90c81527");
		//人脸秘钥
		jsonParam.put("secret", "b3b15e5dee9b479b9011b43ca47f753e");
		//测试demo密钥
//		jsonParam.put("secret", "2d6f9089895f41bdbd3e5a8fd6863f0e");
//		jsonParam.put("secret", "e31496365864411d8698a2c89b7ccd9e");

		String str = Utils.createNonceStr();
		jsonParam.put("nonceStr", str);

        long datel = System.currentTimeMillis()/1000;
		String date = String.valueOf(datel);
		jsonParam.put("timestamp",date);

		String waitSign = "appId=d43714e0246a435e87037f80495d2c6d&nonceStr="+str+"&secret=b3b15e5dee9b479b9011b43ca47f753e&timestamp=" + date;
		String sign = Utils.sha256(waitSign.getBytes());
//		sign = sign.replaceAll("a","A");
		jsonParam.put("signature",sign);

		System.out.println(jsonParam.toString());

		StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		HttpResponse resp = client.execute(httpPost);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
		System.out.println(respContent);

		JsonObject jsonObject = (JsonObject) new JsonParser().parse(respContent);

		String res = jsonObject.get("resp").getAsString();
		jsonObject = jsonObject.get("params").getAsJsonObject();
		String expiresIn = jsonObject.get("expiresIn").getAsString();
		System.out.println("res::" + res);
		System.out.println("expiresIn::" + expiresIn);

	}
}
