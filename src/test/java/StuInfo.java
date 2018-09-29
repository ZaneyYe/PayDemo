import java.io.Serializable;

/**
 * Created by yezhangyuan on 2018-05-11.
 *
 * @author yezhangyuan
 */
public class StuInfo implements Serializable {

	private static final long serialVersionUID = 4490926877013947575L;

	private int age;

	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
