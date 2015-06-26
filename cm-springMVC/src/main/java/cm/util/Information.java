package cm.util;

/**
 * Created by li hong on 2015/6/2.
 */
public class Information {
	String courseNumber;
	String className;
	String teacherName;
	String courseName;
	String url;
	public Information(){

	}
	public String getCourseNumber() {
		return this.courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}


	public Information(String className, String teacherName, String courseNumber,String courseName, String url) {
		this.className = className;
		this.teacherName = teacherName;
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.url = url;
	}

	public Information(String courseNumber, String className, String courseName, String url) {
		this.courseNumber = courseNumber;
		this.className = className;
		this.courseName = courseName;
		this.url = url;
	}
	public Information( String className, String courseName, String url) {
		this.courseNumber = courseNumber;
		this.className = className;
		this.courseName = courseName;
		this.url = url;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
