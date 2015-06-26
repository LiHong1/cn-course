package cm.commons.dao;

import cm.commons.DomainObject;
import cm.commons.PageBean;
import cm.commons.exception.BusinessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 基础Dao接口
 *
 * @param <T>
 * @author lzc
 */


public interface BaseDao<T extends DomainObject> {

    /**
     * 根据ID获取实例
     *
     * @param id
     * @return 返回相应的持久化实例
     * @throws BusinessException
     */
    T get(Serializable id) throws BusinessException;

    /**
     * 保存（新增）领域对象
     *
     * @param entity
     */
    Serializable save(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 删除
     *
     * @param uuid
     */
    void delete(UUID uuid);

    /**
     * 更改
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 合并对象
     *
     * @param entity
     * @return
     */
    T merge(T entity);

    /**
     * 获取所有对象
     *
     * @return
     */
    List<T> getAll();

    /**
     * 获得实体
     *
     * @param propety
     * @param value
     * @return
     */
    T get(String propety, Object value);

    /**
     * 获取所有对象（分页）
     *
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<T> getAll(int firstResult, int maxResults);

    /**
     * 所有对象数目
     *
     * @return
     */
    long getAllCount();

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<T> getPage(int currentPage, int pageSize);

    /**
     * 根据查询条件获取分页列表 jin.z add 2013.08.28
     *
     * @param currentPage
     * @param pageSize
     * @param t
     * @return
     */
    PageBean<T> getPageByExample(T t, int currentPage, int pageSize);

    /**
     * @param t
     * @param currentPage
     * @param pageSize
     * @param properties
     * @return
     */
    public PageBean<T> getPageByExample(T t, int currentPage, int pageSize, Map<String, Object> properties);

    /**
     * 根据查询条件获取分页列表并排序 jin.z add 2013.11.22
     *
     * @param currentPage
     * @param pageSize
     * @param t
     * @param order
     * @return
     */
    PageBean<T> getPageByExampleOrder(T t, int currentPage, int pageSize, Order order);

    /**
     * 根据查询条件获取查询列表
     *
     * @param t
     * @return
     */
    List<T> getByExample(T t);

    /**
     * 根据查询条件获取查询列表
     * @param t
     * @param map 关联关系
     * @return
     */
    List<T> getByExample(T t,Map<String,Object> map);

    /**
     * 批量提交
     *
     * @param items
     * @param commitCount
     */
    void save(List<T> items, int commitCount);

    /**
     * 从缓存保存到数据库
     */
    void sessionFlush();

    /**
     * 清除缓存内容
     */
    void sessionClear();

    /**
     * 获取session
     *
     * @return
     */
    public Session getSession();

    /**
     * 获取sessionFactory
     *
     * @return
     */
    public SessionFactory getSessionFactory();
}
