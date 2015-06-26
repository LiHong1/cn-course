package cm.dwr;

import cm.entity.StAttendanceItem;

import java.util.UUID;

public interface IDwrService {
    //试卷加入考试
    public void addToExam(String id);

    //学生出勤情况
    public void updateStAttendanceItem(String stAttendanceItemId,String type);

    //取消
    public void updateStAttendanceItem(String stAttendanceItemId);
}
