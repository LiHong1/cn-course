package cm.service;


import cm.commons.service.BaseService;
import cm.entity.Application;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 学生申请Service接口
 *
 * @author li hong
 */
public interface ApplicationService extends BaseService<Application> {
    /**
     * 查找申请
     *
     * @param state
     * @return
     */
    public List<Application> findByState(State state);

    /**
     * 查找申请
     *
     * @param state
     * @param student
     * @return
     */
    public Application find(State state, Student student);

    /**
     * 筛选申请
     *
     * @param cuState
     * @param type
     * @return
     */
    public List<Application> find(State cuState, Long type);



}
