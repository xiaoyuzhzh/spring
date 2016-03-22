package org.spring.mongodb.dao;

import org.spring.mongodb.model.LogMessage;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by xiaoy on 2016/3/17.
 */
public interface LogMessageRepository extends PagingAndSortingRepository<LogMessage,Long> ,LogMessageRepositoryCustom{
//    List<LogMessage> find(Query query);
}
