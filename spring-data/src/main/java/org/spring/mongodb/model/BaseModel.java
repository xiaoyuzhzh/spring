package org.spring.mongodb.model;

import org.springframework.data.annotation.Id;

/**
 * Created by xiaoy on 2016/3/17.
 */
public class BaseModel {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
