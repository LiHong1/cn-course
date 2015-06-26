package cm.commons.service.impl;

import cm.commons.MenuType;
import cm.commons.Page;
import cm.commons.bean.MenuItem;
import cm.commons.dao.MenuItemDao;
import cm.commons.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 菜单Service实现类
 *
 * @author lzc
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuItem> implements MenuService {
    @Autowired
    private MenuItemDao menuDao;

//    @Autowired
//    public void setMenuDao(MenuItemDao menuDao) {
//        this.menuDao = menuDao;
//        setBaseDao(menuDao);
//    }


    @Transactional
    public Long create(UUID creatorId, String name) {
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setCreatedDate(Calendar.getInstance());
        item.setCreatedBy(creatorId);

        return (Long) menuDao.save(item);
    }


    @Transactional
    public Long create(MenuItem menu) {
        menu.setCreatedDate(Calendar.getInstance());
        return (Long) menuDao.save(menu);
    }


    @Transactional(readOnly = true)
    public List<MenuItem> getAll(Integer type) {
        return menuDao.getAll(type);
    }


    @Transactional(readOnly = true)
    public List<MenuItem> getChildren(Long parentId, Integer type) {
        return menuDao.getChildren(parentId, type);
    }


    @Transactional(readOnly = true)
    public List<MenuItem> getChildren(Long parentId) {
        return menuDao.getChildren(parentId, null);
    }


    @Transactional(readOnly = true)
    public Page<MenuItem> getChildren(Long parentId, int firstResult, int maxResults) {
        return new Page<MenuItem>(menuDao.getChildren(parentId, null, firstResult, maxResults),
                menuDao.getChildrenCount(parentId, null), firstResult, maxResults);
    }


    @Transactional(readOnly = true)
    public List<MenuItem> getRoot(Integer type) {
        return menuDao.getChildren(MenuType.ROOT_ID, type);
    }


    @Transactional(readOnly = true)
    public List<MenuItem> getRoot() {
        return menuDao.getChildren(MenuType.ROOT_ID, null);
    }


    @Transactional(readOnly = true)
    public long getRootCount() {
        return menuDao.getChildrenCount(null, null);
    }

    @Override
    public void updateSort(UUID[] ids) {
        menuDao.updateSort(ids);
    }

    @Override
    public List<MenuItem> getAll(String authority,Boolean asc){
        return menuDao.getAll(authority,asc);
    }

   /* @Cacheable(value = "menuCache",cacheManager = "cacheManager")*/
    public List<MenuItem> getAll(){
        return  menuDao.getAll();
    }
}
