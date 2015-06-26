package cm.commons.dao.impl;

import cm.commons.CalendarRange;
import cm.commons.CommonsErrorCode;
import cm.commons.bean.OperationLogItem;
import cm.commons.dao.OperationLogDao;
import cm.commons.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 操作日志Dao实现类
 *
 * @author lzc
 */
@Repository
public class OperationLogDaoImpl extends BaseDaoImpl<OperationLogItem> implements OperationLogDao {
    private static final int BATCH_SAVE_SIZE = 20;
    private static final Log LOG = LogFactory.getLog(OperationLogDaoImpl.class);

    @Override
    @Transactional
    public Long save(OperationLogItem item) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(item);
    }

    @Override
    @Transactional
    public void save(List<OperationLogItem> items) {
        int count = 0;
        LOG.debug("保存操作日志开始：");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            for (OperationLogItem item : items) {
                session.save(item);
                count++;
                if (count % BATCH_SAVE_SIZE == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.debug(e.getMessage());
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        LOG.debug("保存操作日志结束：");
    }

    @Override
    @Transactional
    public OperationLogItem get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        OperationLogItem result = (OperationLogItem) session.get(OperationLogItem.class, id);
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.OPERATION_LOG_NOT_EXIST, OperationLogItem.class.toString()
                    + "操作日志不存在：id=" + id);
        }
        return result;
    }

    @Override
    @Transactional
    public void clearLog() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete OperationLogItem").executeUpdate();
    }

    @Override
    @Transactional
    public List<OperationLogItem> getAll(Long operatorId, CalendarRange cr, int firstResult, int maxResults) {
        Criteria criteria = createCriteria(operatorId, cr);
        @SuppressWarnings("unchecked")
        List<OperationLogItem> loglist = (List<OperationLogItem>) criteria.setMaxResults(maxResults)
                .addOrder(Order.desc("id")).list();
        return loglist;
    }

    @Override
    @Transactional
    public long getAllCount(Long operatorId, CalendarRange cr) {
        Criteria criteria = createCriteria(operatorId, cr);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    private Criteria createCriteria(Long operatorId, CalendarRange cr) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OperationLogItem.class);
        if (operatorId != null) {
            criteria.add(Restrictions.eq("operatorId", operatorId));
        }
        if (cr != null && cr.getStart() != null) {
            criteria.add(Restrictions.ge("operationDateEnd", cr.getStart()));
        }
        if (cr != null && cr.getEnd() != null) {
            criteria.add(Restrictions.le("operationDateEnd", cr.getEnd()));
        }

        return criteria;
    }

}
