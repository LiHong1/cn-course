package cm.action;


import cm.auth.AuthClass;
import cm.entity.Case;
import cm.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 案例Action
 *
 * @author li hong
 */

@Controller
@Scope("prototype")
@AuthClass("teacher")
public class CaseAction extends BaseAction<Case> {

    /**
     * 列出老师
     *
     * @return
     */
    public String list() {
        Teacher teacher = (Teacher) getCurrentUser();
        List<Case> caseList = caseService.find(teacher.getCuState());
        ActionContext.getContext().put("caseList", caseList);
        return "list";
    }

    public String addUI() {
        return "addUI";
    }

    public String add() {
        Teacher teacher = (Teacher) getCurrentUser();
        model.setState(teacher.getCuState());
        caseService.save(model);
        return "toList";
    }

    public String updateUI() {
        ActionContext.getContext().put("Case", caseService.get(model.getId()));
        return "updateUI";
    }

    public String update() {
        Case cas = caseService.get(model.getId());
        cas.setContent(model.getContent());
        cas.setTitle(model.getTitle());
        caseService.update(cas);
        ActionContext.getContext().put("Case", cas);
        return "toList";
    }

    public String delete() {
        caseService.delete(model.getId());
        return "toList";
    }
}
