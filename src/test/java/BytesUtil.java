import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BytesUtil {

    /**
     * 将16进制的字符串转换成bytes
     *
     * @param hex
     * @return 转化后的byte数组
     */
    public static byte[] hexToBytes(String hex) {
        return hexToBytes(hex.toCharArray());
    }

    /**
     * 将16进制的字符数组转换成byte数组
     *
     * @param hex
     * @return 转换后的byte数组
     */
    public static byte[] hexToBytes(char[] hex) {
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }

    /**
     * 将byte数组转换成16进制字符串
     *
     * @param bytes
     * @return 16进制字符串
     */
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

    public static byte[] readAll(InputStream in) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for (int i = in.read(); i != -1; i = in.read()) {
            bout.write(i);
        }
        return bout.toByteArray();
    }

    public static void main(String[] args){
        String str = "30820122300d06092a864886f70d01010105000382010f003082010a02820101009ffb23f88621abad99375a95df2f2606f9397ae60cdf23402420dcdda5edad0fa121499f6275bc38509013819e223feed41795efb099cf351c75e9234e13ae338d7f0bbdfdc47e1b6c2436251bd584126a692c9e2d345d7ddceec474cc7493b1bca392fa088e75c0b671b79ad6cb3e10ec15d05e00601f3c7f17ed6c12ac7507ace97ffc1abf4628d5d2c33e16b9cd5e46d9a4930769cde85dd563164c3ef88c0c102ecb7535b9661784bc7807e76e94150da8d89e35b20ccf2887fbca92f07a12a8edc83b01b9fcce57cace41e6e4b4fe81b32384c8bc1195bcb1536bfbd41baf2486ba4b9fc6c888f6264e2b0cfa71595ed7da522ea3588c77af77c1aa4ed90203010001";
        System.out.println(Base64Utils.encodeToString(hexToBytes(str)));
    }

}
