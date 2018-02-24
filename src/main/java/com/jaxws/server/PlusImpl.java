package com.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author landyl
 * @create 2:46 PM 02/24/2018
 */
@WebService(endpointInterface = "com.jaxws.server.Plus",serviceName = "plusImplService",portName = "plusImplPort",targetNamespace = "http://www.landy.com/ws")
public class PlusImpl implements Plus {
    @WebMethod
    public int plus(@WebParam(name="x") int x,@WebParam(name="y") int y) {
        return x + y;
    }
}
