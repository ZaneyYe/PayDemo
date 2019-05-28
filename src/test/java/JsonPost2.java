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
		jsonParam.put("appId", "d43714e0246a435e87037f80495d2c6d");
		jsonParam.put("openId", "2X/rjxxiADyrDxLV4jm43trfwlvlG6gvsFKWdLWqsvpqUxB90dsWjSExndldBabC");
//		String str = Utils.createNonceStr();
		jsonParam.put("accessToken", "C2X0OuQbQJeFC/H2QrIPXrRo2bIRo6C6Sm2VNekNdAyo8kjLcipv71BwvwO05JKAovVL6iGO1O/paEdn/XJMYN48qdlCzjMVjT4sbvW61V0=");
		jsonParam.put("backendToken","T5jkuW4DRPyR6wjYccKvKQ==");
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
