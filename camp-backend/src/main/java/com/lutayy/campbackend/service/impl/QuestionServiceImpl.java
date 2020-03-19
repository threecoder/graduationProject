package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.QuestionMapper;
import com.lutayy.campbackend.pojo.Question;
import com.lutayy.campbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public JSONObject modifyQuestion(JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        int questionId = jsonObject.getInteger("questionId");
        String state = jsonObject.getString("state");
        JSONArray answer = jsonObject.getJSONArray("answer");
        String choiceA = jsonObject.getString("choiceA");
        String choiceB = jsonObject.getString("choiceB");
        String choiceC = jsonObject.getString("choiceC");
        String choiceD = jsonObject.getString("choiceD");
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (question == null) {
            result.put("code", "fail");
            result.put("msg", "该题目编号不存在！");
            return result;
        } else if (answer == null || answer.size() == 0) {
            result.put("code", "fail");
            result.put("msg", "正确答案不能为空！");
            return result;
        }
        question.setQuestionState(state);
        question.setChoiceA(choiceA);
        question.setChoiceB(choiceB);
        question.setChoiceC(choiceC);
        question.setChoiceD(choiceD);
        question.setRightChoiceOne(((Integer) answer.get(0)).byteValue());
        if (question.getType() == 1) {
            if (answer.size() > 1) {
                question.setRightChoiceTwo(((Integer) answer.get(1)).byteValue());
                if (answer.size() > 2) {
                    question.setRightChoiceThree(((Integer) answer.get(2)).byteValue());
                    if (answer.size() > 3) {
                        question.setRightChoiceFour(((Integer) answer.get(3)).byteValue());
                    }
                }
            }
        }
        if (questionMapper.updateByPrimaryKey(question) > 0) {
            result.put("code", "success");
        } else {
            result.put("code", "fail");
            result.put("msg", "修改失败，请重新尝试");
        }
        return result;
    }
}
