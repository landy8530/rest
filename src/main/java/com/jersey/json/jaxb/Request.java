package com.jersey.json.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author landyl
 * @create 2018-02-1:44 PM
 */
@XmlRootElement
public class Request {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
