package com.oywl.ms;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-15 20:56
 */

public class Main {
//    请统计考试的通过率最低的n个学生信息和学生考试成绩
//    注意：1个老师可能任多门课，学生可能参加多门课的多场考试

    /**
     * @param n          需要统计的通过率最低的n个学生的学生和考试成绩
     * @param resultList 本学年所有学生的所有考试的结果
     * @return 考试通过率最低的学生和成绩
     */

    //疑惑：给的数据应该默认给的数据学生
    //1、首先,你得拿到数据，根据学生进行分组，找到每个学生所有考试结果; 每个学生考试的课程数量
    //2、再根据考试结果计算，每门课程是否通过，
    //3、计算通过率，然后根据通过率正序排列，找到前三n
    public static Map<Student, List<ExamResult>> calculate(int n, List<ExamResult> resultList) {
        //根据学生进行分组
        Map<Student, List<ExamResult>> collect = resultList.stream().collect(Collectors.groupingBy(ExamResult::getStudent));
        //这里已经确定了学生的唯一性
        Set<Student> students = collect.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        //计算每个学生的通过率
        for (Student stu : students) {
            List<ExamResult> examResults = collect.get(stu);
            int length =examResults.size();
            double tgl =0;
            for (ExamResult examResult : examResults) {
                //考试信息
                Exam exam = examResult.getExam();
                //学生成绩
                int score = examResult.getScore();
                //及格分
                int passScore = exam.getPassScore();
                if(score>=passScore){
                    tgl++;
                }
            }
            double rate = tgl / length;
            HashMap<String, Object> addMap = new HashMap<>();
            addMap.put("stu",stu);
            addMap.put("rate",String.valueOf(rate));
            list.add(addMap);
            System.out.println(stu.getId());
            System.out.println("rate:"+rate);
        }
        //升序
        List<Map<String, Object>> rate = list.stream().sorted(Comparator.comparing(x -> Double.valueOf(x.get("rate").toString()))).limit(n).collect(Collectors.toList());

        Map<Student, List<ExamResult>> map = new HashMap<>();
        for (Map<String, Object> objectMap : rate) {
            Student stu = (Student) objectMap.get("stu");
            map.put(stu, collect.get(stu));
        }
        //首先，通过率是指大于及格的分数
        return map;
    }

    public static void main(String[] args) {
        Map<Student, List<ExamResult>> calculate = Main.calculate(2, get());
        Set<Student> students = calculate.keySet();
        for (Student student : students) {
            System.out.println("-->"+student.getId());
        }
    }

    static List<ExamResult> get(){
        ArrayList<ExamResult> list = new ArrayList<>();

        //三个学生
        Student A = new Student(1, null, 1, new Date());
        Student B = new Student(2, null, 1, new Date());
        Student C = new Student(3, null, 1, new Date());


        Exam exam1 = new Exam(null, null, null, 100, 60);
        Exam exam2 = new Exam(null, null, null, 100, 60);
        Exam exam3 = new Exam(null, null, null, 100, 60);
        Exam exam4 = new Exam(null, null, null, 100, 60);
        Exam exam5 = new Exam(null, null, null, 100, 60);



        list.add(new ExamResult(exam1,A,1));
        list.add(new ExamResult(exam2,A,1));
        list.add(new ExamResult(exam3,A,1));
        list.add(new ExamResult(exam4,A,1));
        list.add(new ExamResult(exam5,A,66));



        list.add(new ExamResult(exam1,B,1));
        list.add(new ExamResult(exam2,B,1));
        list.add(new ExamResult(exam3,B,1));
        list.add(new ExamResult(exam4,B,66));
        list.add(new ExamResult(exam5,B,66));


        list.add(new ExamResult(exam1,C,1));
        list.add(new ExamResult(exam2,C,1));
        list.add(new ExamResult(exam3,C,66));
        list.add(new ExamResult(exam4,C,66));
        list.add(new ExamResult(exam5,C,66));

        //假设三名学生  A 通过一门， B通过两门 ,C通过三门，不变了，默认都是五门课程

        return list;
    }
}
