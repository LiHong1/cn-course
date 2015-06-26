import cm.util.LoginHttpPostUtil;

import java.util.List;

/**
 * Created by li hong on 2015/6/2.
 */
public class LoginHttpPostUtilTest {
	public static void main(String[] args){
		List<String> cookies = LoginHttpPostUtil.getConnect("004388", "qwerty", "teacher");
	}
}
