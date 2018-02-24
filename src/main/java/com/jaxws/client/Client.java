package com.jaxws.client;

import com.jaxws.server.Plus;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author landyl
 * @create 2:51 PM 02/24/2018
 */
public class Client {
    public static void main(String[] args) throws Exception {
        QName serviceName = new QName("http://www.landy.com/ws", "plusImplService");
        QName portName = new QName("http://www.landy.com/ws", "plusImplPort");

        String addr = "http://127.0.0.1:8888/Plus?wsdl";
        URL url = new URL(addr);

        Service service = Service.create(url, serviceName);
        Plus plus = service.getPort(portName,Plus.class);

        //使用Plus plus = service.getPort(Plus.class)方法时，客户端调用时报错：
//        Exception in thread "main" javax.xml.ws.WebServiceException: Undefined port type: {http://server.sean.com/}Plus
//        解决方式一：
//        如客户端示例，将Plus plus = service.getPort(Plus.class)修改为Plus plus = service.getPort(portName,Plus.class)
//        解决方式二：
//        修改PlusImpl类的@WebService标记修改为@WebService(endpointInterface="com.sean.server.Plus")
//        参考：http://stackoverflow.com/questions/13417454/javax-xml-ws-webserviceexception-undefined-port-type-java-struts-soap-wsdl
//        Plus plus = service.getPort(Plus.class);

        int result = plus.plus(100, 200);
        System.out.println("result:" + result);
    }
}
