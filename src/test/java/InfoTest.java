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
public class InfoTest {

	public static void main(String[] args) throws IOException {
		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/user.mobile");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "98528189d94d40a6a96c8a30ba361752");
		jsonParam.put("accessToken", "9pEOPx/iooWLGF2Q+j8ce2NGfR2aWVyesNPnVucPcEf2c2XKHDK/vzlFVptPRaCs/nVrAbHCR67z8GzDdLaZU6Q9b2O9P7q6AWZSRz028Lc=");
		jsonParam.put("openId", "87bsHfUjzYUQulX1ZpBNad9nImJ+ItQqwKJc6+AcS7oagapGeo2pqEkjGOJuFGK8");
		jsonParam.put("backendToken","pCDvuqThTtKw14UMOkgTSQ==");


//		String date = String.valueOf(System.currentTimeMillis()/1000);
//		jsonParam.put("timestamp",date);
//
//		String waitSign = "appId=d0e121723ec0462c95f1c9141a5599d3&nonceStr="+str+"&secret=62bd2d16a02a47a7b919778cf66e7653&timestamp=" + date;
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
