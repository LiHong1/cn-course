package cm.commons.dao;

import cm.commons.bean.MenuItem;

import java.util.List;
import java.util.UUID;

/**
 * 菜单项Dao接口
 *
 * @author lzc
 */
public interface MenuItemDao extends BaseDao<MenuItem> {

    /**
     * 根据类型，获取所有菜单项
     *
     * @param type
     * @return
     */
    List<MenuItem> getAll(Integer type);

    /**
     * 根据父菜单Id、类型，获取所有子菜单列表（分页）
     *
     * @param parentId
     * @param type
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<MenuItem> getChildren(Long parentId, Integer type, int firstResult, int maxResults);

    /**
     * 根据父菜单Id、类型，获取所有子菜单列表
     *
     * @param parentId
     * @param type
     * @return
     */
    List<MenuItem> getChildren(Long parentId, Integer type);

    /**
     * 根据父菜单Id、类型，获取所有子菜单总数
     *
     * @param parentId
     * @param type
     * @return
     */
    long getChildrenCount(Long parentId, Integer type);

    /**
     * 更改排序
     * @param ids
     */
    void updateSort(UUID [] ids);

    /**
     * 获取相应权限的菜单
     * @param authority
     * @return
     */
    List<MenuItem> getAll(String authority,Boolean asc);

}
