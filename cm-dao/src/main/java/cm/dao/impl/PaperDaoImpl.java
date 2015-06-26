package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.PaperDao;
import cm.entity.Paper;
import cm.entity.State;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 材料Dao实现类
 *
 * @author li hong
 */
@Repository
public class PaperDaoImpl extends BaseDaoImpl<Paper> implements PaperDao {
    @Override
    public List<Paper> findByState(State custate) {
        Criteria c = getSession().createCriteria(Paper.class);
        c.add(Restrictions.eq("state", custate));
        c.addOrder(Order.asc("id"));
        return c.list();
    }

    @Override
    public List<Paper> findByIds(UUID[] ids) {
        Criteria c = getSession().createCriteria(Paper.class);
        c.add(Restrictions.in("id", ids));
        return c.list();
    }

    @Override
    public void changePaperType(UUID id) {
        Paper paper = this.get(id);
        if (paper != null) {
            paper.setType(!paper.getType());
            super.update(paper);
        }
    }
}
 