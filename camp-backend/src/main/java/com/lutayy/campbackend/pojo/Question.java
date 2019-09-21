package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class Question implements Serializable {
    private Integer questionId;

    private Integer trainingId;

    private String questionState;

    private Boolean type;

    private String choiceA;

    private String choiceB;

    private String choiceC;

    private String choiceD;

    private Byte rightChoiceOne;

    private Byte rightChoiceTwo;

    private Byte rightChoiceThree;

    private Byte rightChoiceFour;

    private static final long serialVersionUID = 1L;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public String getQuestionState() {
        return questionState;
    }

    public void setQuestionState(String questionState) {
        this.questionState = questionState;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public Byte getRightChoiceOne() {
        return rightChoiceOne;
    }

    public void setRightChoiceOne(Byte rightChoiceOne) {
        this.rightChoiceOne = rightChoiceOne;
    }

    public Byte getRightChoiceTwo() {
        return rightChoiceTwo;
    }

    public void setRightChoiceTwo(Byte rightChoiceTwo) {
        this.rightChoiceTwo = rightChoiceTwo;
    }

    public Byte getRightChoiceThree() {
        return rightChoiceThree;
    }

    public void setRightChoiceThree(Byte rightChoiceThree) {
        this.rightChoiceThree = rightChoiceThree;
    }

    public Byte getRightChoiceFour() {
        return rightChoiceFour;
    }

    public void setRightChoiceFour(Byte rightChoiceFour) {
        this.rightChoiceFour = rightChoiceFour;
    }
}