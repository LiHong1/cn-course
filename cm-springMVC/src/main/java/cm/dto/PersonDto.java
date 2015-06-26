package cm.dto;


import java.util.UUID;

/**
 * Created by li hong on 2015/5/13.
 */
public class PersonDto {
	/**
	 * 学号或教工号
	 */
	String number;
	/**
	 * 密码
	 */
	String password;
	/**
	 * 课程id
	 */
	UUID courseId;
	/**
	 * 班级id
	 */
	UUID classId;

	public UUID getClassId() {
		return classId;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public String getNumber() {
		return number;
	}

	public String getPassword() {
		return password;
	}

	public void setClassId(UUID classId) {
		this.classId = classId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
