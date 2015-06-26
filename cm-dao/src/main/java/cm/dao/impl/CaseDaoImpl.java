package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.CaseDao;
import cm.entity.Case;
import cm.entity.State;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案例Dao的实现类
 *
 * @author li hong
 */
@Repository
public class CaseDaoImpl extends BaseDaoImpl<Case> implements CaseDao {
    @Override
    public List<Case> find(State state) {
        Criteria c = getSession().createCriteria(Case.class);
        c.add(Restrictions.eq("state", state));
        return c.list();
    }

}
