package com.anqi.demo.demostream;

/**
 * 俱乐部学生类
 */
public class Student {
    /**
     * 学号
     */
    private String idNum;

    /**
     * 队员名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 来自班级
     */
    private String classNum;

    public Student() {}

    public Student(String idNum, String name, int age, String classNum) {
        this.idNum = idNum;
        this.name = name;
        this.age = age;
        this.classNum = classNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idNum='" + idNum + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}
