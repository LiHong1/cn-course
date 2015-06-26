package cm.commons.service.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.AllPurposeList;
import cm.commons.bean.ListItem;
import cm.commons.dao.AllPurposeListDao;
import cm.commons.dao.ListItemDao;
import cm.commons.exception.BusinessException;
import cm.commons.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * 列表Service实现类
 *
 * @author lzc
 */
@Service
public class ListServiceImpl extends BaseServiceImpl<AllPurposeList> implements ListService {
    @Autowired
    private AllPurposeListDao listDao;
    @Autowired
    private ListItemDao listItemDao;

//    @Autowired
//    public void setListDao(AllPurposeListDao listDao) {
//        this.listDao = listDao;
//        setBaseDao(listDao);
//    }


    @Transactional
    public AllPurposeList get(String name) {
        return listDao.get(name);
    }


    @Transactional
    public AllPurposeList create(String name) {
        if (listDao.exists(name)) {
            throw new BusinessException(CommonsErrorCode.LIST_EXISTS, "列表已经存在：name=" + name);
        }
        AllPurposeList allPurposeList = new AllPurposeList();
        allPurposeList.setName(name);
        allPurposeList.setCreatedDate(Calendar.getInstance());

        listDao.save(allPurposeList);
        return allPurposeList;
    }


    @Transactional
    public void update(ListItem item) {
        listItemDao.update(item);
    }
}
