package com.member_information;

public class StudentDo {
    private Integer id;
    private String name;
    private String no;
    private String homeTown;
    private double cnScore;
    private double enScore;
    private double mathScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public double getCnScore() {
        return cnScore;
    }

    public void setCnScore(double cnScore) {
        this.cnScore = cnScore;
    }

    public double getEnScore() {
        return enScore;
    }

    public void setEnScore(double enScore) {
        this.enScore = enScore;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }
}
