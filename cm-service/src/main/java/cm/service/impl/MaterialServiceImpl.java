package cm.service.impl;


import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.CaseDao;
import cm.dao.MaterialDao;
import cm.dao.StateDao;
import cm.entity.Case;
import cm.entity.Material;
import cm.entity.State;
import cm.entity.Teacher;
import cm.service.MaterialService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 材料Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class MaterialServiceImpl extends BaseServiceImpl<Material> implements MaterialService {
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private CaseDao caseDao;
    @Autowired
    private StateDao stateDao;
    @Override
    public List<Material> findByState(State state) {

        return materialDao.findByState(state);
    }

    @Override
    public List<Material> findByCase(Case cas) {

        return materialDao.findByCase(cas);
    }

    public void caseMaterialSave(String fileFileName,String name,String path,byte [] bytes,Case cas) throws IOException {
        String root = path+"/file/case/" + cas.getId() + "/";
        if(name == null||name.equals(""))
            name = fileFileName;
        else name = name + FilenameUtils.getExtension(fileFileName);
            Material material = new Material();
            material.setId(UUID.randomUUID());
            material.setName(name);
            material.setOriginalName(fileFileName);
            material.setCases(cas);
            material.setType(getType(fileFileName));
            material.setMaterialPath(root);
            materialDao.save(material);
            File savefile = new File(new File(root), material.getId() + material.getSuffix());
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileCopyUtils.copy(bytes, savefile);
            cas.getMaterials().add(material);
            caseDao.update(cas);
    }

    public void courseMaterialSave(String fileFileName,String name,String path,String webRootPath,byte [] bytes,Teacher teacher) throws IOException {
        State state = stateDao.get(teacher.getCuState().getId());
        //绝对路径
        String root = path+"/file/learnMaterial/" + state.getId();
        //相对路径
        String re_path = "/file/learnMaterial/" + state.getId() + "/";
            Material material = new Material();
            material.setId(UUID.randomUUID());
            Long type = getType(fileFileName);
            if (type == 0) {
                root = webRootPath+"/file/video/" + state.getId();
                re_path = "/file/video/" + state.getId() + "/";
            }else if(type == 1){
                root = webRootPath+re_path;
            }
            if (name == null || name.equals(""))
                name = fileFileName;
            else name = name+"." + FilenameUtils.getExtension(fileFileName);
            material.setMaterialPath(re_path);
            material.setName(name);
            material.setOriginalName(fileFileName);
            material.setState(state);
            material.setType(getType(fileFileName));
            material.setSuffix("." + FilenameUtils.getExtension(fileFileName));
            materialDao.save(material);
            state.getMaterials().add(material);
            stateDao.update(state);
            File savefile = new File(new File(root), material.getId() + material.getSuffix());
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            FileCopyUtils.copy(bytes,savefile);
    }
    public long getType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (suffix.equals(".flv") || suffix.equals(".mp4"))
            return 0L;
        if (suffix.equals(".doc") || suffix.equals(".pdf") || suffix.equals(".txt") || suffix.equals(".ppt")
                || suffix.equals(".pptx") || suffix.equals(".docx"))
            return 1L;
        return 2L;
    }

} 
