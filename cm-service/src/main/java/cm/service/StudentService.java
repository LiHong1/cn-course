package cm.service;

import cm.commons.service.BaseService;
import cm.dto.StudentDto;
import cm.entity.Student;

import java.util.Calendar;
import java.util.List;

/**
 * 学生Service接口
 *
 * @author li hong
 */

public interface StudentService extends BaseService<Student> {
    /**
     * 学生登录
     *
     * @param number
     * @param password
     * @return
     */
    public Student login(String number, String password);

    /**
     * 按学号查找
     *
     * @param number
     * @return
     */
    public Student findByNumber(String number);

    /**
     * 按学号模糊删除
     *
     * @param number
     */
    public void likeDelete(String number);

    /**
     * 获取最近更新的学生信息
     *
     * @param c
     * @return
     */
    public List<StudentDto> getLatestUpdate(Calendar c);

    /**
     * webservice获取所有实例（operate 不为2）
     *
     * @return
     */
    public List<Student> getAll();


}
