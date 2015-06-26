package cm.commons.service;

import cm.commons.bean.ErrorCodeInfo;

/**
 * 错误码服务
 *
 * @author lzc
 */
public interface ErrorCodeService extends BaseService<ErrorCodeInfo> {
    /**
     * 获取错误码的显示信息
     *
     * @param erorrCode
     * @return 找不到到返回""
     */
    String getShowMessage(Integer errorCode);

    /**
     * 更新或者新增错误码信息
     *
     * @param errorCode
     * @param message
     * @param module
     */
    ErrorCodeInfo merge(Integer errorCode, String message, String module, String description);

    /**
     * 根据模块清除
     *
     * @param moduleName
     */
    void deleteByModule(String moduleName);

    /**
     * 清除所有
     */
    void deleteAll();

}
