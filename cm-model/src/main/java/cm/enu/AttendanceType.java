package cm.enu;

/**
 * Created by li hong on 2015/6/12.
 */
public enum AttendanceType {
	LATE("迟到"),LEAVE("早退"),
	MISS("请假"),UNKNOWN("旷课");

	private String name;

	private AttendanceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
