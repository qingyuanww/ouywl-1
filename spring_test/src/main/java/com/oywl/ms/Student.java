package com.oywl.ms;

import java.util.Date;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-15 20:54
 */

public class Student {
    private int id;
    // 学生姓名
    private String name;
    // 性别
    private int sex;
    // 生日
    private Date birth;

    private Double rate;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", rate=" + rate +
                '}';
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Student(int id, String name, int sex, Date birth) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
