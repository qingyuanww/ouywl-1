package com.oywl.ms;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-15 20:56
 */

public class ExamResult {
    // 考试信息
    private Exam exam;
    // 参加考试的学生
    private Student student;
    // 考试成绩
    private int score;

    @Override
    public String toString() {
        return "ExamResult{" +
                "exam=" + exam +
                ", student=" + student +
                ", score=" + score +
                '}';
    }

    public ExamResult(Exam exam, Student student, int score) {
        this.exam = exam;
        this.student = student;
        this.score = score;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
