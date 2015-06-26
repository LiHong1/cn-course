package cm.util;

/**
 * Created by li hong on 2015/6/2.
 */
public class Student {
	String name;
	String number;
	String clazz;

	public Student(String number, String clazz, String name) {
		this.number = number;
		this.clazz = clazz;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getClazz() {
		return this.clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
}
