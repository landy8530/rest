package com.jersey.https;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author landyl
 * @create 9:27 AM 02/23/2018
 * 1.Person类是基于JAXB方式
 * 2.Person类是基于pojo方式
 */
//@XmlRootElement
public class Person {

    private String name;
    private List<String> addresses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
