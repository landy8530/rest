package com.jersey.json.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author landyl
 * @date 2/23/2018 9:19 AM
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
