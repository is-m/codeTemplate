package com.m.study.codeTemplate.spring.data.mongo;

import com.m.study.codeTemplate.spring.data.mongo.domain.User;
import com.m.study.codeTemplate.spring.data.mongo.repository.CustomUserRepository;
import com.m.study.codeTemplate.spring.data.mongo.repository.UserRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    private static final Logger log = LoggerFactory.getLogger(MongoTest.class);

    @Autowired
    CustomUserRepository customUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;
/*
    @Before
    public  void setup() {
        if (userRepository.count() < 100000) {
            User user = new User();
            Map<String, Object> props = new HashMap<>();
            props.put("country", "china");
            user.setProperties(props);

            // 100W
            for (int i = 0; i < 1000000; i++) {
                user.setUid(i + "");
                user.setName("user " + i);
                userRepository.save(user);
            }
        }
    }*/

    @Test
    public void findBySkipPage() {
        log.info("findBySkipPage");
        long count = mongoTemplate.count(new Query(), User.class);

        log.info("count is {}", count);

        Query query = new Query();
        query.limit(10).skip(10).with(Sort.by("_id"));
        query.fields().include("uid").exclude("_id"); // 包含uid ， 排除默认id
        mongoTemplate.find(query, Document.class,"user").forEach(System.out::println);
    }

    @Test
    public void findByLastIdPage() {
        log.info("findByLastIdPage");
        // 增量查询，返回指定字段
        String startObjectId = "5e00e0da7cf5aa20b0ef5b03"; // 起始行的 mongo _id
        Query query = Query.query(Criteria.where("_id").gt(new ObjectId(startObjectId))).limit(10).with(Sort.by("_id"));
        query.fields().include("uid").exclude("_id"); // 包含uid ， 排除默认id
        // 以文档的方式查询集合
        mongoTemplate.find(query, Document.class,"user").forEach(System.out::println);
    }

    @AfterTestMethod
    public void destroy() {
        log.info("test run completed");

    }
}
