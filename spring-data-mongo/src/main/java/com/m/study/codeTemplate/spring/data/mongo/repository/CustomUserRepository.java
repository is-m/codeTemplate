package com.m.study.codeTemplate.spring.data.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public void findPageList(){

    }

    public void findPageIdList(){

    }



}
