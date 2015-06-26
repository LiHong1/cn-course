package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Application;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 学生申请Dao接口
 *
 * @author li hong
 */
public interface ApplicationDao extends BaseDao<Application> {
    /**
     * 查找申请
     *
     * @param state
     * @return
     */
    List<Application> findByState(State state);

    /**
     * 查找申请
     *
     * @param state
     * @param type
     * @return
     */
    List<Application> find(State state, Long type);

    /**
     * 查找申请
     *
     * @param state
     * @param student
     * @return
     */
    Application find(State state, Student student);
}
