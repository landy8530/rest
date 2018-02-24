package com.jaxws.server;

import javax.xml.ws.Endpoint;

/**
 * @author landyl
 * @create 2:48 PM 02/24/2018
 * 程序启动后，访问http://127.0.0.1:8888/Plus?wsdl即可查看自动生成的WSDL文件
 */
public class Server {

    public static void main(String[] args) {
        PlusImpl plus = new PlusImpl();
        String addr = "http://127.0.0.1:8888/Plus";
        Endpoint.publish(addr, plus);
    }

}
