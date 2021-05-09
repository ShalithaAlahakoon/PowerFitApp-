package com.example.powerfit;

public class Member {

    private String Name;
    private Integer Conatact;
    private String Email;
    private Integer Height;
    private Integer Weight;
    private String Gender;
    private String Membership;
    private Integer Payement;

    public Member(String name, Integer conatact, String email, Integer height, Integer weight, String gender, String membership, Integer payement) {
        Name = name;
        Conatact = conatact;
        Email = email;
        Height = height;
        Weight = weight;
        Gender = gender;
        Membership = membership;
        Payement = payement;
    }

    public Member() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getConatact() {
        return Conatact;
    }

    public void setConatact(Integer conatact) {
        Conatact = conatact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public Integer getWeight() {
        return Weight;
    }

    public void setWeight(Integer weight) {
        Weight = weight;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMembership() {
        return Membership;
    }

    public void setMembership(String membership) {
        Membership = membership;
    }

    public Integer getPayement() {
        return Payement;
    }

    public void setPayement(Integer payement) {
        Payement = payement;
    }
}
