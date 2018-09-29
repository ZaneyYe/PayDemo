import java.io.*;

/**
 * Created by yezhangyuan on 2018-05-11.
 *
 * @author yezhangyuan
 */
public class SerializationUtils {

	private static final String FILE_OUT_DIR = "E:\\out1";

	public static void writeObject(Serializable s){
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_OUT_DIR));
			outputStream.writeObject(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object readObject(){
		Object obj = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_OUT_DIR));
			obj = inputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}



}
