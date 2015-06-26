package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.ErrorCodeInfo;
import cm.commons.dao.ErrorCodeDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 错误码Dao实现类
 *
 * @author lzc
 */
@Repository
public class ErrorCodeDaoImpl extends BaseDaoImpl<ErrorCodeInfo> implements ErrorCodeDao {
    @Override
    public String getShowMessage(Integer errorCode) {
        ErrorCodeInfo info = null;
        try {
            info = get(errorCode);
        } catch (Exception e) {
            return null;
        }
        return info.getShowMessage();
    }

    @Override
    public List<ErrorCodeInfo> getAll() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<ErrorCodeInfo> result = (List<ErrorCodeInfo>) session.createCriteria(ErrorCodeInfo.class).list();
        return result;
    }

    @Override
    public ErrorCodeInfo get(Integer code) {
        Session session = sessionFactory.getCurrentSession();
        ErrorCodeInfo result = (ErrorCodeInfo) session.get(ErrorCodeInfo.class, code);
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.ERROR_CODE_NOT_EXIST, "找不到错误码信息：code=" + code.toString());
        }
        return result;
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete ErrorCodeInfo").executeUpdate();
    }

    @Override
    public void update(ErrorCodeInfo info) {
        Session session = sessionFactory.getCurrentSession();
        session.update(info);
    }

    @Override
    public Integer save(ErrorCodeInfo info) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(info);
    }

    @Override
    public void delete(ErrorCodeInfo info) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(info);
    }

    @Override
    public ErrorCodeInfo merge(ErrorCodeInfo info) {
        Session session = sessionFactory.getCurrentSession();
        return (ErrorCodeInfo) session.merge(info);
    }

    @Override
    public void deleteByModule(String moduleName) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete ErrorCodeInfo where module = ?").setString(0, moduleName).executeUpdate();
    }
}
