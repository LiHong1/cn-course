package cm.commons.service.impl;

import cm.commons.bean.ErrorCodeInfo;
import cm.commons.dao.ErrorCodeDao;
import cm.commons.service.ErrorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 错误码信息Service实现类
 *
 * @author lzc
 */
@Service
public class ErrorCodeServiceImpl extends BaseServiceImpl<ErrorCodeInfo> implements ErrorCodeService {
    @Autowired
    private ErrorCodeDao errorCodeDao;

    @Transactional(readOnly = true)
    public String getShowMessage(Integer errorCode) {
        return errorCodeDao.getShowMessage(errorCode);
    }

    @Transactional
    public ErrorCodeInfo merge(Integer errorCode, String message, String module, String description) {
        ErrorCodeInfo info = new ErrorCodeInfo();
        info.setErrorCode(errorCode);
        info.setShowMessage(message);
        info.setModule(module);
        info.setDescription(description);
        return errorCodeDao.merge(info);
    }


    @Transactional
    public void deleteAll() {
        errorCodeDao.deleteAll();
    }

    @Transactional
    public void deleteByModule(String moduleName) {
        errorCodeDao.deleteByModule(moduleName);
    }

}
