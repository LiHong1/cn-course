package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by li hong on 2015/6/12.
 */

/**
 * 一次点名情况
 */
@Entity
@Table(name = "T_ST_ATTENDANCE")
public class StAttendance extends DomainObject{
	//对应关系
	private State state;
	//当前点名 所有学生的情况
	private Set<StAttendanceItem> stAttendanceItems = new HashSet<StAttendanceItem>();
	//请假总记录
	private Integer missCount;
	//迟到总记录
	private Integer lateCount;
	//早退总记录
	private Integer leaveCount;
	//逃课总记录
	private Integer unKnowCount;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_ID")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToMany(mappedBy = "attendance",cascade = CascadeType.REMOVE)
	public Set<StAttendanceItem> getStAttendanceItems() {
		return this.stAttendanceItems;
	}


	public void setStAttendanceItems(Set<StAttendanceItem> stAttendanceItems) {
		this.stAttendanceItems = stAttendanceItems;
	}

	@Column(name="MISS_COUNT")
	public Integer getMissCount() {
		return this.missCount;
	}

	public void setMissCount(Integer missCount) {
		this.missCount = missCount;
	}

	@Column(name="LATE_COUNT")
	public Integer getLateCount() {
		return this.lateCount;
	}

	public void setLateCount(Integer lateCount) {
		this.lateCount = lateCount;
	}

	@Column(name="LEAVE_COUNT")
	public Integer getLeaveCount() {
		return this.leaveCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	@Column(name="UNKNOW_COUNT")
	public Integer getUnKnowCount() {
		return this.unKnowCount;
	}

	public void setUnKnowCount(Integer unKnowCount) {
		this.unKnowCount = unKnowCount;
	}
}
