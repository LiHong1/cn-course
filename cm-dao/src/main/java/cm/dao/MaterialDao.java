package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Case;
import cm.entity.Material;
import cm.entity.State;

import java.util.List;

/**
 * 材料Dao接口
 *
 * @author li hong
 */
public interface MaterialDao extends BaseDao<Material> {
    /**
     * 更据关系查找
     *
     * @param state
     * @return
     */
    List<Material> findByState(State state);

    /**
     * 根据案例查找
     *
     * @param cas
     * @return
     */
    List<Material> findByCase(Case cas);

}
