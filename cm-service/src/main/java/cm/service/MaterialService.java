package cm.service;


import cm.commons.service.BaseService;
import cm.entity.Case;
import cm.entity.Material;
import cm.entity.State;
import cm.entity.Teacher;

import java.io.IOException;
import java.util.List;

/**
 * 材料Service接口
 *
 * @author li hong
 */

public interface MaterialService extends BaseService<Material> {
    /**
     * 根据state查找material
     *
     * @param state
     * @return
     */
    public List<Material> findByState(State state);

    /**
     * 根据案例查找material
     *
     * @param cas
     * @return
     */
    public List<Material> findByCase(Case cas);

    /**
     * 课程材料保存
     * @param fileFileName
     * @param name
     * @param path
     * @param bytes
     * @param teacher
     * @throws IOException
     */
    public void courseMaterialSave(String fileFileName,String name,String path,String webRootPath,byte [] bytes,Teacher teacher) throws IOException;

    /**
     * 案例材料保存
     * @param fileFileName
     * @param name
     * @param path
     * @param bytes
     * @param cas
     * @throws IOException
     */
    public void caseMaterialSave(String fileFileName,String name,String path,byte [] bytes,Case cas) throws IOException;
}
