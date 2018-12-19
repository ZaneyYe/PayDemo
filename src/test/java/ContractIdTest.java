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
public class ContractIdTest {

	public static void main(String[] args) throws IOException {
		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/contract.apply");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "a5949221470c4059b9b0b45a90c81527");
		jsonParam.put("accessToken", "+aRsCfRAlAu3J9iJgyBFg+uuIVB7MbVP8PoxbFG1O5a+MgVqUQD9YPmvzBozwcb0pXMVFwqT2erkJm3twbnANlxgtZvuziMVN+q5XERbBiM=");
		jsonParam.put("openId", "IR09BvvPAzxk8T+aHkdLpC68T7JTr4/7HNhR5LXrBztQVGMcoREG/u0orGars25t");
		jsonParam.put("backendToken","VJ5G6bbjRha1N6+JLODuyQ==");
		jsonParam.put("plan_id","1d7a370d886f4133a2f366350116b2d0");
		jsonParam.put("contract_code","201811223344");
//		jsonParam.put("moblie","1851");
//		jsonParam.put("certId","");


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
