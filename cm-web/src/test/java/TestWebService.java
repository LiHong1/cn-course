import cm.dto.StudentDto;
import cm.webservice.StudentWebservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestWebService {
    public static void main(String[] args) throws ParseException, DocumentException, IOException {
        //创建WebService客户端代理工厂
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        //注册WebService接口
        factory.setServiceClass(StudentWebservice.class);
        //设置WebService地址
        factory.setAddress("http://localhost:8080/cm-web/webservice/studentService");
        StudentWebservice studentService = (StudentWebservice) factory.create();


        Calendar c = Calendar.getInstance();
        //PropertyUtils.update();
        //String time = PropertyUtils.getValue("time");
        String time = null;
        if (time == null) {
            getAll(studentService);
        } else {
            getLatestUpdate(c, studentService);
            upload(c);
        }
        //PropertyUtils.setValue("time", c.getInstance().getTime().toString());
        //PropertyUtils.save();


        System.out.println("invoke webservice...");
    }

    //需要更新的数据上传  c为上次更新的时间
    @SuppressWarnings("deprecation")
    public static void upload(Calendar c) throws DocumentException, FileNotFoundException {
        List<StudentDto> students = null;
        Date createdDate, updatedDate;
        for (int i = 0; i < students.size(); i++) {
            StudentDto studentDto = students.get(i);
            if (studentDto.getUpdatedDate().after(c.getTime())) {
                if (studentDto.getCreatedDate().after(c.getTime()) && studentDto.getOperate() != 2) {//客户端增加数据
                    studentDto.setOperate(0);
                } else {
                    //客户端修改数据或是删除数据
                }

                System.out.println(studentDto.getId() + "|" + studentDto.getName() + "|" + studentDto.getOperate());

            }
        }
        ;
    }

    //获取所有的数据
    public static void getAll(StudentWebservice studentService) throws IOException {
        String json = studentService.getAll();
        System.out.println(json);
    }

    //获取最近更新的数据
    public static void getLatestUpdate(Calendar c, StudentWebservice studentService) throws DocumentException, IOException {
        String json = studentService.getLatestUpdate(c);
        List<StudentDto> students = getStudents(json);
    }


    public static List<StudentDto> getStudents(String json) {
        Gson g = new Gson();
        List<StudentDto> students = g.fromJson(json, new TypeToken<List<StudentDto>>() {
        }.getType());
        return students;
    }


    public static void save(List<StudentDto> students) throws IOException {
        Gson g = new Gson();
        System.out.println(g.toJson(students));
    }

}
