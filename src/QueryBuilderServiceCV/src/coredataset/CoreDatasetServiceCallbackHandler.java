
/**
 * CoreDatasetServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package coredataset;

    /**
     *  CoreDatasetServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CoreDatasetServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CoreDatasetServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CoreDatasetServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getNormalFormVersion method
            * override this method for handling normal response from getNormalFormVersion operation
            */
           public void receiveResultgetNormalFormVersion(
                    coredataset.GetNormalFormVersionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getNormalFormVersion operation
           */
            public void receiveErrorgetNormalFormVersion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTillGranparents method
            * override this method for handling normal response from getTillGranparents operation
            */
           public void receiveResultgetTillGranparents(
                    coredataset.GetTillGranparentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTillGranparents operation
           */
            public void receiveErrorgetTillGranparents(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTermBindingVersion method
            * override this method for handling normal response from getTermBindingVersion operation
            */
           public void receiveResultgetTermBindingVersion(
                    coredataset.GetTermBindingVersionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTermBindingVersion operation
           */
            public void receiveErrorgetTermBindingVersion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShortNormalForm method
            * override this method for handling normal response from getShortNormalForm operation
            */
           public void receiveResultgetShortNormalForm(
                    coredataset.GetShortNormalFormResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShortNormalForm operation
           */
            public void receiveErrorgetShortNormalForm(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cD2CDMNEW method
            * override this method for handling normal response from cD2CDMNEW operation
            */
           public void receiveResultcD2CDMNEW(
                    coredataset.CD2CDMNEWResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cD2CDMNEW operation
           */
            public void receiveErrorcD2CDMNEW(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOWLVersion method
            * override this method for handling normal response from getOWLVersion operation
            */
           public void receiveResultgetOWLVersion(
                    coredataset.GetOWLVersionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOWLVersion operation
           */
            public void receiveErrorgetOWLVersion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for publicExecuteQuery method
            * override this method for handling normal response from publicExecuteQuery operation
            */
           public void receiveResultpublicExecuteQuery(
                    coredataset.PublicExecuteQueryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from publicExecuteQuery operation
           */
            public void receiveErrorpublicExecuteQuery(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServiceVersion method
            * override this method for handling normal response from getServiceVersion operation
            */
           public void receiveResultgetServiceVersion(
                    coredataset.GetServiceVersionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServiceVersion operation
           */
            public void receiveErrorgetServiceVersion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for expandQuery method
            * override this method for handling normal response from expandQuery operation
            */
           public void receiveResultexpandQuery(
                    coredataset.ExpandQueryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from expandQuery operation
           */
            public void receiveErrorexpandQuery(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getNextGen method
            * override this method for handling normal response from getNextGen operation
            */
           public void receiveResultgetNextGen(
                    coredataset.GetNextGenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getNextGen operation
           */
            public void receiveErrorgetNextGen(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cD2CDM method
            * override this method for handling normal response from cD2CDM operation
            */
           public void receiveResultcD2CDM(
                    coredataset.CD2CDMResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cD2CDM operation
           */
            public void receiveErrorcD2CDM(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cDM2CD method
            * override this method for handling normal response from cDM2CD operation
            */
           public void receiveResultcDM2CD(
                    coredataset.CDM2CDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cDM2CD operation
           */
            public void receiveErrorcDM2CD(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getParents method
            * override this method for handling normal response from getParents operation
            */
           public void receiveResultgetParents(
                    coredataset.GetParentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getParents operation
           */
            public void receiveErrorgetParents(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRootConcept method
            * override this method for handling normal response from getRootConcept operation
            */
           public void receiveResultgetRootConcept(
                    coredataset.GetRootConceptResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRootConcept operation
           */
            public void receiveErrorgetRootConcept(java.lang.Exception e) {
            }
                


    }
    