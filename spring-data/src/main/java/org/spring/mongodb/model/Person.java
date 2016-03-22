package org.spring.mongodb.model;

/**
 * Created by xiaoy on 2016/3/3.
 */
public class Person extends BaseModel{

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + getId() + ", name=" + name + ", age=" + age + "]";
    }
}