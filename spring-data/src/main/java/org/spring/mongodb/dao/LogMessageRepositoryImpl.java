package org.spring.mongodb.dao;

import org.spring.mongodb.model.LogMessage;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by xiaoy on 2016/3/17.
 */
@NoRepositoryBean
public class LogMessageRepositoryImpl implements LogMessageRepositoryCustom{


    @Resource
    private MongoTemplate mongoTemplate;

    public List<LogMessage> findByCriteria(){

        return mongoTemplate.find(query(where("level").regex("Info")),LogMessage.class);
    }

}
