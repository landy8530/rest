package com.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author landyl
 * @create 2:44 PM 02/24/2018
 * JAX-WS服务端采用注释描述WebService，不再依赖WebService描述文件
   使用JDK1.6_45（JDK1.5中不包含所需类）
 */
@WebService(name = "plus", targetNamespace = "http://www.landy.com/ws")
public interface Plus {
    @WebMethod
    int plus(@WebParam(name="x") int x,@WebParam(name="y") int y);
}
