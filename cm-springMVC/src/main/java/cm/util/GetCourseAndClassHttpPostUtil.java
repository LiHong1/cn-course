package cm.util; /**
 * Created by li hong on 2015/5/28.
 */

import org.apache.commons.io.IOUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.TextExtractingVisitor;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCourseAndClassHttpPostUtil {

		public static List<Information> getCourseAndClass(String cookie,String userType){
			List<Information> informations = new ArrayList<Information>();
			try{
				// Configure and open a connection to the site you will send the request
				URL url = new URL("http://jwc.jxnu.edu.cn/User/default.aspx?&&code=111&uctl=MyControl%5cxfz_kcb.ascx&MyAction=Personal");
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				urlConnection.setConnectTimeout(10000);
				//HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// 设置doOutput属性为true表示将使用此urlConnection写入数据
				urlConnection.setDoOutput(true);
				// 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
				urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
				urlConnection.setRequestProperty("Cookie",
						cookie );
				// 得到请求的输出流对象
				OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
				// 把数据写入请求的Body
				// 把数据写入请求的Body
				out.write("__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUJNzIzMTk0NzYzD2QWAgIBD2QWCgIBDw8WAh4EVGV4dAUgMjAxNeW5tDXmnIgyOeaXpSDmmJ" +
						"%2FmnJ%2FkupQmbmJzcDtkZAIFDw8WAh8ABRjlvZPliY3kvY3nva7vvJror77nqIvooahkZAIHDw8WAh8ABSogICDmrKLov47mgq" +
						"jvvIwoMTIwODA2NjAzMCxTdHVkZW50KSDpu47nuqJkZAIKD2QWBAIBDw8WAh4ISW1hZ2VVcmwFQy4uL015Q29udHJvbC9BbGxfUGhvdG9TaG93LmFzcHg" +
						"%2FVXNlck51bT0xMjA4MDY2MDMwJlVzZXJUeXBlPVN0dWRlbnRkZAIDDxYCHwAFuCQ8ZGl2IGlkPSdtZW51UGFyZW50XzAnIGNsY" +
						"XNzPSdtZW51UGFyZW50JyBvbmNsaWNrPSdtZW51R3JvdXBTd2l0Y2goMCk7Jz7miJHnmoTkv6Hmga88L2Rpdj48ZGl2IGlkPSdtZW51R3JvdXAwJyBjbGFzcz0nbWVudUdyb3VwJz48RGl2IGNsYXNzPSdtZW51SXRlbU9uJyB0aXRsZT0n6K" +
						"%2B%2B56iL6KGoJz48YSBocmVmPSJkZWZhdWx0LmFzcHg%2FJmNvZGU9MTExJiZ1Y3RsPU15Q29udHJvbFx4Znpfa2NiLmFzY3gmTXlBY3Rpb249UGVyc29uYWwiIHRhcmdldD0ncGFyZW50Jz7or77nqIvooag8L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfln7rmnKzkv6Hmga8nPjxhIGhyZWY9Ii4uXE15Q29udHJvbFxTdHVkZW50X0luZm9yQ2hlY2suYXNweCIgdGFyZ2V0PSdfYmxhbmsnPuWfuuacrOS" +
						"%2FoeaBrzwvYT48L2Rpdj48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J%2BS%2FruaUueWvhueggSc%2BPGEgaHJlZj0iZGV" +
						"mYXVsdC5hc3B4PyZjb2RlPTExMCYmdWN0bD1NeUNvbnRyb2xccGVyc29uYWxfY2hhbmdlcHdkLmFzY3giIHRhcmdldD0ncGFyZW50Jz7kv67mlLnlr4bnoIE8L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSflrabnsY3pooToraYnPjxhIGhyZWY9ImphdmFzY3JpcHQ6T3BlbldpbmRvdygneGZ6X2J5c2guYXNjeCZBY3Rpb249UGVyc29uYWwnKTsiIHRhcmdldD0nJz7lrabnsY3pooToraY8L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfor77nqIvmiJDnu6knPjxhIGhyZWY9ImphdmFzY3JpcHQ6T3BlbldpbmRvdygneGZ6X2NqLmFzY3gmQWN0aW9uPVBlcnNvbmFsJyk7IiB0YXJnZXQ9Jyc" +
						"%2B6K%2B%2B56iL5oiQ57upPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0nMjAxNeW5tOS4iuWNiuW5tOe7k" +
						"%2BS4muihpeiAg%2BaKpeWQjSc%2BPGEgaHJlZj0iamF2YXNjcmlwdDpPcGVuV2luZG93KCdKWUJLX1hTX0luZGV4LmFzY3gnKTs" +
						"iIHRhcmdldD0nJz4yMDE15bm05LiK5Y2K5bm057uT5Lia6KGl6ICD5oql5ZCNPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n5omL5py65Y" +
						"%2B356CBJz48YSBocmVmPSIuLlxNeUNvbnRyb2xcUGhvbmUuYXNweCIgdGFyZ2V0PSdfYmxhbmsnPuaJi%2BacuuWPt%2BeggTwvYT48L2Rpdj48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J" +
						"%2BWutumVv%2BeZu%2BW9lSc%2BPGEgaHJlZj0iZGVmYXVsdC5hc3B4PyZjb2RlPTIwMyYmdWN0bD1NeUNvbnRyb2xcSnpfc3R1ZGVudHNldHRpbmcuYXNjeCIgdGFyZ2V0PSdwYXJlbnQnPuWutumVv" +
						"%2BeZu%2BW9lTwvYT48L2Rpdj48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J%2BWPjOS4k%2BS4muWPjOWtpuS9jeivvueoi" +
						"%2BWuieaOkuihqCc%2BPGEgaHJlZj0iLi5cTXlDb250cm9sXERlenlfa2IuYXNweCIgdGFyZ2V0PSdfYmxhbmsnPuWPjOS4k%2BS4muWPjOWtpuS9jeivvueoi" +
						"%2BWuieaOkuihqDwvYT48L2Rpdj48L2Rpdj48ZGl2IGlkPSdtZW51UGFyZW50XzEnIGNsYXNzPSdtZW51UGFyZW50JyBvbmNsaWN" +
						"rPSdtZW51R3JvdXBTd2l0Y2goMSk7Jz7mraPlpKflvq7or748L2Rpdj48ZGl2IGlkPSdtZW51R3JvdXAxJyBjbGFzcz0nbWVudUdyb3VwJz48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J" +
						"%2BeUs%2Bivt%2BiAg%2BivlSc%2BPGEgaHJlZj0iZGVmYXVsdC5hc3B4PyZjb2RlPTIwNyYmdWN0bD1NeUNvbnRyb2xcV0tfUGFwZXJMaXN0LmFzY3giIHRhcmdldD0ncGFyZW50Jz7nlLPor7fogIPor5U8L2E" +
						"%2BPC9kaXY%2BPC9kaXY%2BPGRpdiBpZD0nbWVudVBhcmVudF8yJyBjbGFzcz0nbWVudVBhcmVudCcgb25jbGljaz0nbWVudUdyb3VwU3dpdGNoKDIpOyc" +
						"%2B5YWs5YWx5pyN5YqhPC9kaXY%2BPGRpdiBpZD0nbWVudUdyb3VwMicgY2xhc3M9J21lbnVHcm91cCc%2BPERpdiBjbGFzcz0nb" +
						"WVudUl0ZW0nIHRpdGxlPSfln7nlhbvmlrnmoYgnPjxhIGhyZWY9ImRlZmF1bHQuYXNweD8mY29kZT0xMDQmJnVjdGw9TXlDb250cm9sXGFsbF9qeGpoLmFzY3giIHRhcmdldD0ncGFyZW50Jz7ln7nlhbvmlrnmoYg8L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfor77nqIvkv6Hmga8nPjxhIGhyZWY9ImRlZmF1bHQuYXNweD8mY29kZT0xMTYmJnVjdGw9TXlDb250cm9sXGFsbF9jb3Vyc2VzZWFyY2guYXNjeCIgdGFyZ2V0PSdwYXJlbnQnPuivvueoi" +
						"%2BS%2FoeaBrzwvYT48L2Rpdj48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J%2BW8gOivvuWuieaOkic%2BPGEgaHJlZj0iLi5cTXlDb250cm9sXFB1YmxpY19La2FwLmFzcHgiIHRhcmdldD0nX2JsYW5rJz7lvIDor77lronmjpI8L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSflrabnlJ%2Fkv6Hmga8nPjxhIGhyZWY9ImRlZmF1bHQuYXNweD8mY29kZT0xMTkmJnVjdGw9TXlDb250cm9sXGFsbF9zZWFyY2hzdHVkZW50LmFzY3giIHRhcmdldD0ncGFyZW50Jz7lrabnlJ" +
						"%2Fkv6Hmga88L2E%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfmlZnlt6Xkv6Hmga8nPjxhIGhyZWY9ImRl" +
						"ZmF1bHQuYXNweD8mY29kZT0xMjAmJnVjdGw9TXlDb250cm9sXGFsbF90ZWFjaGVyLmFzY3giIHRhcmdldD0ncGFyZW50Jz7mlZnlt6Xkv6Hmga88L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfnn63kv6HlubPlj7AnPjxhIGhyZWY9ImRlZmF1bHQuYXNweD8mY29kZT0xMjImJnVjdGw9TXlDb250cm9sXG1haWxfbGlzdC5hc2N4IiB0YXJnZXQ9J3BhcmVudCc" +
						"%2B55%2Bt5L%2Bh5bmz5Y%2BwPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n5pWZ5a6k5pWZ5a2m5a6J5o6SJz48YSBocmVmPSIuLlxNeUNvbnRyb2xccHVibGljX2NsYXNzcm9vbS5hc3B4IiB0YXJnZXQ9J19ibGFuayc" +
						"%2B5pWZ5a6k5pWZ5a2m5a6J5o6SPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n5Y%2BM5a2m5L2N6K%2B%2" +
						"B56iL5oiQ57upJz48YSBocmVmPSJqYXZhc2NyaXB0Ok9wZW5XaW5kb3coJ2RlenlfY2ouYXNjeCZBY3Rpb249UGVyc29uYWwnKTsiIHRhcmdldD0nJz7lj4zlrabkvY3or77nqIvmiJDnu6k8L2E" +
						"%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfmr5XkuJrnlJ%2Flm77lg4%2Fph4fpm4bkv6Hmga%2FmoKHlr7knPjxhIGhyZWY9Ii4uXE15Q29udHJvbFxUWENKX0luZm9yQ2hlY2suYXNweCIgdGFyZ2V0PSdfYmxhbmsnPuavleS4mueUn" +
						"%2BWbvuWDj%2BmHh%2BmbhuS%2FoeaBr%2BagoeWvuTwvYT48L2Rpdj48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J%2Bacn" +
						"%2Bacq%2BaIkOe7qeafpeivoic%2BPGEgaHJlZj0iamF2YXNjcmlwdDpPcGVuV2luZG93KCd4ZnpfVGVzdF9jai5hc2N4Jyk7IiB0YXJnZXQ9Jyc" +
						"%2B5pyf5pyr5oiQ57up5p%2Bl6K%2BiPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n5pyf5pyr5oiQ57up5p" +
						"%2Bl5YiG55Sz6K%2B3Jz48YSBocmVmPSJqYXZhc2NyaXB0Ok9wZW5XaW5kb3coJ0Nmc3FfU3R1ZGVudC5hc2N4Jyk7IiB0YXJnZXQ9Jyc" +
						"%2B5pyf5pyr5oiQ57up5p%2Bl5YiG55Sz6K%2B3PC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n6KGl57yT6ICD5a6J5o6SJz48YSBocmVmPSJqYXZhc2NyaXB0Ok9wZW5XaW5kb3coJ3hmel9UZXN0X0JISy5hc2N4Jyk7IiB0YXJnZXQ9Jyc" +
						"%2B6KGl57yT6ICD5a6J5o6SPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n5a2m5Lmg6Zeu562UJz48YSBocmVmPSJkZWZhdWx0LmFzcHg" +
						"%2FJmNvZGU9MTU5JiZ1Y3RsPU15Q29udHJvbFxBbGxfU3R1ZHlfTGlzdC5hc2N4IiB0YXJnZXQ9J3BhcmVudCc%2B5a2m5Lmg6Ze" +
						"u562UPC9hPjwvZGl2PjwvZGl2PjxkaXYgaWQ9J21lbnVQYXJlbnRfMycgY2xhc3M9J21lbnVQYXJlbnQnIG9uY2xpY2s9J21lbnVHcm91cFN3aXRjaCgzKTsnPuaVmeWtpuS" +
						"%2FoeaBrzwvZGl2PjxkaXYgaWQ9J21lbnVHcm91cDMnIGNsYXNzPSdtZW51R3JvdXAnPjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0n572R5LiK6K" +
						"%2BE5pWZJz48YSBocmVmPSJqYXZhc2NyaXB0Ok9wZW5XaW5kb3coJ3BqX3N0dWRlbnRfaW5kZXguYXNjeCcpOyIgdGFyZ2V0PScnPue9keS4iuivhOaVmTwvYT48L2Rpdj48RGl2IGNsYXNzPSdtZW51SXRlbScgdGl0bGU9J" +
						"%2BaVmeWKoeaEj%2BingeeusSc%2BPGEgaHJlZj0iLi4vRGVmYXVsdC5hc3B4P0FjdGlvbj1BZHZpc2UiIHRhcmdldD0nX2JsYW5rJz7mlZnliqHmhI" +
						"%2Fop4HnrrE8L2E%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfmnJ%2FmnKvogIPor5XlronmjpInPjxhIG" +
						"hyZWY9ImRlZmF1bHQuYXNweD8mY29kZT0xMjkmJnVjdGw9TXlDb250cm9sXHhmel90ZXN0X3NjaGVkdWxlLmFzY3giIHRhcmdldD0ncGFyZW50Jz7mnJ" +
						"%2FmnKvogIPor5XlronmjpI8L2E%2BPC9kaXY%2BPERpdiBjbGFzcz0nbWVudUl0ZW0nIHRpdGxlPSfovoXkv67lj4zkuJPkuJrlj4zlrabkvY3miqXlkI0nPjxhIGhyZWY9ImphdmFzY3JpcHQ6T3BlbldpbmRvdygnRGV6eV9ibS5hc2N4Jyk7IiB0YXJnZXQ9Jyc" +
						"%2B6L6F5L%2Bu5Y%2BM5LiT5Lia5Y%2BM5a2m5L2N5oql5ZCNPC9hPjwvZGl2PjxEaXYgY2xhc3M9J21lbnVJdGVtJyB0aXRsZT0nMjAxNOe6p" +
						"%2BacrOenkeWtpueUn%2Bi9rOS4k%2BS4muaKpeWQjSc%2BPGEgaHJlZj0iLi5cTXlDb250cm9sXHp6eV9zdHVkZW50X3NxLmFzc" +
						"HgiIHRhcmdldD0nX2JsYW5rJz4yMDE057qn5pys56eR5a2m55Sf6L2s5LiT5Lia5oql5ZCNPC9hPjwvZGl2PjwvZGl2PmQCDA9kFgJmD2QWDAIBDw8WAh8ABR7msZ" +
						"%2Fopb%2FluIjojIPlpKflrablrabnlJ%2For77ooahkZAIDDw8WAh8ABXjnj63nuqflkI3np7DvvJo8VT4xMue6p%2BWNk%2Bi2iuW3peeoi" +
						"%2BW4iO%2B8iOeJqeiBlOe9ke%2B8ieePrTwvVT7jgIDjgIDlrablj7fvvJo8VT4xMjA4MDY2MDMwPC91PuOAgOOAgOWnk%2BWQje" +
						"%2B8mjx1Pum7jue6ojwvdT5kZAIFDxAPFgYeDURhdGFUZXh0RmllbGQFDOWtpuacn%2BWQjeensB4ORGF0YVZhbHVlRmllbGQFDO" +
						"W8gOWtpuaXpeacnx4LXyFEYXRhQm91bmRnZBAVCA8xNS0xNuesrDHlrabmnJ8PMTQtMTXnrKwy5a2m5pyfDzE0LTE156ysMeWtpuacnw8xMy0xNOesrDLlrabmnJ8PMTMtMTTnrKwx5a2m5pyfDzEyLTEz56ysMuWtpuacnw8xMi0xM" +
						"%2BesrDHlrabmnJ8PMTEtMTLnrKwy5a2m5pyfFQgQMjAxNS85LzEgMDowMDowMBAyMDE1LzMvMSAwOjAwOjAwEDIwMTQvOS8xIDA" +
						"6MDA6MDAQMjAxNC8zLzEgMDowMDowMBAyMDEzLzkvMSAwOjAwOjAwEDIwMTMvMy8xIDA6MDA6MDAQMjAxMi85LzEgMDowMDowMBA" +
						"yMDEyLzMvMSAwOjAwOjAwFCsDCGdnZ2dnZ2dnZGQCCQ8PFgIeB1Zpc2libGVoZGQCCg88KwALAQAPFggeCERhdGFLZXlzFgAeC18hSXRlbUNvdW50Av" +
						"%2F%2F%2F%2F8PHhVfIURhdGFTb3VyY2VJdGVtQ291bnQC%2F%2F%2F%2F%2Fw8eCVBhZ2VDb3VudGZkZAILDzwrAAsBAA8WCh8GFgAfBwIDHwgCAx8JAgEfBWdkFgJmD2QWBgIBD2QWDGYPDxYCHwAFCjI2MjA3MSAgICBkZAIBDw8WAh8ABRXltYzlhaXlvI" +
						"%2Fns7vnu5%2Forr7orqFkZAICDw8WAh8ABTDmi4bnj63mlZnlt6Xlj7bnu6fljY4jLjHnj60gICAgICAgICAgICAgICAgICAgIC" +
						"BkZAIDDw8WAh8ABQnlj7bnu6fljY5kZAIED2QWAmYPFQFtPGEgaHJlZj1qYXZhc2NyaXB0Ok9wZW5XaW5kb3coJ1hmel9DbGFzc19zdHVkZW50LmFzY3gmYmpoPTAwMjY0MiQxJmtjaD0yNjIwNzEmeHE9MjAxNS8zLzEnKTs" +
						"%2B5p%2Bl55yL5ZCN5Y2VPC9hPmQCBQ9kFgJmDxUBajxhIHRhcmdldD1fYmxhbmsgaHJlZj0nLi4vd3NrdC9Db3Vyc2VTZXR0aW5nLmFzcHg" +
						"%2FYmpoPTAwMjY0MiQxJmtjaD0yNjIwNzEmeHE9MjAxNS8zLzEnPuivvueoi%2BiuqOiuuuWMujwvYT5kAgIPZBYMZg8PFgIfAAUKMjYyMTI2ICAgIGRkAgEPDxYCHwAFFeaXoOe6v" +
						"%2BS8oOaEn%2BWZqOe9kee7nGRkAgIPDxYCHwAFNDEy57qn5Y2T6LaK5bel56iL5biI77yI54mp6IGU572R77yJ54%2BtICAgICA" +
						"gICAgICAgICBkZAIDDw8WAh8ABQnpm7fpnIfmmKVkZAIED2QWAmYPFQFsPGEgaHJlZj1qYXZhc2NyaXB0Ok9wZW5XaW5kb3coJ1h" +
						"mel9DbGFzc19zdHVkZW50LmFzY3gmYmpoPTIxMTEwMjYma2NoPTI2MjEyNiZ4cT0yMDE1LzMvMScpOz7mn6XnnIvlkI3ljZU8L2E" +
						"%2BZAIFD2QWAmYPFQFpPGEgdGFyZ2V0PV9ibGFuayBocmVmPScuLi93c2t0L0NvdXJzZVNldHRpbmcuYXNweD9iamg9MjExMTAyNiZrY2g9MjYyMTI2JnhxPTIwMTUvMy8xJz7or77nqIvorqjorrrljLo8L2E" +
						"%2BZAIDD2QWDGYPDxYCHwAFCjI2MjEzMSAgICBkZAIBDw8WAh8ABRXnianogZTnvZHkv6Hmga%2FlronlhahkZAICDw8WAh8ABTQxMue6p" +
						"%2BWNk%2Bi2iuW3peeoi%2BW4iO%2B8iOeJqeiBlOe9ke%2B8ieePrSAgICAgICAgICAgICAgZGQCAw8PFgIfAAUJ5byg5YWJ5rK" +
						"zZGQCBA9kFgJmDxUBbDxhIGhyZWY9amF2YXNjcmlwdDpPcGVuV2luZG93KCdYZnpfQ2xhc3Nfc3R1ZGVudC5hc2N4JmJqaD0yMTExMDI2JmtjaD0yNjIxMzEmeHE9MjAxNS8zLzEnKTs" +
						"%2B5p%2Bl55yL5ZCN5Y2VPC9hPmQCBQ9kFgJmDxUBaTxhIHRhcmdldD1fYmxhbmsgaHJlZj0nLi4vd3NrdC9Db3Vyc2VTZXR0aW5nLmFzcHg" +
						"%2FYmpoPTIxMTEwMjYma2NoPTI2MjEzMSZ4cT0yMDE1LzMvMSc%2B6K%2B%2B56iL6K6o6K665Yy6PC9hPmRk8OYnIIpaJIMJ8jKAZN" +
						"%2FCLzrZjXHVzwzehL%2FkQck3d1s%3D&__EVENTVALIDATION=%2FwEWCwKU0uSxAgKKhuW9AQLeg4%2BHCQL9g%2FeyBwLItunkDwLvttGQDQLItv0EAu" +
						"%2B25bAOAoaZ%2FbIBAqWZ5e4OAubhijP52VN9zJQwhFF5zDBgbdOXOrHsgEIXzb2qgZkBmapN9Q%3D%3D&_ctl1%3AddlSterm=2015" +
						"%2F3%2F1+0%3A00%3A00&_ctl1%3AbtnSearch=%E7%A1%AE%E5%AE%9A" );


				out.flush();
				out.close();

				// 从服务器读取响应
				InputStream inputStream = urlConnection.getInputStream();
				String encoding = urlConnection.getContentEncoding();
				if(encoding == null){
					encoding = "UTF-8";
				}
				String text = IOUtils.toString(inputStream, encoding);
				Parser parser = new Parser(text);
				NodeFilter idFilter;
				if(userType.equals("student"))
				idFilter = new HasAttributeFilter( "id", "_ctl1_dgStudentLesson" );
				else  idFilter = new HasAttributeFilter( "id", "_ctl1_dgTeacherLesson" );
				NodeList nodeList  =parser.extractAllNodesThatMatch(idFilter);
				String s = nodeList.toHtml();

				Parser trParser = new Parser(s);
				NodeFilter trFilter = (NodeFilter)new TagNameFilter("tr");
				NodeList trNodes = trParser.extractAllNodesThatMatch(trFilter);


					for(int i = 1; i < trNodes.size(); i++) {
						{

							Parser tdParser = new Parser(trNodes.elementAt(i).toHtml());
							NodeFilter tdFilter = (NodeFilter)new TagNameFilter("td");
							NodeList tdNodes = tdParser.extractAllNodesThatMatch(tdFilter);
								if(userType.equals("student")){
									String courseNumber = getText(tdNodes.elementAt(0).toHtml());
									String courseName = getText(tdNodes.elementAt(1).toHtml());
									String className = getText(tdNodes.elementAt(2).toHtml());
									String teacherName = getText(tdNodes.elementAt(3).toHtml());
									System.out.println("课程号："+getText(tdNodes.elementAt(0).toHtml()));
									System.out.println("课程名："+getText(tdNodes.elementAt(1).toHtml()));
									System.out.println("班级名："+getText(tdNodes.elementAt(2).toHtml()));
									System.out.println("教师：" + getText(tdNodes.elementAt(3).toHtml()));
									String a = tdNodes.elementAt(4).toHtml().toString();
									System.out.println(a);
									Pattern urlPattern = Pattern.compile("(.*?)");
									Matcher urlMatcher = urlPattern.matcher(a);
									urlMatcher.find();
									String getStudentUrl = urlMatcher.group();
									informations.add(new Information(courseNumber,className,courseName,getStudentUrl));
								}else{
									String courseName = getText(tdNodes.elementAt(0).toHtml());
									String className = getText(tdNodes.elementAt(1).toHtml());
									System.out.println("课程名称："+courseName);
									System.out.println("班级名称："+className);
									String a = tdNodes.elementAt(2).toHtml().toString();
									Pattern urlPattern = Pattern.compile("'(.*?)'");
									Matcher urlMatcher = urlPattern.matcher(a);
									urlMatcher.find();
									String getStudentUrl = urlMatcher.group();
									informations.add(new Information(className,courseName,getStudentUrl));
						           }
						}
					}

			}catch(Exception e){
				System.out.println(e);
			}
			return informations;
		}
	public static String getText(String html) throws Exception{
		Parser parser = new Parser(html);
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		parser.visitAllNodesWith(visitor);
		return visitor.getExtractedText();
	}


}
