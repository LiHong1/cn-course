package cm.dao;

import cm.commons.dao.BaseDao;
import cm.dto.StudentDto;
import cm.entity.Student;

import java.util.Calendar;
import java.util.List;

/**
 * 学生Dao接口
 *
 * @author li hong
 */
public interface StudentDao extends BaseDao<Student> {
    /**
     * 根据学号查找学生
     *
     * @param number
     * @return
     */
    public Student findByNumber(String number);

    /**
     * 获得学生
     *
     * @param number
     * @param password
     * @return
     */
    public Student get(String number, String password);

    /**
     * 模糊删除学生
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
