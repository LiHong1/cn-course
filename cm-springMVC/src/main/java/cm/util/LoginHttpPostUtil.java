package cm.util; /**
 * Created by li hong on 2015/5/28.
 */

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginHttpPostUtil {

		public static List<String> getConnect(String number,String password,String userType){
			String param;
			List<String> list = new ArrayList<String>();
			try{

				// Configure and open a connection to the site you will send the request
				URL url = new URL("http://jwc.jxnu.edu.cn/Default_Login.aspx?preurl=");
				URLConnection urlConnection = url.openConnection();
				//HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// 设置doOutput属性为true表示将使用此urlConnection写入数据
				urlConnection.setDoOutput(true);
				urlConnection.setDoInput(true);
				// 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
				urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
				// 得到请求的输出流对象
				OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());

				if(userType.equals("teacher")){
					param = "rblUserType=Teacher&ddlCollege=180+++++&StuNum=&TeaNum="+number+"&Password="+password+"&login=%E7%99%BB%E5%BD%95";
				}else if(userType.equals("student")){
					param = "rblUserType=Student&ddlCollege=180+++++&StuNum="+number+"&TeaNum=&Password="+password+"&login=%E7%99%BB%E5%BD%95";
				}else{
					param = "rblUserType=Student&ddlCollege=180+++++&StuNum=1208066030&TeaNum=&Password=921026&login=%E7%99%BB%E5%BD%95";
				}
				// 把数据写入请求的Body
				out.write("__EVENTTARGET=&__EVENTARGUMENT=&__LASTFOCUS=&__VIEWSTATE=%2FwEPDwUJNjk1MjA1MTY0D2QWAgIBD2QWBAIBD2QWB" +
						"GYPEGRkFgFmZAIBDxAPFgYeDURhdGFUZXh0RmllbGQFDOWNleS9jeWQjeensB4ORGF0YVZhbHVlRmllbGQFCeWNleS9jeWPtx4LXyFEYXRhQm91bmRnZBAVPwnkv53ljavlpIQJ6LSi5Yqh5aSEEui0ouaUv" +
						"%2BmHkeiejeWtpumZohLmiJDkurrmlZnogrLlrabpmaIS5Z%2BO5biC5bu66K6%2B5a2m6ZmiEuWIneetieaVmeiCsuWtpumZogzkvKDmkq3lrabpmaIh5b2T5Luj5b2i5oCB5paH6Im65a2m56CU56m25Lit5b" +
						"%2BDD%2BWFmuWKnuOAgeagoeWKngnmoaPmoYjppoYV5Zyw55CG5LiO546v5aKD5a2m6ZmiG%2BWvueWkluiBlOe7nOS4juaOpeW%2BheS4reW" +
						"%2Fgxjpq5jnrYnmlZnogrLnoJTnqbbkuK3lv4MY5Zu96ZmF5ZCI5L2c5LiO5Lqk5rWB5aSEEuWbvemZheaVmeiCsuWtpumZog%2FlkI7li6Tkv53pmpzlpIQY5YyW5bel5byA5Y" +
						"%2BR56CU56m25Lit5b%2BDEuWMluWtpuWMluW3peWtpumZognln7rlu7rlpIQb6K6h566X5py65L%2Bh5oGv5bel56iL5a2m6ZmiKuaxn" +
						"%2Bilv%2BecgeWFieeUteWtkOS4jumAmuS%2FoemHjeeCueWunumqjOWupA%2FmlZnluIjmlZnogrLlpIQJ5pWZ5Yqh5aSEDOaVmeiCsuWtpumZog" +
						"%2FlhpvkuovmlZnnoJTlrqQS56eR5oqA44CB56S%2B56eR5aSEEuenkeWtpuaKgOacr%2BWtpumZohjor77nqIvkuI7mlZnlrabnoJTnqbbmiYAY56a76YCA5LyR5bel5L2c5Yqe5YWs5a6kEueQhuWMlua1i" +
						"%2BivleS4reW%2Fgxvljoblj7LmlofljJbkuI7ml4XmuLjlrabpmaIM576O5pyv5a2m6ZmiEuWFjei0ueW4iOiMg%2BeUn%2BmZohLkurrmiY3kuqTmtYHkuK3lv4MJ5Lq65LqL5aSEDOi9r" +
						"%2BS7tuWtpumZognllYblrabpmaIb6K6%2B5aSH5LiO5a6e6aqM5a6k566h55CG5aSEEueUn%2BWRveenkeWtpuWtpumZohLluIjotYTln7norq3kuK3lv4Mb5pWw5a2m5LiO5L" +
						"%2Bh5oGv56eR5a2m5a2m6ZmiEue0oOi0qOaVmeiCsuS4reW%2FgwzkvZPogrLlrabpmaIJ5Zu%2B5Lmm6aaGD%2BWkluWbveivreWtpumZoh7lpJbnsY3kuJPlrrbnrqHnkIbmnI3liqHkuK3lv4MS5aSW6K" +
						"%2Bt6ICD6K%2BV5Lit5b%2BDD%2BaWh%2BWMlueglOeptumZognmloflrabpmaIb54mp55CG5LiO6YCa5L%2Bh55S15a2Q5a2m6ZmiHueOsOS7o" +
						"%2BaVmeiCsuaKgOacr%2BW6lOeUqOS4reW%2FgxXmoKHlj4vlt6XkvZzlip7lhazlrqQV5qCh5Zut572R566h55CG5Lit5b%2BDDOW" +
						"%2Fg%2BeQhuWtpumZohLmlrDpl7vkv6Hmga%2FkuK3lv4MP5a2m5oql5p2C5b%2BX56S%2BD%2BWtpuenkeW7uuiuvuWkhAnlrabnlJ" +
						"%2FlpIQM56CU56m255Sf6ZmiEuiJuuacr%2BeglOeptuS4reW%2Fgwzpn7PkuZDlrabpmaIP5oub55Sf5bCx5Lia5aSEDOaUv%2BazleWtpumZohU" +
						"%2FCDE4MCAgICAgCDE3MCAgICAgCDY4MDAwICAgCDQ1MCAgICAgCDYzMDAwICAgCDgyMDAwICAgCDY0MDAwICAgCDM4MiAgICAgC" +
						"DEzMCAgICAgCDEwOSAgICAgCDQ4MDAwICAgCDEzMiAgICAgCDM5MCAgICAgCDE2MCAgICAgCDY5MDAwICAgCDg3MDAwICAgCDM2N" +
						"SAgICAgCDYxMDAwICAgCDE0NCAgICAgCDYyMDAwICAgCDM4MSAgICAgCDI1MCAgICAgCDI0MDAwICAgCDUwMDAwICAgCDM3MDAwI" +
						"CAgCDE0MCAgICAgCDgxMDAwICAgCDMyNCAgICAgCDEwNCAgICAgCDMyMCAgICAgCDU4MDAwICAgCDY1MDAwICAgCDU3MDAwICAgC" +
						"DMzMCAgICAgCDE1MCAgICAgCDY3MDAwICAgCDU0MDAwICAgCDM2MCAgICAgCDY2MDAwICAgCDMxMCAgICAgCDU1MDAwICAgCDM4M" +
						"DAwICAgCDU2MDAwICAgCDI5MCAgICAgCDUyMDAwICAgCDg5MDAwICAgCDMwMCAgICAgCDM1MCAgICAgCDUxMDAwICAgCDYwMDAwI" +
						"CAgCDM2MSAgICAgCDE4OSAgICAgCDMwNCAgICAgCDQ5MDAwICAgCDEwNiAgICAgCDQyMCAgICAgCDEzNiAgICAgCDExMCAgICAgC" +
						"DE5MCAgICAgCDE0NiAgICAgCDUzMDAwICAgCDQ0MCAgICAgCDU5MDAwICAgFCsDP2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ" +
						"2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2RkAgMPDxYCHgdWaXNpYmxlaGRkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQpSZW1lbmJlck1lPqVKoKlH" +
						"%2BuZtJyFiPk2sXwWyCEa8KunRIk0Qb9MuwbM%3D&__EVENTVALIDATION=%2FwEWSgKl16TtBgLr6%2B%2FkCQK3yfbSBAKDspbeCQL21fViApC695MMAsjQmpEOAsjQpo4OAv3S2u0DAq" +
						"%2FRroAOAv3S9t4DAqPW8tMDAv3S6tEDAoSwypgNAsjQtoIOArWVmJEHAr%2FR2u0DAsaw5o0NAo7QnpwOAsjQooMOAv3S3ugDAqPW5toDArfW7mMC" +
						"%2FdL%2B0AMCvJDK9wsC%2FdLy0wMCw5aHjwMC6dGugA4C%2BdHq0QMC3NH61QMCntDm2gMCyNCqhQ4Co9b%2B0AMC8pHSiQwCvJDaiwwCjtCyhw4C3NHa7QMC" +
						"%2FdLu3AMC3NHm2gMCjtC2gg4CyNCugA4C%2FdLm2gMC3NHq0QMCjtCigw4C%2FdLi3wMCjtC%2BhA4C3NHu3AMCntDa7QMC3NHi3wMC6dGenA4C3NHy0wMCo9be6AMCjtC6mQ4CjtCugA4C3NH" +
						"%2B0AMC%2FdL61QMCw5bP%2FgICtZX4qQcC8pHaiwwCv9He6AMCqvCJ9QoCr9Gyhw4CqvCF%2FgoCyNC%2BhA4CyNCenA4CqvC58QoC3NH23gMCr9GqhQ4C3NHe6AMC" +
						"%2BeuUqg4C2tqumwgC0sXgkQ8CuLeX%2BQECj8jxgAoRQcvVbiCAwXxJW8rVX6vT%2F4ywlHmBLMltZTQFLhvEWw%3D%3D&" +
						param );
				out.flush();
				out.close();

				// 从服务器读取响应
				InputStream inputStream = urlConnection.getInputStream();
				String encoding = urlConnection.getContentEncoding();
				if(encoding == null){
					encoding = "UTF-8";
				}
				String body = IOUtils.toString(inputStream, encoding);

				System.out.println(urlConnection.getHeaderField("set-cookie"));

				list.add(urlConnection.getHeaderField("set-cookie"));
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
	}
