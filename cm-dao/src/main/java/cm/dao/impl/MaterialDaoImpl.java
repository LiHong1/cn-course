package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.MaterialDao;
import cm.entity.Case;
import cm.entity.Material;
import cm.entity.State;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 材料Dao实现类
 *
 * @author li hong
 */
@Repository
public class MaterialDaoImpl extends BaseDaoImpl<Material> implements MaterialDao {
    @Override
    public List<Material> findByState(State state) {
        Criteria c = getSession().createCriteria(Material.class);
        c.add(Restrictions.eq("state", state));
        c.addOrder(Order.asc("id"));
        return c.list();
    }

    @Override
    public List<Material> findByCase(Case cas) {
        Criteria c = getSession().createCriteria(Material.class);
        c.add(Restrictions.eq("cases", cas));
        c.addOrder(Order.asc("id"));
        return c.list();
    }

}
