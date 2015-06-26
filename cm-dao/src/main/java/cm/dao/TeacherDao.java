package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Teacher;

/**
 * 老师Dao接口
 *
 * @author li hong
 */
public interface TeacherDao extends BaseDao<Teacher> {
    /**
     * 老师登录
     *
     * @param number
     * @param password
     * @return
     */
    Teacher login(String number, String password);

    /**
     * 更据工号查找老师
     *
     * @param number
     * @return
     */
    Teacher findByNumber(String number);
}
