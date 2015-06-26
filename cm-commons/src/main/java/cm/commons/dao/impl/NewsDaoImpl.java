package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.News;
import cm.commons.dao.NewsDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * 新闻Dao实现类
 *
 * @author lzc
 */
@Repository
public class NewsDaoImpl extends NonDeletableBaseDaoImpl<News> implements NewsDao {

    /**
     * 根据id获取新闻
     */
    @Override
    public News get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        News result = (News) session.get(News.class, id);
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.NEWS_NOT_EXIST, News.class.toString() + "不存在：id=" + id);
        }
        return result;
    }

    /**
     * 根据标题、起始时间、分类、是否审核，获取新闻列表（分页）
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<News> get(String title, Calendar startDate, Calendar endDate, Integer category, Boolean approved,
                          int firstResult, int maxResults) {
        Criteria c = createCriteria(title, startDate, endDate, category, approved);
        c.setFirstResult(firstResult);
        c.setMaxResults(maxResults);
        c.addOrder(Order.desc("id"));

        return (List<News>) c.list();
    }

    /**
     * 根据标题、起始时间、分类、是否审核，获取新闻总数
     */
    @Override
    public Long getCount(String title, Calendar startDate, Calendar endDate, Integer category, Boolean approved) {
        Criteria c = createCriteria(title, startDate, endDate, category, approved);
        c.setProjection(Projections.rowCount());
        return (Long) c.uniqueResult();
    }

    /**
     * 根据标题、起始时间、分类、是否审核，获取新闻列表（分页），安装创建时间排序
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<News> getOrderByCreationDate(String title, Calendar startDate, Calendar endDate, Integer category,
                                             Boolean approved, int firstResult, int maxResults) {
        Criteria c = createCriteria(title, startDate, endDate, category, approved);
        c.setFirstResult(firstResult);
        c.setMaxResults(maxResults);
        c.addOrder(Order.desc("publishDate"));
        c.addOrder(Order.desc("createdDate"));
        return (List<News>) c.list();
    }

    /**
     * 根据标题、起始时间、分类、是否审核，生成查询准则
     *
     * @param title
     * @param startDate
     * @param endDate
     * @param category
     * @param approved
     * @return
     */
    private Criteria createCriteria(String title, Calendar startDate, Calendar endDate, Integer category,
                                    Boolean approved) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(News.class);
        if (title != null && title.length() > 0) {
            c.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
        }
        if (startDate != null) {
            c.add(Restrictions.ge("showDate", startDate));
        }
        if (endDate != null) {
            c.add(Restrictions.le("showDate", endDate));
        }
        if (category != null) {
            c.add(Restrictions.eq("category", category));
        }
        if (approved != null) {
            c.add(Restrictions.eq("approved", approved));
        }
        c.add(Restrictions.eqOrIsNull("deleted", Boolean.FALSE));
        return c;
    }
}
