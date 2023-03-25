package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String,Student> student_map;

    private HashMap<String,Teacher> teacher_map;

    private HashMap<String, List<String>> teacher_student;

    public  StudentRepository(){
        student_map = new HashMap<>();
        teacher_map = new HashMap<>();
        teacher_student = new HashMap<>();
    }
    //1
    public void addStudent(Student student){
        student_map.put(student.getName(),student);
    }
    //2
    public void addTeacher(Teacher teacher){
        teacher_map.put(teacher.getName(),teacher);
    }
    //3
    public void addStudentTeacherPair(String student,String teacher){
        if(teacher_student.containsKey(teacher)){
            List<String> students  = teacher_student.get(teacher);
            students.add(student);
        }
        else{
            List<String> students  = new ArrayList<>();
            students.add(student);
            teacher_student.put(teacher,students);
        }
        Teacher teacher1 = teacher_map.get(teacher);
        teacher1.setNumberOfStudents(teacher_student.get(teacher).size());
    }
    //4
    public Student getStudentByName(String student){
        if(student_map.containsKey(student)){
            return student_map.get(student);
        }
        return  null;
    }
    //5
    public Teacher getTeacherByName(String teacher){
        if(teacher_map.containsKey(teacher)){
            return  teacher_map.get(teacher);
        }
        return null;
    }

    //6
    public List<String>  getStudentsByTeacherName(String teacher){
        if(teacher_student.containsKey(teacher)){
            return teacher_student.get(teacher);
        }
        return new ArrayList<>();
    }
    public List<String>  getAllStudents(){
        return new ArrayList<>(student_map.keySet());
    }
    public void deleteTeacherByName(String teacher){
        if(teacher_map.containsKey(teacher)) teacher_map.remove(teacher);
        if(teacher_student.containsKey(teacher)){
            for(String student : teacher_student.get(teacher)){
                if(student_map.containsKey(student)){
                    student_map.remove(student);
                }
            }
            teacher_student.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        for (String teacher : teacher_student.keySet()) {
            if (teacher_map.containsKey(teacher)) teacher_map.remove(teacher);

            for (String student : teacher_student.get(teacher)) {
                if (student_map.containsKey(student)) {
                    student_map.remove(student);
                }

                teacher_student.remove(teacher);
            }
        }
    }

}