package com.m.study.codeTemplate.spring.data.mongo.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class User {

    @Indexed
    private String uid;

    @Indexed
    private String name;

    private Map<String, Object> properties;

    private org.bson.Document extra;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public org.bson.Document getExtra() {
        return extra;
    }

    public void setExtra(org.bson.Document extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", properties=" + properties +
                ", extra=" + extra +
                '}';
    }
}
