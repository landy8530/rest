package com.jersey.json.jsonobject;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * @author landyl
 * @create 2018-02-2:16 PM
 * 与JAXB相比，结果是相同的，但是处理过程（主要是组装JSON对象）要复杂
 */
@Path("query")
public class MyResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject query(JSONObject query) {
        //{"query":"name"}
        System.out.println(query.toString());

        JSONObject resp = new JSONObject();
        try {
            resp.put("respCode", 0);
            resp.put("respDesc", query.get("query") + " for jsonObject");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public static void main(String[] args) {
        URI uri = UriBuilder.fromUri("http://127.0.0.1").port(10000).build();
        ResourceConfig rc = new PackagesResourceConfig("com.jersey.json.jsonobject");
        try {
            HttpServer server = GrizzlyServerFactory.createHttpServer(uri, rc);
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
