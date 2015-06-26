package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Job;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 作业Dao接口
 *
 * @author li hong
 */
public interface JobDao extends BaseDao<Job> {

    List<Job> findJobs(Student student, State state);

}
