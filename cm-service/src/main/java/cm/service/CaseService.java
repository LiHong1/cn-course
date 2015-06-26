package cm.service;


import cm.commons.service.BaseService;
import cm.entity.Case;
import cm.entity.State;

import java.util.List;

/**
 * 案例Service接口
 *
 * @author li hong
 */

public interface CaseService extends BaseService<Case> {
    /**
     * 查找案例
     *
     * @param state
     * @return
     */
    public List<Case> find(State state);
}
