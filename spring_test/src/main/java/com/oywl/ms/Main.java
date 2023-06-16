package com.oywl.ms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            stu.setRate(tgl / length);
        }

        List<Student> studentList = students.stream().sorted(Comparator.comparing(Student::getRate).reversed()).limit(n).collect(Collectors.toList());

        LinkedHashMap<Student, List<ExamResult>> returnMap = new LinkedHashMap<>();
        for (Student student : studentList) {
            returnMap.put(student, collect.get(student));
        }
        return returnMap;
//        //升序
//        List<Map<String, Object>> rate = list.stream().sorted(Comparator.comparing(x -> Double.valueOf(x.get("rate").toString()))).limit(n).collect(Collectors.toList());
//
////        Map<Student, List<ExamResult>> collect1 = rate.stream().collect(Collectors.toMap(item -> (Student) item.get("stu"), item2 -> collect.get((Student) item2.get("stu"))));
////        Map<Student, List<ExamResult>> map = new HashMap<>();
////        for (Map<String, Object> objectMap : rate) {
////            Student stu = (Student) objectMap.get("stu");
////            map.put(stu, collect.get(stu));
////        }
//        //首先，通过率是指大于及格的分数
//        return rate.stream().collect(Collectors.toMap(item -> (Student) item.get("stu"), item2 -> collect.get((Student) item2.get("stu"))));
    }

    public static void main(String[] args) {
        Map<Student, List<ExamResult>> calculate = Main.calculate(3, get());

        for (Map.Entry<Student, List<ExamResult>> next : calculate.entrySet()) {
            Student key = next.getKey();
            List<ExamResult> value = next.getValue();
            System.out.println(key.getRate() + ":" + key.getName() + value.stream().map(examResult -> examResult.getExam().getCourse().getName() + examResult.getScore()).distinct().collect(Collectors.joining(",")));
        }
//        Set<Student> students = calculate.keySet();
//        for (Student student : students) {
//            System.out.println("-->"+student.getId()+":"+student.getName()+"-"+student.getRate()+calculate.get(student).stream().map(item->item.getExam().getCourse().getName()+item.getScore()).distinct().collect(Collectors.joining(",")));
//        }
    }

    static List<ExamResult> get(){
        ArrayList<ExamResult> list = new ArrayList<>();

        //三个学生
        Student zs = new Student(1, "张三", 1, new Date());
        Student ls = new Student(2, "李四", 1, new Date());
        Student ww = new Student(3, "王五", 1, new Date());
        Student zl = new Student(4, "赵六", 1, new Date());

        Course 语文 = new Course(1, "语文", null);
        Course 数学 = new Course(2, "数学", null);
        Course 英语 = new Course(3, "英语", null);
        Course 政治 = new Course(4, "政治", null);
        Course 计算机 = new Course(5, "计算机", null);

        Exam exam1 = new Exam(语文, 100, 60);
        Exam exam2 = new Exam(数学, 100, 60);
        Exam exam3 = new Exam(英语, 100, 60);
        Exam exam4 = new Exam(政治, 100, 60);
        Exam exam5 = new Exam( 计算机, 100, 60);



        list.add(new ExamResult(exam1,zs,1));
        list.add(new ExamResult(exam2,zs,1));
        list.add(new ExamResult(exam3,zs,1));
        list.add(new ExamResult(exam4,zs,1));
        list.add(new ExamResult(exam5,zs,66));



        list.add(new ExamResult(exam1,ls,1));
        list.add(new ExamResult(exam2,ls,1));
        list.add(new ExamResult(exam3,ls,1));
        list.add(new ExamResult(exam4,ls,66));
        list.add(new ExamResult(exam5,ls,66));


        list.add(new ExamResult(exam1,ww,1));
        list.add(new ExamResult(exam2,ww,1));
        list.add(new ExamResult(exam3,ww,66));
        list.add(new ExamResult(exam4,ww,66));
        list.add(new ExamResult(exam5,ww,66));

        list.add(new ExamResult(exam1,zl,1));
        list.add(new ExamResult(exam2,zl,1));
        list.add(new ExamResult(exam3,zl,66));
        list.add(new ExamResult(exam4,zl,66));
        list.add(new ExamResult(exam5,zl,66));
        //假设三名学生  A 通过一门， B通过两门 ,C通过三门，不变了，默认都是五门课程

        return list;
    }
}
