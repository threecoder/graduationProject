package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class ExamReQuestion implements Serializable {
    private Integer examId;

    private Integer questionId;

    private static final long serialVersionUID = 1L;

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
}