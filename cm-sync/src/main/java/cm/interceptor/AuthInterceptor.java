package cm.interceptor;

/**
 * Created by li hong on 2015/5/6.
 */
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.NodeList;
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	private SAAJInInterceptor saa = new SAAJInInterceptor();
	public AuthInterceptor() {
		super(Phase.PRE_PROTOCOL);
		getAfter().add(SAAJInInterceptor.class.getName());
	}
	public void handleMessage(SoapMessage message) throws Fault {
		SOAPMessage mess = message.getContent(SOAPMessage.class);
		if (mess == null) {
			saa.handleMessage(message);
			mess = message.getContent(SOAPMessage.class);
			System.out.println(mess.toString());
		}
		SOAPHeader head = null;
		try {
			head = mess.getSOAPHeader();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (head == null) {
			return;
		}
		System.out.println(head.toString());
		NodeList nodes = head.getElementsByTagName("tns:spId");
		NodeList nodepass = head.getElementsByTagName("tns:spPassword");
		if (nodes.item(0).getTextContent().indexOf("wang") != -1) {
			if (nodepass.item(0).getTextContent().equals("can")) {
				System.out.println("认证成功");
			}
		} else {
			SOAPException soapExc = new SOAPException("认证错误");
			throw new Fault(soapExc);
		}

	}
}
