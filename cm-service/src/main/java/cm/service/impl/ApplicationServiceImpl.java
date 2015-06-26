package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.ApplicationDao;
import cm.entity.Application;
import cm.entity.State;
import cm.entity.Student;
import cm.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生申请Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class ApplicationServiceImpl extends BaseServiceImpl<Application> implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public List<Application> findByState(State state) {
        return applicationDao.findByState(state);
    }

    @Override
    public Application find(State state, Student student) {
        return applicationDao.find(state, student);
    }

    @Override
    public List<Application> find(State cuState, Long type) {
        if (type == -1L)
            return applicationDao.findByState(cuState);
        return applicationDao.find(cuState, type);
    }
} 
