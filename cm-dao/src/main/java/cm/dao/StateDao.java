package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Clazz;
import cm.entity.Course;
import cm.entity.State;
import cm.entity.Teacher;

import java.util.List;
import java.util.UUID;

/**
 * 班级 课程 老师对应关系Dao接口
 *
 * @author li hong
 */
public interface StateDao extends BaseDao<State> {
    /**
     * 模糊查找
     *
     * @param clas
     * @param course
     * @param teacher
     * @return
     */
    List<State> find(Clazz clas, Course course, Teacher teacher);

    /**
     * 精确查找
     *
     * @param course
     * @param clas
     * @return
     */
    State find(Course course, Clazz clas);

    /***
     * 精确查找
     * @param courseId
     * @param classId
     * @return
     */
    State get(UUID courseId, UUID classId);
}
