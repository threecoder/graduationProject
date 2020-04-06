package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class ExamQuestionStudentAnswer implements Serializable {
    private Integer id;

    private Integer examId;

    private Integer questionId;

    private Integer studentId;

    private Byte answerOne;

    private Byte answerTwo;

    private Byte answerThree;

    private Byte answerFour;

    private Boolean correctOrNot;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Byte getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(Byte answerOne) {
        this.answerOne = answerOne;
    }

    public Byte getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(Byte answerTwo) {
        this.answerTwo = answerTwo;
    }

    public Byte getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(Byte answerThree) {
        this.answerThree = answerThree;
    }

    public Byte getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(Byte answerFour) {
        this.answerFour = answerFour;
    }

    public Boolean getCorrectOrNot() {
        return correctOrNot;
    }

    public void setCorrectOrNot(Boolean correctOrNot) {
        this.correctOrNot = correctOrNot;
    }
}