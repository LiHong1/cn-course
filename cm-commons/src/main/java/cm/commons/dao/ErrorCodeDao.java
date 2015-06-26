package cm.commons.dao;

import cm.commons.bean.ErrorCodeInfo;

import java.util.List;

/**
 * 错误码Dao接口
 *
 * @author lzc
 */
public interface ErrorCodeDao extends BaseDao<ErrorCodeInfo> {
    /**
     * 获取错误码的显示信息
     *
     * @return 找不到到返回null
     */
    String getShowMessage(Integer errorCode);

    /*
     * 更新错误码信息
     */
    void update(ErrorCodeInfo info);

    /**
     * 新增错误码信息
     *
     * @param info
     * @return
     */
    Integer save(ErrorCodeInfo info);

    /**
     * 删除错误码信息
     *
     * @param info
     */
    void delete(ErrorCodeInfo info);

    /**
     * 删除所有错误码信息
     */
    void deleteAll();

    /**
     * 获取错误码信息
     *
     * @param code
     * @return
     */
    ErrorCodeInfo get(Integer code);

    /**
     * 获取所有错误码信息
     *
     * @return
     */
    List<ErrorCodeInfo> getAll();

    /**
     * 合并错误码信息
     *
     * @param info
     * @return
     */
    ErrorCodeInfo merge(ErrorCodeInfo info);

    /**
     * 删除模块所有错误码信息
     *
     * @param moduleName
     */
    void deleteByModule(String moduleName);

}
