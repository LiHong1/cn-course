package cm.service.impl;


import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.StateDao;
import cm.entity.Clazz;
import cm.entity.Course;
import cm.entity.State;
import cm.entity.Teacher;
import cm.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 班级 课程 老师对应关系Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class StateServiceImpl extends BaseServiceImpl<State> implements StateService {
    @Autowired
    private StateDao stateDao;

    @Override
    public List<State> find(Clazz clas, Course course, Teacher teacher) {
        return stateDao.find(clas, course, teacher);
    }

    @Override
    public State find(Course course, Clazz clas) {
        if (course != null && clas != null) {
            return stateDao.find(course, clas);
        }
        return null;
    }
    @Override
    public State get(UUID courseId, UUID classID) {
        if (courseId != null && classID != null) {
            return stateDao.get(courseId, classID);
        }
        return null;
    }

} 
