package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.commons.util.MDCoder;
import cm.dao.StudentDao;
import cm.dto.StudentDto;
import cm.entity.Student;
import cm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * 学生Service实现
 *
 * @author li hong
 */

@Service
@Transactional
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student findByNumber(String number) {
        return studentDao.findByNumber(number);
    }

    @Override
    public Student login(String number, String password) {
        password = MDCoder.encodeMD5Hex(password);
        return studentDao.get(number, password);
    }

    @Override
    public void likeDelete(String number) {
        studentDao.likeDelete(number);
    }

//    public void save(Student s) {
//        s.setCreatedDate(Calendar.getInstance());
//        s.setUpdatedDate(Calendar.getInstance());
//        studentDao.save(s);
//    }

    /**
     * 获取最近更新的学生信息
     *
     * @param c
     * @return
     */
    @Override
    public List<StudentDto> getLatestUpdate(Calendar c) {
        return studentDao.getLatestUpdate(c);
    }

    /**
     * webservice获取所有实例（operate 不为2）
     *
     * @return
     */
    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }
} 
