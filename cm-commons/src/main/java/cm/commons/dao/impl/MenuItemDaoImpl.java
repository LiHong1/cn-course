package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.MenuItem;
import cm.commons.dao.MenuItemDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * 菜单项Dao实现类
 *
 * @author lzc NonDeletable
 */
@Repository
public class MenuItemDaoImpl extends BaseDaoImpl<MenuItem> implements MenuItemDao {

    @Override
    public MenuItem get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        MenuItem result = (MenuItem) session.get(MenuItem.class, id);
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.MENU_ITEM_NOT_EXIST, MenuItem.class.toString() + "不存在：id="
                    + id);
        }
        return result;
    }

    @Override
    public List<MenuItem> getAll(Integer type) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<MenuItem> result = (List<MenuItem>) session.createCriteria(MenuItem.class)
                .add(Restrictions.eq("type", type)).add(Restrictions.eq("deleted", false)).addOrder(Order.asc("order"))
                .list();
        return result;
    }

    @Override
    public List<MenuItem> getAll() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<MenuItem> result = (List<MenuItem>) session.createCriteria(MenuItem.class)
                .addOrder(Order.asc("order"))
                .list();
        return result;
    }

    @Override
    public List<MenuItem> getChildren(Long parentId, Integer type) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MenuItem.class);
        if (type != null) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (parentId != null) {
            criteria.add(Restrictions.eq("parentId", parentId));
        }

        @SuppressWarnings("unchecked")
        List<MenuItem> result = (List<MenuItem>) criteria.add(Restrictions.eq("deleted", false))
                .addOrder(Order.asc("order")).list();
        return result;

    }

    @Override
    public List<MenuItem> getChildren(Long parentId, Integer type, int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MenuItem.class);
        if (type != null) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (parentId != null) {
            criteria.add(Restrictions.eq("parentId", parentId));
        }

        @SuppressWarnings("unchecked")
        List<MenuItem> result = (List<MenuItem>) criteria.add(Restrictions.eq("deleted", false))
                .setFirstResult(firstResult).setMaxResults(maxResults).addOrder(Order.asc("order")).list();
        return result;
    }

    @Override
    public long getChildrenCount(Long parentId, Integer type) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MenuItem.class);
        if (type != null) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (parentId != null) {
            criteria.add(Restrictions.eq("parentId", parentId));
        }

        return (Long) criteria.add(Restrictions.eq("deleted", false)).setProjection(Projections.rowCount())
                .uniqueResult();
    }
    public void  updateSort(UUID [] ids){
       /* for(int i=0; i<ids.length; i++){
            MenuItem menuItem = this.get(ids[i]);
            menuItem.setOrder(i);
            this.update(menuItem);
        }*/
        List<MenuItem> menuItems = this.getAll();
        for(MenuItem menuItem:menuItems){
            UUID id = menuItem.getId();
            for(int i=0; i<ids.length;i++){
                if (id.toString().equals(ids[i].toString())){
                    menuItem.setOrder(i);
                    this.update(menuItem);
                    break;
                }
            }
        }

    }

    @Override
    public List<MenuItem> getAll(String authority,Boolean asc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MenuItem.class);
        Order order;
        if (asc == true)
            order = Order.asc("order");
        else
            order = Order.desc("order");
        return criteria.add(Restrictions.eq("authority", authority)).addOrder(order).list();
    }
}
