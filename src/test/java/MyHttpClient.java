import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import sdkUtil.SDKConstants;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yezhangyuan on 2018-09-27.
 *
 * @author yezhangyuan
 */
public class MyHttpClient {

	private static ObjectMapper objectMapper = new ObjectMapper();

	private String url;

	public MyHttpClient() {
	}

	public MyHttpClient(String url){
		this.url = url;
	}

	/**
	 * post请求返回信息
	 * @param param
	 * @return
	 */
	public String excuteResult(String param){
		// 接收参数json列表
		StringEntity entity = new StringEntity(param,"utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");

		String resData;
		CloseableHttpClient httpclient = null;
		try {
			HttpPost post = new HttpPost(url);
			post.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(50000)
					.setSocketTimeout(50000)
					.setConnectionRequestTimeout(120000).build();
			post.setConfig(requestConfig);
			httpclient = HttpClients.createDefault();
			resData = "";
			CloseableHttpResponse response = null;
			try {
				  response = httpclient.execute(post);
	//			 请求结束，返回结果
				  resData = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resData;
	}

	/**
	 * 发送請求
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public String sendPost(Map<String,String> map) throws IOException {
		String params = objectMapper.writeValueAsString(map);
		System.out.println("请求参数：:" + params);
		String res = excuteResult(params);
		return res;
	}

	/**
	 * 将Map中的数据转换成key1=value1&key2=value2的形式 不包含签名域signature
	 *
	 * @param data
	 *            待拼接的Map数据
	 * @return 拼接好后的字符串
	 */
	public static String coverMap2String(Map<String, String> data) {
		TreeMap<String, String> tree = new TreeMap<String, String>();
		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			if (SDKConstants.param_signature.equals(en.getKey().trim())) {
				continue;
			}
			tree.put(en.getKey(), en.getValue());
		}
		it = tree.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			sf.append(en.getKey() + SDKConstants.EQUAL + en.getValue()
					+ SDKConstants.AMPERSAND);
		}
		return sf.substring(0, sf.length() - 1);
	}


}
