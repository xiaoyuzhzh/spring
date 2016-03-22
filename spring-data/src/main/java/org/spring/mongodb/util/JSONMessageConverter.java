package org.spring.mongodb.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * Created by xiaoy on 2016/3/17.
 */
public class JSONMessageConverter extends AbstractHttpMessageConverter{


    public JSONMessageConverter(){
        super(MediaType.APPLICATION_JSON);
    }


    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String body = IOUtils.toString(inputMessage.getBody(),"utf-8");
        return JSON.parseObject(body,clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        System.out.println(JSON.toJSONString(o));
        IOUtils.write(JSON.toJSONString(o).getBytes("utf-8"),outputMessage.getBody());
    }
}
