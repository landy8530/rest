package com.jersey.https;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import org.glassfish.jersey.SslConfigurator;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * @author landyl
 * @create 9:29 AM 02/23/2018
 *
 * SSL握手过程中，会对请求IP或请求域名进行校验，如果在证书信息中无法找到相关请求IP或请求域名则会报错
 * （javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException: No subject alternative names present）
 *
 */
public class SSLClient {
    public static void main(String[] args) {
        int authType =
                Integer.valueOf(Configuration.getConfig().getProperty("authority")).intValue();

        SslConfigurator sslConfig = SslConfigurator.newInstance();
        if(authType == 1){
            sslConfig.trustStoreFile(Configuration.getConfig().getProperty("clientTrustCer"))
                    .trustStorePassword(Configuration.getConfig().getProperty("clientTrustCerPwd"));
        }else if(authType == 2){
            sslConfig.keyStoreFile(Configuration.getConfig().getProperty("clientCer"))
                    .keyStorePassword(Configuration.getConfig().getProperty("clientCerPwd"))
                    .keyPassword(Configuration.getConfig().getProperty("clientKeyPwd"))
                    .trustStoreFile(Configuration.getConfig().getProperty("clientTrustCer"))
                    .trustStorePassword(Configuration.getConfig().getProperty("clientTrustCerPwd"));
        }
        sslConfig.securityProtocol(Configuration.getConfig().getProperty("protocol"));
        SSLContext sslContext = sslConfig.createSSLContext();

        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
                new HTTPSProperties(new MyHostnameVerifier(), sslContext));
        Client client = Client.create(cc);

        Integer port = Integer.valueOf(Configuration.getConfig().getProperty("serverListenPort"));

        URI uri = UriBuilder.fromUri("https://127.0.0.1/queryAddress").port(port).build();
        WebResource resource = client.resource(uri);

        Person person = new Person();
        person.setName("landy");

        ClientResponse response = resource
                .accept(MediaType.APPLICATION_JSON) //APPLICATION_XML
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, person);

        String addresses = response.getEntity(String.class);
        System.out.println(addresses);
    }
}
