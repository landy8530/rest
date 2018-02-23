package com.jersey.https;

import com.sun.jersey.api.container.ContainerFactory;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.SslConfigurator;

import javax.net.ssl.SSLContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author landyl
 * @create 9:40 AM 02/23/2018
 */
@Path("queryAddress")
public class SSLServer {

    @POST
    @Consumes(MediaType.APPLICATION_JSON) //(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Person queryAddress(Person person) {
        System.out.println("==============" + person.getName());

//        Person person = new Person();
//        person.setName(name);
        List<String> addresses = new ArrayList<String>();
        addresses.add("address1----xm chuangxindashai");
        addresses.add("address2----xm xinglinwanyihao");
        person.setAddresses(addresses);
        return person;
    }

    public static void main(String[] args) {
        Integer authType =
                Integer.valueOf(Configuration.getConfig().getProperty("authority")).intValue();

        SslConfigurator sslConfig = SslConfigurator.newInstance();
        if(authType == 1){
            sslConfig.keyStoreFile(Configuration.getConfig().getProperty("serverCer"))
                    .keyStorePassword(Configuration.getConfig().getProperty("serverCerPwd"))
                    .keyPassword(Configuration.getConfig().getProperty("serverKeyPwd"));
        }else if(authType == 2){
            sslConfig.keyStoreFile(Configuration.getConfig().getProperty("serverCer"))
                    .keyStorePassword(Configuration.getConfig().getProperty("serverCerPwd"))
                    .keyPassword(Configuration.getConfig().getProperty("serverKeyPwd"))
                    .trustStoreFile(Configuration.getConfig().getProperty("serverTrustCer"))
                    .trustStorePassword(Configuration.getConfig().getProperty("serverTrustCerPwd"));
        }
        sslConfig.securityProtocol(Configuration.getConfig().getProperty("protocol"));
        SSLContext sslContext = sslConfig.createSSLContext();

        SSLEngineConfigurator sslEngineConfig = new SSLEngineConfigurator(sslContext);
        //默认情况下是客户端模式，如果忘记修改模式
        //会抛出异常
        //javax.net.ssl.SSLProtocolException: Handshake message sequence violation, 1]
        sslEngineConfig.setClientMode(false);
        if(authType == 1)
            sslEngineConfig.setWantClientAuth(true);
        else if(authType == 2)
            sslEngineConfig.setNeedClientAuth(true);

        ResourceConfig rc = new PackagesResourceConfig("com.jersey.https");

        //使用Jersey对POJO的支持，必须设置为true
        //如果基于JAXB方式需要注释
        rc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        HttpHandler handler = ContainerFactory.createContainer(
                HttpHandler.class, rc);

        Integer port = Integer.valueOf(Configuration.getConfig().getProperty("serverListenPort"));

        URI uri = UriBuilder.fromUri("https://127.0.0.1/").port(port).build();
        try {
            HttpServer server = GrizzlyServerFactory.createHttpServer(uri, handler, true,
                    sslEngineConfig);
            server.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
