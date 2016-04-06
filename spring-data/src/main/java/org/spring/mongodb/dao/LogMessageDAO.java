package org.spring.mongodb.dao;

import org.apache.commons.lang3.StringUtils;
import org.spring.mongodb.model.LogMessage;
import org.spring.mongodb.pojo.qo.LogMessageQO;
import org.spring.mongodb.pojo.qo.PageQO;
import org.spring.mongodb.pojo.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by xiaoy on 2016/3/17.
 */
@Component
public class LogMessageDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    protected Criteria getCriteria(LogMessageQO logMessageQO){
        Criteria criteria = new Criteria();
        if(logMessageQO!=null){

            if(logMessageQO.getEnvId()!=null){
                criteria.and("envId").is(logMessageQO.getEnvId());
            }

            if(logMessageQO.getLevel()!=null){
                criteria.and("level").regex(logMessageQO.getLevel(),"i");
            }

            if(logMessageQO.getProjectId()!=null){
                criteria.and("projectId").is(logMessageQO.getProjectId());
            }

            if(StringUtils.isNotBlank(logMessageQO.getTitle())){
                criteria.and("title").regex(logMessageQO.getTitle());
            }

            if(StringUtils.isNotBlank(logMessageQO.getContent())){
                criteria.and("content").regex(logMessageQO.getContent());
            }

        }

        return criteria;
    }


    public void save(LogMessage logMessage){
         mongoTemplate.save(logMessage);
    }

    public PageResult<LogMessage> queryWithPage(PageQO pageQO){
        PageResult pageResult = new PageResult();
        pageResult.setPageSize(pageQO.getPageSize());
        pageResult.setPageNo(pageQO.getPageNo());

        PageRequest pageRequest = new PageRequest(pageQO.getPageNo()-1,pageQO.getPageSize());
        Criteria criteria = getCriteria((LogMessageQO) pageQO.getBaseQO());
        Sort sort = new Sort(Sort.Direction.DESC,"logDate");
        pageResult.setList(mongoTemplate.find(query(criteria).with(pageRequest).with(sort),LogMessage.class));

        pageResult.setTotalCount(mongoTemplate.count(query(criteria),LogMessage.class));

        return pageResult;
    }

    public List<LogMessage> queryAll(LogMessageQO logMessageQO){
        Criteria criteria = getCriteria(logMessageQO);

        Sort sort = new Sort(Sort.Direction.DESC,"logDate");

        return mongoTemplate.find(query(criteria).with(sort),LogMessage.class);
    }
}
