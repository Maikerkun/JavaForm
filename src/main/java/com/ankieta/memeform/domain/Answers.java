package com.ankieta.memeform.domain;

public class Answers {
    private Long id;
    private String age;
    private String gender;

    public Answers(){

    }

    public Answers(Long id, String age, String gender) {
        this.id = id;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
