package com.annie.justgymit.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserDetailsPost {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("weightgoal")
    @Expose
    private Integer weightgoal;
    @SerializedName("homegym")
    @Expose
    private String homegym;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeightgoal() {
        return weightgoal;
    }

    public void setWeightgoal(Integer weightgoal) {
        this.weightgoal = weightgoal;
    }

    public String getHomegym() {
        return homegym;
    }

    public void setHomegym(String homegym) {
        this.homegym = homegym;
    }

}
