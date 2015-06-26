package cm.entity;

/**
 * Created by li hong on 2015/6/12.
 */

import cm.commons.DomainObject;
import cm.enu.AttendanceType;

import javax.persistence.*;

/**
 * 出勤表
 */
@Entity
@Table(name = "T_ST_ATTENDANCE_ITEM")
public class StAttendanceItem extends DomainObject{
	//出勤情况
	private AttendanceType type;
	//学生
	private Student student;
	//对应哪次点名
	private StAttendance attendance;


	public AttendanceType getType() {
		return this.type;
	}

	public void setType(AttendanceType type) {
		this.type = type;
	}

	@ManyToOne()
	@JoinColumn(name = "ST_ID")
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ST_ATT_ID")
	public StAttendance getAttendance() {
		return this.attendance;
	}

	public void setAttendance(StAttendance attendance) {
		this.attendance = attendance;
	}

}
