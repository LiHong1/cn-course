//package cm.entity;
//
//import cm.commons.DomainObject;
//
//import javax.persistence.*;
//
///**
// * 试卷答案实体
// */
//@Entity
//@Table(name = "T_ANSWER")
//public class Answer extends DomainObject {
//    private static final long serialVersionUID = 1L;
//    //学生
//    private Student student;
//    //试卷
//    private Paper paper;
//    //答案
//    private String answer;
//
//    @ManyToOne
//    @JoinColumn(name = "STUDENT_ID")
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "PAPER_ID")
//    public Paper getPaper() {
//        return paper;
//    }
//
//    public void setPaper(Paper paper) {
//        this.paper = paper;
//    }
//    @Column(name="ANSWER")
//    public String getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(String answer) {
//        this.answer = answer;
//    }
//
//}
