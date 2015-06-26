package cm.commons.dao;

import cm.commons.bean.AllPurposeList;

/**
 * 列表Dao接口
 *
 * @author lzc
 */
public interface AllPurposeListDao extends BaseDao<AllPurposeList> {
    /**
     * 获取列表
     *
     * @param name
     * @return
     */
    AllPurposeList get(String name);

    /**
     * 列表是否存在
     *
     * @param name
     * @return
     */
    boolean exists(String name);
}
