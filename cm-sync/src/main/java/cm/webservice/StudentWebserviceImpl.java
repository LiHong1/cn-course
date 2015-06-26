package cm.webservice;

import cm.dto.StudentDto;
import cm.entity.Student;
import cm.service.StudentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//@WebService注解让CXF知道我们希望使用哪个接口来创建WSDL,，本例中就是StudentService接口。
@WebService(endpointInterface = "cm.webservice.StudentWebservice")
public class StudentWebserviceImpl implements StudentWebservice {
    @Autowired
    private StudentService studentDao;

    public String getAll() {
        List<Student> students = studentDao.getAll();
        List<Student> gstudetns = new ArrayList<Student>();
        for (Student student : students) {
            Student s = new Student();
            s.setNumber(student.getNumber());
            s.setName(student.getName());
            s.setPassword(student.getPassword());
            gstudetns.add(s);
        }
        System.out.println("in webservice");
        Gson g = new Gson();
        System.out.println(g.toJson(gstudetns));
        return g.toJson(gstudetns);

    }

    public String getLatestUpdate(Calendar c) {
        List<StudentDto> students = studentDao.getLatestUpdate(c);
        Gson g = new Gson();
        return g.toJson(students);
    }

}
