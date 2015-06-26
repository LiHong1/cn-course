package cm.commons.service;

import cm.commons.DomainObject;
import cm.commons.PageBean;
import cm.commons.exception.BusinessException;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T extends DomainObject> {

    /**
     * 根据ID获取实例
     *
     * @param id
     * @return 返回相应的持久化实例
     * @throws BusinessException
     */
    T get(Serializable id) throws BusinessException;

    /**
     * 新增
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
     * @param id
     */
    void delete(Serializable id);

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
     * 获取所有对象（分页）
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<T> getAll(int currentPage, int pageSize);

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
     * @param curentPage
     * @param pageSize
     * @param t
     * @return
     */
    PageBean<T> getPageByExample(T t, int curentPage, int pageSize);

    PageBean<T> getPageByExample(T t, int curentPage, int pageSize, Map<String, Object> properties);

    /**
     * 根据查询条件获取分页列表并排序
     *
     * @param curentPage
     * @param pageSize
     * @param t
     * @param order
     * @return
     */
    PageBean<T> getPageByExampleOrder(T t, int curentPage, int pageSize, Order order);

    /**
     * 根据查询条件获取查询列表
     *
     * @param t
     * @return
     */
    List<T> getByExample(T t);

    /**
     * 根据查询条件获取查询列表
     *
     * @param t
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
     * 根据属性值获得实体
     *
     * @param propety
     * @param value
     * @return
     */
    public T find(String propety, Object value);

}
