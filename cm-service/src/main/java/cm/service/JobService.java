package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Job;
import cm.entity.State;
import cm.entity.Student;

import java.io.IOException;
import java.util.List;

/**
 * 作业Service接口
 *
 * @author li hong
 */
public interface JobService extends BaseService<Job> {
    /**
     * 根据学生，对应关系查找实体
     *
     * @param student
     * @param state
     * @return
     */
    List<Job> findJobs(Student student, State state);

    /**
     *
     * @param fileFileName 上传时的真实名字
     * @param name  保存名
     * @param student
     * @param path
     * @param bites
     * @throws IOException
     */
    public void save(String fileFileName,String name,Student student,String path,byte []bites) throws IOException;
}
