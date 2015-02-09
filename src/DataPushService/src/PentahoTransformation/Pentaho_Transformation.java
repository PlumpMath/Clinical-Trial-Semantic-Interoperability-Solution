package PentahoTransformation;

import java.rmi.RemoteException;

import com.mirth.connect.connectors.ws.AcceptMessage;
import com.mirth.connect.connectors.ws.AcceptMessageE;
import com.mirth.connect.connectors.ws.AcceptMessageResponse;
import com.mirth.connect.connectors.ws.AcceptMessageResponseE;
import com.mirth.connect.connectors.ws.DefaultAcceptMessageServiceStub;

public class Pentaho_Transformation {

		

	public synchronized static String processETL (String msg) {
				
		try {
			DefaultAcceptMessageServiceStub st = new DefaultAcceptMessageServiceStub();
		
			AcceptMessageE acceptMessage2 = new AcceptMessageE();
			// 
			AcceptMessage param = new AcceptMessage();
			param.setArg0(msg);
			
			acceptMessage2.setAcceptMessage(param);
			
		
			AcceptMessageResponseE res = st.acceptMessage(acceptMessage2 );
			
			AcceptMessageResponse res_final = res.getAcceptMessageResponse();
			
			return res_final.get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
