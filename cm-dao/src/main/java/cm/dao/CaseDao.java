package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Case;
import cm.entity.State;

import java.util.List;

/**
 * 案例Dao接口
 *
 * @author li hong
 */
public interface CaseDao extends BaseDao<Case> {
    /**
     * 查找案例
     *
     * @param state
     * @return
     */
    List<Case> find(State state);
}
