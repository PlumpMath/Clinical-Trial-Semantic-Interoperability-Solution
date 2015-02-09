

/**
 * DefaultAcceptMessageService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.mirth.connect.connectors.ws;

    /*
     *  DefaultAcceptMessageService java interface
     */

    public interface DefaultAcceptMessageService {
          

        /**
          * Auto generated method signature
          * 
                    * @param acceptMessage0
                
         */

         
                     public com.mirth.connect.connectors.ws.AcceptMessageResponseE acceptMessage(

                        com.mirth.connect.connectors.ws.AcceptMessageE acceptMessage0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param acceptMessage0
            
          */
        public void startacceptMessage(

            com.mirth.connect.connectors.ws.AcceptMessageE acceptMessage0,

            final com.mirth.connect.connectors.ws.DefaultAcceptMessageServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    