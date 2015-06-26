package cm.service.impl;


import cm.commons.service.impl.BaseServiceImpl;
import cm.commons.util.FileUtils;
import cm.dao.JobDao;
import cm.dao.StateDao;
import cm.entity.Job;
import cm.entity.State;
import cm.entity.Student;
import cm.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 课程Service的实现类
 *
 * @author li hong
 */

@Service
@Transactional
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {
    @Autowired
    private JobDao jobdao;
    @Autowired
    private StateDao stateDao;


    public List<Job> findJobs(Student student, State state) {
        List<Job> jobs = null;
        if (student != null && state != null)
            jobs = jobdao.findJobs(student, state);
        return jobs;
    }

    public void save(String fileFileName,String name,Student student,String path,byte []bites) throws IOException {
        State state = stateDao.get(student.getCuState().getId());
        String root = path+File.separator+"file"+File.separator+"job"+File.separator + state.getId();
        String suffix = fileFileName.substring(fileFileName.lastIndexOf("."), fileFileName.length());
        if (name == null || name.equals("")) {
            name = student.getNumber() + "_" + student.getName() + "_" + fileFileName;
        } else
            name = student.getNumber() + "_" + student.getName() + "_" + name + suffix;

            File savefile = new File(new File(root), name);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileCopyUtils.copy(bites, savefile);
            Job job = new Job();
            job.setId(UUID.randomUUID());
            job.setJobName(name);
            job.setPath("/file/job/" + state.getId() + "/");
            job.setState(state);
            job.setStudent(student);
            jobdao.save(job);

    }
} 
