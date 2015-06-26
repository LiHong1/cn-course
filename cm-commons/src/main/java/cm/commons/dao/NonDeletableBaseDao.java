package cm.commons.dao;

import cm.commons.NonDeletableDomainObject;
import cm.commons.PageBean;
import cm.commons.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

/**
 * 逻辑删除对象的基础Dao接口
 *
 * @param <T>
 * @author lzc
 */
public interface NonDeletableBaseDao<T extends NonDeletableDomainObject> extends BaseDao<T> {

    /**
     * 获取实例，忽略是否被逻辑删除
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    T getI(Serializable id) throws BusinessException;

    /**
     * 删除（从数据库实际删除）
     *
     * @param entity
     */
    void deleteFromDb(T entity);

    /**
     * 获取所有对象（包括逻辑删除了的对象）
     *
     * @return
     */
    List<T> getAllI();

    /**
     * 所有对象数目（包括逻辑删除了的对象）
     *
     * @return
     */
    long getAllCountI();

    /**
     * 获取分页对象（包括逻辑删除了的对象）
     *
     * @param firstResult
     * @param maxResults
     * @return
     */
    PageBean<T> getPageI(int firstResult, int maxResults);
}
