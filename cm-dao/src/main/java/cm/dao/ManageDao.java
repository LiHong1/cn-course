package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Manage;

/**
 * 管理员Dao接口
 *
 * @author li hong
 */
public interface ManageDao extends BaseDao<Manage> {
    /**
     * 管理员登录
     *
     * @param number
     * @param password
     * @return
     */
    Manage login(String number, String password);

    /**
     * 根据用户名查找
     *
     * @param number
     * @return
     */
    Manage findByNumber(String number);
}
