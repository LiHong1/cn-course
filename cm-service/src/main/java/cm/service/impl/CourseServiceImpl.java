package cm.service.impl;


import cm.commons.service.impl.BaseServiceImpl;
import cm.entity.Course;
import cm.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程Service的实现类
 *
 * @author li hong
 */

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {


} 
