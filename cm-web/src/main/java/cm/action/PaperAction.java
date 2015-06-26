package cm.action;


import cm.entity.Paper;
import cm.entity.State;
import cm.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.List;


@Controller
@Scope("prototype")
public class PaperAction extends BaseAction<Paper> {
   /* public String[] paperIds;

    public String[] getPaperIds() {
        return paperIds;
    }

    public void setPaperIds(String[] paperIds) {
        this.paperIds = paperIds;
    }*/

    public String list() {
        Teacher teacher = (Teacher) getCurrentUser();
        if (teacher != null) {
            State custate = stateService.get(teacher.getCuState().getId());
            List<Paper> papers = paperService.findByState(custate);
            ActionContext.getContext().put("papers", papers);
        }
        return "list";
    }

    public String addUI() {

        return "addUI";
    }

    public String add() {
        Teacher teacher = (Teacher) getCurrentUser();
        if (teacher != null) {
            State custate = teacher.getCuState();
            model.setState(custate);
            model.setUpdatedDate(Calendar.getInstance());
            paperService.save(model);

        }

        return "toList";
    }

    public String editUI() {
        Paper paper = paperService.get(model.getId());
        ActionContext.getContext().put("paper", paper);

        return "editUI";
    }

    public String edit() {
        Paper paper = paperService.get(model.getId());
        paper.setName(model.getName());
        paper.setAnswer(model.getAnswer());
        paper.setProblem(model.getProblem());
        paper.setUpdatedDate(Calendar.getInstance());
        paperService.update(paper);
        return "toList";
    }

    public String delete() {
        paperService.delete(model.getId());
        return "toList";
    }

 /*   public String addToExam() {
        Teacher teacher = (Teacher) getCurrentUser();
        List<Paper> papers = paperService.findByState(teacher.getCuState());
        for (Paper paper : papers) {
            boolean addExam = false;
            for (int i = 0; i < paperIds.length; i++) {
                if (paper.getId().equals(paperIds[i])) {
                    addExam = true;
                }
            }
            if (addExam && !paper.getType()) {
                paper.setType(true);
                paperService.update(paper);
            } else if (!addExam && paper.getType()) {
                paper.setType(false);
                paperService.update(paper);
            }
        }
        return "toList";
    }*/
}
