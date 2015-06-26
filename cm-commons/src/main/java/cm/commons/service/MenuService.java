package cm.commons.service;

import cm.commons.Page;
import cm.commons.bean.MenuItem;

import java.util.List;
import java.util.UUID;

/**
 * 菜单服务接口
 *
 * @author leizhenchun
 */

public interface MenuService extends BaseService<MenuItem> {
    /**
     * 新增菜单项
     *
     * @param creatorId
     * @param name
     * @return 菜单项Id
     */
    Long create(UUID creatorId, String name);

    /**
     * 根据类型获取所有有效菜单项
     *
     * @return
     */
    List<MenuItem> getAll(Integer type);

    /**
     * 根据父Id和类型查询子菜单项
     *
     * @param parentId
     * @param type
     * @return
     */
    List<MenuItem> getChildren(Long parentId, Integer type);

    /**
     * 根据父Id查询菜单项
     *
     * @param parentId
     * @return
     */
    List<MenuItem> getChildren(Long parentId);

    /**
     * 根据父Id查询菜单项
     *
     * @param parentId
     * @return
     */
    Page<MenuItem> getChildren(Long parentId, int firstResult, int maxResults);

    /**
     * 根据类型获取Root菜单项
     *
     * @param type
     * @return
     */
    List<MenuItem> getRoot(Integer type);

    /**
     * 获取Root菜单项
     *
     * @return
     */
    List<MenuItem> getRoot();

    /**
     * 获取Root菜单项
     *
     * @return
     */
    long getRootCount();

    /**
     * 更改排序
     * @param ids
     */
    void updateSort(UUID []ids);

    /**
     * 获取相应角色的菜单
     * @param authority
     * @return
     */
    List<MenuItem> getAll(String authority,Boolean asc);

    /***
     * 获取所有的菜单
     * @return
     */
    List<MenuItem> getAll();

}
