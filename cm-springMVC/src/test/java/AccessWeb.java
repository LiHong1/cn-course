import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessWeb {

	/**
	 * @throws Exception
	 */
	static void url() throws Exception {
		URL url = new URL("http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl=Xfz_Class_student.ascx&bjh=2111026&kch=262126&xq=2015/3/1");
		//url  = new URL("http://jwc.jxnu.edu.cn/User/default.aspx?&&code=111&uctl=MyControl%5cxfz_kcb.ascx&MyAction=Personal");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		conn.setConnectTimeout(10000);
        conn.setRequestProperty("Cookie",
				"JwOAUserSettingNew=UserNum=yuQ30uJLG/ewJgjNUNdcRw==&UserName=4xxNTTvuZ/o=&UserType=WmTb330+jk8=&UserLoginTime=2015/5/29 15:24:43; expires=Sat, 30-May-2015 07:24:43 GMT; path=/");


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

		/*Pattern namePattern = Pattern.compile("<td>姓名：</td>\t\t\t\t\t<td>(.*?)</td>");
		Matcher nameMatcher = namePattern.matcher(text);
		ArrayList<String> names = new ArrayList<String>();
		while(nameMatcher.find())
			  names.add(nameMatcher.group());
		System.out.println(names);*/
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

				System.out.println("学号："+getText(tdNodes.elementAt(4).toHtml()));
				System.out.println("姓名："+getText(tdNodes.elementAt(6).toHtml()));
				System.out.println("班级："+getText(tdNodes.elementAt(8).toHtml()));

		}
	}

	public static String getText(String html) throws Exception{
		Parser parser = new Parser(html);
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		parser.visitAllNodesWith(visitor);
		return visitor.getExtractedText();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			url();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
