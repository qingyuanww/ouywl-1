package com.oywl.ms;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-15 20:54
 */

public class Course {
    private int id;
    // 课程名称
    private String name;
    // 任课老师
    private Teacher teacher;

    public Course(int id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
