package org.spring.mongodb.controller;

import com.alibaba.fastjson.JSON;
import org.spring.mongodb.dao.PersonRepository;
import org.spring.mongodb.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiaoy on 2016/3/4.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PersonRepository personDao;

    @RequestMapping("/test")
    @ResponseBody
    public String test(String personId){

        Page<Person> personPage = personDao.findAll(new PageRequest(0, 10));

        List<Person> persons = personPage.getContent();

        Person person = personDao.findById(persons.get(0).getId());

        personDao.save(person);

        String personsString = JSON.toJSONString(persons);

        System.out.println(JSON.toJSONString(person));

        System.out.println(personsString);

        return personsString;
    }

}
