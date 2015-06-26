package cm.handler;

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Created by li hong on 2015/5/5.
 */
public class WsAuthHandler implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
            String identifier = pc.getIdentifier();
            int usage = pc.getUsage();
            if (usage == WSPasswordCallback.USERNAME_TOKEN) {// 密钥方式USERNAME_TOKEN
                // username token pwd...
                // ▲这里的值必须和客户端设的值相同,从cxf2.4.x后校验方式改为cxf内部实现校验，不必自己比较password是否相同
                // 请参考：http://cxf.apache.org/docs/24-migration-guide.html的Runtime
                // Changes片段
                pc.setPassword("testPassword");// ▲【这里非常重要】▲
                // ▲PS 如果和客户端不同将抛出org.apache.ws.cm.security.WSSecurityException:
                // The
                // cm.security token could not be authenticated or
                // authorized异常，服务端会认为客户端为非法调用
            } else if (usage == WSPasswordCallback.SIGNATURE) {// 密钥方式SIGNATURE
                // set the password for client's keystore.keyPassword
                // ▲这里的值必须和客户端设的值相同,从cxf2.4.x后校验方式改为cxf内部实现校验，不必自己比较password是否相同;
                // 请参考：http://cxf.apache.org/docs/24-migration-guide.html的Runtime
                // Changes片段
                pc.setPassword("testPassword");// //▲【这里非常重要】▲
                // ▲PS:如果和客户端不同将抛出org.apache.ws.cm.security.WSSecurityException:The
                // cm.security token could not be authenticated or
                // authorized异常，服务端会认为客户端为非法调用
            }
            //不用做其他操作
        }
    }
}
