package cm.util;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetStudentHttpGetUtil {

	/**
	 * @throws Exception
	 */
	public static List<Student> getStudent(String cookie,String relUrl) throws Exception {
		List<Student> students = new ArrayList<Student>();
		URL url = new URL("http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl=Xfz_Class_student.ascx&bjh=2111026&kch=262126&xq=2015/3/1");
		//url  = new URL("http://jwc.jxnu.edu.cn/User/default.aspx?&&code=111&uctl=MyControl%5cxfz_kcb.ascx&MyAction=Personal");
		url = new URL("http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl="+relUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		conn.setConnectTimeout(10000);
        conn.setRequestProperty("Cookie",
				cookie);


		InputStream is = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer text = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			text.append(line);
		}
		if (is != null) {
			is.close();
		}
		if (conn != null) {
			conn.disconnect();
		}

		Parser parser = new Parser(text.toString());
		NodeFilter idFilter = new HasAttributeFilter( "id", "_ctl0_dlStudent" );
		NodeList nodeList  =parser.extractAllNodesThatMatch(idFilter);
		String s = nodeList.toHtml();

		Parser tableParser = new Parser(s);
		NodeFilter tableFilter = (NodeFilter)new TagNameFilter("table");
		NodeList tableNodes = tableParser.extractAllNodesThatMatch(tableFilter);

		for(int i = 1; i < tableNodes.size(); i++) {
			System.out.println("===============");
			Parser tdParser = new Parser(tableNodes.elementAt(i).toHtml());
			NodeFilter tdFilter = (NodeFilter)new TagNameFilter("td");
			NodeList tdNodes =tdParser.extractAllNodesThatMatch(tdFilter);
				String number = getText(tdNodes.elementAt(4).toHtml());
				String name = getText(tdNodes.elementAt(6).toHtml());
				String clazz = getText(tdNodes.elementAt(8).toHtml());
				System.out.println("学号："+getText(tdNodes.elementAt(4).toHtml()));
				System.out.println("姓名："+getText(tdNodes.elementAt(6).toHtml()));
				System.out.println("班级："+getText(tdNodes.elementAt(8).toHtml()));
			    students.add(new Student(number.trim(),clazz.trim(),name.trim()));

		}
		return students;
	}

	public static String getText(String html) throws Exception{
		Parser parser = new Parser(html);
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		parser.visitAllNodesWith(visitor);
		return visitor.getExtractedText();
	}


}
