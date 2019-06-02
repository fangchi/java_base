package com.fc.jd8;

public class Student {
    private String name;
    private Double score;

    public Student(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public static int localComp(Student s1, Student s2) {
        if (s1.getScore() - s2.getScore() > 0) {
            return 1;
        } else if (s1.getScore() - s2.getScore() == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "{" + "\"name\":\"" + name + "\"" + ", \"score\":\"" + score + "\"" + "}";
    }
}
