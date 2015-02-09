package QueryEngine;

import java.util.Iterator;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.log4j.Logger;

public class HeaderHandler extends AbstractHandler {
	public static final Logger log = Logger.getLogger(HeaderHandler.class);

	@Override
	public InvocationResponse invoke(MessageContext mc) throws AxisFault {
		String headerName = null;
		String headerValue = null;
		
		Iterator it = mc.getEnvelope().getHeader().getChildElements();
		while (it.hasNext()) {
			OMElement o = (OMElement) it.next();
			headerName = o.getQName().getLocalPart();
			headerValue = o.getText();
			
			//System.out.println("HEADERNAME: "+headerName);
			//System.out.println("HEADERVALUE: "+headerValue);
			if (log.isDebugEnabled())
				log.debug("SOAP Message Header: " + headerName + "=" + headerValue);
			mc.setProperty(headerName, headerValue);
		}
		return InvocationResponse.CONTINUE;
	}

}