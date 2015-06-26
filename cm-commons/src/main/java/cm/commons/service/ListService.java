package cm.commons.service;

import cm.commons.bean.AllPurposeList;
import cm.commons.bean.ListItem;

/**
 * 列表服务接口
 *
 * @author lzc
 */
public interface ListService extends BaseService<AllPurposeList> {

    /**
     * 获取列表
     *
     * @param name
     * @return
     * @throws ModelException
     */
    AllPurposeList get(String name);

    /**
     * 新增列表
     *
     * @param name
     * @return
     * @throws ModelException
     */
    AllPurposeList create(String name);

    /**
     * 更新列表项
     *
     * @param item
     */
    void update(ListItem item);
}
