package cm.dwr;

import cm.dao.PaperDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("dwrService")
public class DwrService implements IDwrService {
    @Inject
    private PaperDao paperDao;

    public void addToExam(String id) {
        System.out.println(id);
        paperDao.changePaperType(id);
    }
}
