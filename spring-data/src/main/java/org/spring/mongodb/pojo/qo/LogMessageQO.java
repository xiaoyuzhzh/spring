package org.spring.mongodb.pojo.qo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by xiaoy on 2016/3/17.
 */
public class LogMessageQO {

    /**
     * 工程id
     */
    private String projectId;
    /**
     * 环境id
     */
    private String envId;
    /**
     * 日志等级
     */
    private String level;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 起始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
