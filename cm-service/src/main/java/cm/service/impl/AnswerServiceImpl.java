//package cm.service.impl;
//
//import cm.commons.service.impl.BaseServiceImpl;
//import cm.dao.AnswerDao;
//import cm.entity.Answer;
//import cm.entity.Paper;
//import cm.entity.Student;
//import cm.service.AnswerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * AnswerService实现类
// *
// * @author li hong
// */
//@Service
//@Transactional
//public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements AnswerService {
//    @Autowired
//    private AnswerDao answerDao;
//
//    public Answer find(Paper paper, Student student) {
//        // TODO Auto-generated method stub
//        return answerDao.find(paper, student);
//    }
//
//    public Answer find(List<Paper> papers, Student student) {
//        // TODO Auto-generated method stub
//        return answerDao.find(papers, student);
//    }
//
//}
