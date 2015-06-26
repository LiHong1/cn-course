package cm.action;

import cm.commons.key.KeyFactory;
import cm.entity.Person;
import cm.service.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;


public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model;
    @Resource
    protected StudentService studentService;
    @Resource
    protected TeacherService teacherService;

//    @Override
//    public String execute() throws Exception {
//        Person user = getCurrentUser();
//        String modelName = model.getClass().getSimpleName();
//        if (user.getClass().getSimpleName().equals(modelName)) {
//            modelName = modelName.toLowerCase();
//            ActionContext.getContext().put("menu", "${pageContext.request.contextPath}/" + modelName + "_menu.action");
//            ActionContext.getContext().put("right", "${pageContext.request.contextPath}/" + modelName + "_right.action");
//            ActionContext.getContext().put("top1", "${pageContext.request.contextPath}/" + modelName + "_top.action");
//            return "success";
//        }
//        return "urlError";
//    }
    @Resource
    protected CourseService courseService;
    @Resource
    protected ClazzService classService;
    @Resource
    protected ManageService manageService;
    @Resource
    protected StateService stateService;
    @Resource
    protected ApplicationService applicationService;
    @Resource
    protected CaseService caseService;
    @Resource
    protected MaterialService materialService;
    @Resource
    protected PaperService paperService;
    @Resource
    protected JobService jobService;
    @Resource
    protected KeyFactory keyFactory;
    @Resource
    protected TopicService topicService;
    @Resource
    protected TimeLogService timeLogService;
    @Resource
    protected ReplyService replyService;
    protected int pageNum = 1; // 当前页
    protected int pageSize = 3; // 每页显示多少条记录

    public BaseAction() {
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            // 通过反射创建model的实例
            model = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ============== 分页用的参数 =============

    public T getModel() {
        return model;
    }

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    protected Person getCurrentUser() {
        return (Person) ActionContext.getContext().getSession().get("user");
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
