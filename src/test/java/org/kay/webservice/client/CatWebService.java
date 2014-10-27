

/**
 * CatWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package org.kay.webservice.client;

    /*
     *  CatWebService java interface
     */

    public interface CatWebService {
          

        /**
          * Auto generated method signature
          * 
                    * @param say0
                
         */

         
                     public org.kay.webservice.SayResponse say(

                        org.kay.webservice.Say say0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param say0
            
          */
        public void startsay(

            org.kay.webservice.Say say0,

            final org.kay.webservice.client.CatWebServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  show(
         org.kay.webservice.Show show2

        ) throws java.rmi.RemoteException
        
        ;

        

        
       //
       }
    