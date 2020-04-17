package com.example.personalityandme.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Builder;

@Builder
public class Question implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question_1")
    @Expose
    private String question1;
    @SerializedName("question_2")
    @Expose
    private String question2;

    public Question() {
    }

    public Question(Integer id, String question1, String question2) {
        this.id = id;
        this.question1 = question1;
        this.question2 = question2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question1='" + question1 + '\'' +
                ", question2='" + question2 + '\'' +
                '}';
    }
}
