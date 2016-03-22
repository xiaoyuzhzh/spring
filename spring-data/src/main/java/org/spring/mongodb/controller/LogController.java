package org.spring.mongodb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.spring.mongodb.dao.LogMessageDAO;
import org.spring.mongodb.dao.LogMessageRepository;
import org.spring.mongodb.model.LogMessage;
import org.spring.mongodb.pojo.qo.LogMessageQO;
import org.spring.mongodb.pojo.qo.PageQO;
import org.spring.mongodb.pojo.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaoy on 2016/3/17.
 */
@RequestMapping("/log")
@Controller
public class LogController {

    @Autowired
    private LogMessageRepository logMessageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LogMessageDAO logMessageDAO;

    @RequestMapping(value = "/addlog")
    @ResponseBody
    public String addLog(@RequestBody LogMessage logMessage){
        logMessageDAO.save(logMessage);
        return "success";
    }

    @RequestMapping(value="/getlogs")
    @ResponseBody
    public List<LogMessage> queryLog(){

        LogMessageQO logMessageQO = new LogMessageQO();

        return logMessageDAO.queryAll(logMessageQO);
    }

    @RequestMapping(value = "/querylogsbypage/{pageNo}/{pageSize}")
    @ResponseBody
    public PageResult<LogMessage> queryLogsByPage(@RequestBody LogMessageQO logMessageQO , @PathVariable int pageNo,@PathVariable int pageSize){
        PageQO pageQO = new PageQO();
        pageQO.setPageSize(pageSize);
        pageQO.setPageNo(pageNo);
        pageQO.setBaseQO(logMessageQO);

        PageResult<LogMessage> pageResult = logMessageDAO.queryWithPage(pageQO);


        return pageResult;
    }


    @ExceptionHandler
    @ResponseBody
    public Object handleRuntimeException( RuntimeException e){
        e.printStackTrace();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error","服务器异常");
        return jsonObject;
    }

}
