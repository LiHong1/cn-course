package cm.service.impl;


import cm.commons.service.impl.BaseServiceImpl;
import cm.commons.util.MDCoder;
import cm.dao.TeacherDao;
import cm.entity.Teacher;
import cm.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher findByNumber(String number) {
        return teacherDao.findByNumber(number);
    }

    @Override
    public Teacher login(String number, String password) {
        password = MDCoder.encodeMD5Hex(password);
        return teacherDao.login(number, password);
    }

} 
