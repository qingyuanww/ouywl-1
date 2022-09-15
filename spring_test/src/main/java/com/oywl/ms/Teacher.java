package com.oywl.ms;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-15 20:54
 */

public class Teacher {
    private int id;
    // 老师姓名
    private String name;
    // 性别
    private int sex;

    public Teacher(int id, String name, int sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
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
}
