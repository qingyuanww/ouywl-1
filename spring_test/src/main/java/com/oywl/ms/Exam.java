package com.oywl.ms;

import java.util.Date;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-15 20:56
 */

public class Exam {
    // 开始开始时间
    private Date beginTime;
    // 考试结束时间
    private Date endTime;
    // 考试课程
    private Course course;
    // 满分
    private int fullScore;
    // 及格分
    private int passScore;

    public Exam(Date beginTime, Date endTime, Course course, int fullScore, int passScore) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.course = course;
        this.fullScore = fullScore;
        this.passScore = passScore;
    }


    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
