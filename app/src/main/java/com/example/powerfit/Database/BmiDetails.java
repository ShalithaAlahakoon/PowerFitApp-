package com.example.powerfit.Database;

public class BmiDetails {

    private String memberId;
    private String month;
    private float weight;
    private float height;
    private float bmi;

    public BmiDetails() {
    }


    public BmiDetails(String memberId, String month, float weight, float height, float bmi) {
        this.memberId = memberId;
        this.month = month;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }
}
