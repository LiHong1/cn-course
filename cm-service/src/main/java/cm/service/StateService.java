package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Clazz;
import cm.entity.Course;
import cm.entity.State;
import cm.entity.Teacher;

import java.util.List;
import java.util.UUID;

/**
 * 班级 课程 老师对应关系Service接口
 *
 * @author li hong
 */
public interface StateService extends BaseService<State> {
    /**
     * 查找班级 课程 老师的对应关系
     *
     * @param clas
     * @param course
     * @param teacher
     * @return
     */
    public List find(Clazz clas, Course course, Teacher teacher);

    /**
     * 查找班级 课程 老师的对应关系
     *
     * @param clas
     * @param course
     * @return
     */
    public State find(Course course, Clazz clas);

    /**
     * 精确查找
     * @param courseId
     * @param classID
     * @return
     */
    public State get(UUID courseId, UUID classID);

}
