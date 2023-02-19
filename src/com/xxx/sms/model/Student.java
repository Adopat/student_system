package com.xxx.sms.model;

/**
 * 学生实体类
 */
public class Student {
    // id
    private int id;
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 性别 0-女 1-男
    private int sex;
    // 班级id
    private int classId;
    // 班级名字
    private String className;

    public static int pageSize = 5;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
