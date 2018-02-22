package com.restful.entity;

/**
 * @author landyl
 * @create 2018-01-5:14 PM
 * PersonEntity实体类实现
 */
public class PersonEntity {
    private String id;
    private String name;
    private String addr;

    public PersonEntity() {
    }

    public PersonEntity(String id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
