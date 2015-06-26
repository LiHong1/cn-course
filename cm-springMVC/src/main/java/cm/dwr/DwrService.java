package cm.dwr;

import cm.dao.PaperDao;
import cm.dao.StAttendanceDao;
import cm.dao.StAttendanceItemDao;
import cm.dao.StudentDao;
import cm.entity.StAttendance;
import cm.entity.StAttendanceItem;
import cm.entity.Student;
import cm.enu.AttendanceType;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service("dwrService")
public class DwrService implements IDwrService {
    @Autowired
    private PaperDao paperDao;
    @Autowired
    private StAttendanceDao stAttendanceDao;
    @Autowired
    private StAttendanceItemDao stAttendanceItemDao;
    @Autowired
    private StudentDao studentDao;

    public void addToExam(String id) {
        System.out.println(id);
        paperDao.changePaperType(UUID.fromString(id));
    }
    public void updateStAttendanceItem(String stAttendanceItemId,String type){
      StAttendanceItem stAttendanceItem = stAttendanceItemDao.get(UUID.fromString(stAttendanceItemId));
        StAttendance stAttendance = stAttendanceItem.getAttendance();
        reduceCount(stAttendanceItem, stAttendance);
        stAttendanceItem.setType(AttendanceType.values()[Integer.parseInt(type)]);
        if(stAttendanceItem.getType().ordinal() == 0){
            stAttendance.setLateCount(stAttendance.getLateCount()+1);
        }else if(stAttendanceItem.getType().ordinal() ==1){
            stAttendance.setLeaveCount(stAttendance.getLeaveCount() + 1);
        }else if(stAttendanceItem.getType().ordinal() == 2){
            stAttendance.setMissCount(stAttendance.getMissCount() + 1);
        }else if(stAttendanceItem.getType().ordinal() ==3){
            stAttendance.setUnKnowCount(stAttendance.getUnKnowCount() + 1);
        }
        stAttendanceDao.update(stAttendance);
        stAttendanceItemDao.update(stAttendanceItem);
    }
    public void updateStAttendanceItem(String stAttendanceItemId){

        StAttendanceItem stAttendanceItem = stAttendanceItemDao.get(UUID.fromString(stAttendanceItemId));
        StAttendance stAttendance = stAttendanceItem.getAttendance();
        reduceCount(stAttendanceItem,stAttendance);
        stAttendanceDao.update(stAttendance);
        stAttendanceItem.setType(null);
        stAttendanceItemDao.update(stAttendanceItem);
    }

    private void reduceCount(StAttendanceItem stAttendanceItem,StAttendance stAttendance){

        if(stAttendanceItem.getType()!=null){
           if(stAttendanceItem.getType().ordinal() == 0){
               stAttendance.setLateCount(stAttendance.getLateCount()-1);
           }else if(stAttendanceItem.getType().ordinal() ==1){
               stAttendance.setLeaveCount(stAttendance.getLeaveCount() - 1);
           }else if(stAttendanceItem.getType().ordinal() == 2){
               stAttendance.setMissCount(stAttendance.getMissCount() - 1);
           }else if(stAttendanceItem.getType().ordinal() ==3){
               stAttendance.setUnKnowCount(stAttendance.getUnKnowCount() - 1);
           }
       }
    }
}
