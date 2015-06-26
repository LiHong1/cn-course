package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Paper;
import cm.entity.State;

import java.util.List;
import java.util.UUID;

/**
 * 试卷Service接口
 *
 * @author li hong
 */
public interface PaperService extends BaseService<Paper> {
    /**
     * 更据对应关系查找实体
     *
     * @param custate
     * @return
     */
    List<Paper> findByState(State custate);

    /**
     * 根据id查找实体
     *
     * @param ids
     * @return
     */
    List<Paper> findByIds(UUID[] ids);

}
