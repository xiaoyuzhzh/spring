package org.spring.mongodb.dao;

import org.spring.mongodb.model.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by xiaoy on 2016/3/17.
 */
@NoRepositoryBean
public interface LogMessageRepositoryCustom {


    List<LogMessage> findByCriteria();

}

