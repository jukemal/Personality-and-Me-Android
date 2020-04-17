package com.example.personalityandme.models;

import java.io.Serializable;

public class QuestionHelperModel implements Serializable {
    private Question question;
    private boolean isAnswered;

    public QuestionHelperModel() {
    }

    public QuestionHelperModel(Question question, boolean isAnswered) {
        this.question = question;
        this.isAnswered = isAnswered;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    @Override
    public String toString() {
        return "QuestionHelperModel{" +
                "question=" + question +
                ", isAnswered=" + isAnswered +
                '}';
    }
}
