package com.m.study.codeTemplate.spring.data.mongo.repository;

import com.m.study.codeTemplate.spring.data.mongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


}
