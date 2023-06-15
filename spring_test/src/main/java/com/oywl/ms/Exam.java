package com.oywl.ms;

import java.util.Date;

/**
 * @description: 考试信息
 * @author: oywl
 * @time: 2022-9-15 20:56
 */

public class Exam {

    // 考试课程
    private Course course;
    // 满分
    private int fullScore;
    // 及格分
    private int passScore;

    public Exam( Course course, int fullScore, int passScore) {
        this.course = course;
        this.fullScore = fullScore;
        this.passScore = passScore;
    }



    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public int getPassScore() {
        return passScore;
    }

    public void setPassScore(int passScore) {
        this.passScore = passScore;
    }
}
