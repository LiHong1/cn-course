package cm.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Calendar;

@WebService
public interface StudentWebservice {
    //加入WebParam注解，以保证xml文件中参数名字的正确性
    //如果没有加注解，参数将被命名为arg0
    public String getAll();

    //获取最近更新了的数据
    public String getLatestUpdate(@WebParam(name = "latestUpdateTime") Calendar c);


}
