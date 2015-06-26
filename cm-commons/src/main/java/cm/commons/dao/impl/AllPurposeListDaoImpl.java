package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.AllPurposeList;
import cm.commons.dao.AllPurposeListDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 列表Dao实现类
 *
 * @author lzc
 */
@Repository
public class AllPurposeListDaoImpl extends BaseDaoImpl<AllPurposeList> implements AllPurposeListDao {

    @Override
    public AllPurposeList get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        AllPurposeList result = (AllPurposeList) session.get(AllPurposeList.class, id);
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.LIST_NOT_EXIST, "列表不存在：id=" + id);
        }
        return result;
    }

    @Override
    public AllPurposeList get(String name) {
        Session session = sessionFactory.getCurrentSession();
        AllPurposeList result = (AllPurposeList) session.createCriteria(AllPurposeList.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.LIST_NOT_EXIST, "列表不存在：name=" + name);
        }
        return result;
    }

    @Override
    public boolean exists(String name) {
        Session session = sessionFactory.getCurrentSession();
        Long result = (Long) session.createQuery("select l.id from AllPurposeList l where l.name = ? ")
                .setString(0, name).uniqueResult();
        return result == null ? false : true;
    }

}
