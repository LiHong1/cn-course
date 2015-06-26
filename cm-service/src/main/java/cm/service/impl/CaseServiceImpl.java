package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.CaseDao;
import cm.entity.Case;
import cm.entity.State;
import cm.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 案例Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class CaseServiceImpl extends BaseServiceImpl<Case> implements CaseService {

    @Autowired
    private CaseDao caseDao;

    @Override
    public List<Case> find(State state) {
        if (state != null) {
            return caseDao.find(state);
        }
        return null;
    }


} 
