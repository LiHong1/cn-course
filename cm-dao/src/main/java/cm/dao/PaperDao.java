package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Paper;
import cm.entity.State;

import java.util.List;
import java.util.UUID;

/**
 * 试卷Dao接口
 *
 * @author li hong
 */
public interface PaperDao extends BaseDao<Paper> {
    /**
     * 根据关系查找
     *
     * @param custate
     * @return
     */
    List<Paper> findByState(State custate);

    /**
     * 更据id查找
     *
     * @param ids
     * @return
     */
    List<Paper> findByIds(UUID[] ids);

    void changePaperType(UUID id);

}
