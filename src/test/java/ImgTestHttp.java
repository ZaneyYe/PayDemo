import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2018-10-26.
 *
 * @author yezhangyuan
 */
public class ImgTestHttp {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws IOException {
		Map<String,String> map = new HashMap<>();
		map.put("appId","d0e121723ec0462c95f1c9141a5599d3");
		map.put("openId","taeDjyuQzIAXTKyytUn0c72832yy6l/raiT34oi4Ow7fILoqKSvUgdw6CbWpmbMw");//s
		map.put("backendToken","k+JZJN4bQleehJsAGDY7gQ==");//s
		map.put("accessToken","SK9fGYCKzpvaNjhErPE7mKisBfk3CZ0FAA742LURrKFzwTJO/eli4Mewo9tn2C28SxJ3Zh7JPyuZfgSKtn9h05BuFenE6DKy");//s

		String imgUrl = "https://open.95516.com/open/access/1.0/face.image";
		String videoUrl = "https://open.95516.com/open/access/1.0/face.video";

		String base64Img = sendPost(imgUrl,map);
		System.out.println(base64Img);
		Image image = ImgUtils.getImage(Base64Utils.decodeFromString(base64Img));

		ImageIO.write((RenderedImage) image,"png",new File("E://1028.png"));

	}



	/**
	 * 发送請求
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String targetUrl,Map<String,String> map) throws IOException {
		String image = "";
		MyHttpClient client = new MyHttpClient(targetUrl);
		String params = objectMapper.writeValueAsString(map);
		System.out.println("请求参数：:" + params);

		String res = client.excuteResult(params);
		System.out.println(res);
		ImgResponse imgResponse = objectMapper.readValue(res, ImgResponse.class);
		if(imgResponse.getResp().equals("00")){
			image = imgResponse.getParams().getImage();
		}else{
			System.out.println(imgResponse.getMsg());
		}
		return image;
	}


	/**
	 * 发送請求
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static String sendPost2(String targetUrl,Map<String,String> map) throws IOException {
		String video = "";
		MyHttpClient client = new MyHttpClient(targetUrl);
		String params = objectMapper.writeValueAsString(map);
		System.out.println("请求参数：:" + params);

		String res = client.excuteResult(params);
		System.out.println(res);
		VedioResponse vedioResponse = objectMapper.readValue(res, VedioResponse.class);
		if(vedioResponse.getResp().equals("00")){
			video = vedioResponse.getParams().getVedio();
		}else{
			System.out.println(vedioResponse.getMsg());
		}
		return video;
	}
}
