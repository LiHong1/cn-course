package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Manage;

/**
 * 管理员Service接口
 *
 * @author li hong
 */

public interface ManageService extends BaseService<Manage> {
    /**
     * 管理员登录
     *
     * @param number
     * @param password
     * @return
     */
    public Manage login(String number, String password);

    /**
     * 更据用户名查找
     *
     * @param number
     * @return
     */
    public Manage findByNumber(String number);

}
