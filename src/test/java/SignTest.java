import org.codehaus.jackson.map.ObjectMapper;
import sdkUtil.LogUtil;
import sdkUtil.SDKConstants;
import sdkUtil.SecureUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yezhangyuan on 2018-04-24.
 *
 * @author yezhangyuan
 */
public class SignTest {

	public static void main(String[] args) throws IOException {
		String encoding = "UTF-8";
		Map<String, String> data = new HashMap<>();

		data.put("appId", "7ff6d728928c4d9f8f7e5a8ccd5e0d81");
		data.put("nonceStr", "0FvhteCdmJv3xfV6");
		long time = System.currentTimeMillis();
		System.out.println(time);
		data.put("timestamp",1559292191+"");
//		data.put("url","https://hljslh5.jingmon.com/");
//		data.put("frontToken","ousk7Zm/QnixSK4JeTi1vA==");
		String waitForSign = coverMap2String(data);
		LogUtil.writeLog("待签名请求报文串:[" + waitForSign + "]");

		String strAfterSha256 = SecureUtil.sha256X16Str(waitForSign,
				encoding);
		data.put("signature",strAfterSha256);

		LogUtil.writeLog("签名串:[" + strAfterSha256 + "]");
		ObjectMapper objectMapper = new ObjectMapper();

//		String data2 = objectMapper.writeValueAsString(data);
//
//		try {
//			HttpPost post = new HttpPost("https://open.95516.com/open/access/1.0/frontToken");
//			// 接收参数json列表
//			StringEntity entity = new StringEntity(data2,"utf-8");//解决中文乱码问题
//			entity.setContentEncoding("UTF-8");
//			entity.setContentType("application/json");
//			post.setEntity(entity);
//
//			CloseableHttpClient httpclient = HttpClients.createDefault();
//			CloseableHttpResponse result = httpclient.execute(post);
//
////			 请求结束，返回结果
//			String resData = EntityUtils.toString(result.getEntity());
//
//			System.out.println(resData);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

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
