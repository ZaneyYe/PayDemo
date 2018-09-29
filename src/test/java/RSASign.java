import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sdkUtil.LogUtil;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2018-09-07.
 *
 * @author yezhangyuan
 */
public class RSASign {

	public static final String SIGN_ALGORITHMS = "SHA256WithRSA";

	public static String sign(String value, String privateKey) throws Exception {
//        byte[] keyBytes = BytesUtil.hexToBytes(privateKey);
		byte[] keyBytes = Base64.decodeBase64(privateKey.getBytes());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(keySpec);
		Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(value.getBytes("UTF-8"));
		byte[] signed = signature.sign();
//		String result = Base64.encodeBase64String(signed);
        String result = bytesToHex(signed);

		return result;
	}

	public static String bytesToHex(byte[] bytes) {
		String hexArray = "0123456789abcdef";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			int bi = b & 0xff;
			sb.append(hexArray.charAt(bi >> 4));
			sb.append(hexArray.charAt(bi & 0xf));
		}
		return sb.toString();
	}


	public static void main(String[] args) throws Exception {
		String encoding = "UTF-8";
		Map<String, String> data = new HashMap<>();
		data.put("appId", "5949221470");
		data.put("indUsrId", "1057528009");
		long time = System.currentTimeMillis() /1000;
		System.out.println(time);
		data.put("timestamp",time+"");
		data.put("nonceStr","123123");
		data.put("chnl","1");
		String waitForSign = SignTest.coverMap2String(data);
		LogUtil.writeLog("待签名请求报文串:[" + waitForSign + "]");
		String pk = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIApSXeqcT3pKCBDKGpKqf+ByvrSdwMzDnmUb+6eVsms/GpbIhCHrGr+/fPRuq/COZuEsiNmkjusEeNgJCEoga/AzQ3xDklx6VZ7UDMfOVtK4KRsWHSMfYCtcXm2btppxcM6dOFwimtt1vPptgYfxsbdmtrxJw0ziccj0jAV4wpPAgMBAAECgYAHh+WMRZSv6aJ0+t1GGasRm4Pc5z8dDgP8uu8021MIOMUATuiahg5onyE3EYzhxQzziYGaOO3A2eSXMtAMrr+oCdwN7gqwjShgGkB/2cDvDnJ0wFHntvCYXjp13QEFJ8CO5fkYWLVxFtJ6VrdLUktUvhR+Fw4JLuTho/11lYdhGQJBAMFdi3RD2XEyoAoH4mkZ5siPfyW6gu5qkBBroAb3WJaAYxL0bwRmFYI+Q5YAmjYZwJlnm8AC3bMJREpFslP0NOUCQQCprNCXNQal6XuzyQGngy6eAOVGLKp/inGWyRW/wuFu6TJAGTAonbwTpNfeEfQ3aOJGgt/DHWOfvdVJ9BbraqMjAkEArX/2BRhsHrnCB74TVSK8hPDcsUms+af8I/+t0xJVFpWUUAmrI1NFsVuU4R8hP7HTstHYWm0359FEyS/IVrQkUQJAXaWG3t2iVLHf12OKaTTq5sPhxvBiDdCQTsOfIF5j474LQPtl7BTauBDUH7nTCz31HSugamTvFjxE2vNALyCE9wJAEE6G0W9IZDm6w+5nbiZ2mAhd0VBfMI2apa09/yMQGcqt2974bw/42chPoO9Vcwua+x3LsQ1stxl3+6jADQp7Fw==";
		String sign = sign(waitForSign, pk);

//		HttpPost httpPost = new HttpPost("https://open.95516.com/open/access/1.0/frontToken");
		HttpPost httpPost = new HttpPost("https://101.231.204.80/app/access/bank/findUser");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		//json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appId", "5949221470");
		jsonParam.put("indUsrId", "1057528009");
		jsonParam.put("nonceStr","123123");
		jsonParam.put("timestamp",time+"");
		jsonParam.put("chnl","1");
		jsonParam.put("signature",sign);

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
		System.out.println(sign);
	}


}
