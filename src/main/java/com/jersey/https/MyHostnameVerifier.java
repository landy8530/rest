package com.jersey.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author landyl
 * @create 9:37 AM 02/23/2018
 *
 * SSL握手过程中，会对请求IP或请求域名进行校验，如果在证书信息中无法找到相关请求IP或请求域名则会报错
 * （javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException: No subject alternative names present）
 *
 * 这里实现自己的校验逻辑（如果请求的IP为127.0.0.1或请求的域名为localhost，则直接通过校验）以覆盖默认逻辑
 *
 */
public class MyHostnameVerifier implements HostnameVerifier {

    public boolean verify(String hostname, SSLSession session) {
        if("127.0.0.1".equals(hostname) || "localhost".equals(hostname) )
            return true;
        else
            return false;
    }
}
