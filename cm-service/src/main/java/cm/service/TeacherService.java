package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Teacher;

/**
 * 老师Service接口
 *
 * @author li hong
 */
public interface TeacherService extends BaseService<Teacher> {
    /**
     * 老师登录
     *
     * @param number
     * @param password
     * @return
     */
    public Teacher login(String number, String password);

    /**
     * 更据工号查找老师
     *
     * @param number
     * @return
     */
    public Teacher findByNumber(String number);
}
