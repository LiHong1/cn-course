package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.PaperDao;
import cm.entity.Paper;
import cm.entity.State;
import cm.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 试卷Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class PaperServiceImpl extends BaseServiceImpl<Paper> implements PaperService {
    @Autowired
    private PaperDao paperDao;

    @Override
    public List<Paper> findByState(State custate) {
        return paperDao.findByState(custate);
    }

    @Override
    public List<Paper> findByIds(UUID[] ids) {
        return paperDao.findByIds(ids);
    }

} 
