package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.dao.QuestionMapper;
import com.lutayy.campbackend.dao.TrainingMapper;
import com.lutayy.campbackend.pojo.Question;
import com.lutayy.campbackend.pojo.Student;
import com.lutayy.campbackend.pojo.Training;
import com.lutayy.campbackend.service.QuestionService;
import jodd.util.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    GetObjectHelper getObjectHelper;

    @Override
    public JSONObject modifyQuestion(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        int questionId = jsonObject.getInteger("questionId");
        String state = jsonObject.getString("state");
        String type = jsonObject.getString("type");
        JSONArray answer = jsonObject.getJSONArray("answer");
        String choiceA = jsonObject.getString("choiceA");
        String choiceB = jsonObject.getString("choiceB");
        String choiceC = jsonObject.getString("choiceC");
        String choiceD = jsonObject.getString("choiceD");
        Question question = questionMapper.selectByPrimaryKey(questionId);
        question.setRightChoiceOne(null);
        question.setRightChoiceTwo(null);
        question.setRightChoiceThree(null);
        question.setRightChoiceFour(null);
        if (question == null) {
            result.put("code", "fail");
            result.put("msg", "该题目编号不存在！");
            return result;
        } else if (answer == null || answer.size() == 0) {
            result.put("code", "fail");
            result.put("msg", "正确答案不能为空！");
            return result;
        }
        if (type.equals("单选题"))
            question.setType(0);
        else if (type.equals("多选题"))
            question.setType(1);
        else
            question.setType(2);
        question.setQuestionState(state);
        question.setChoiceA(choiceA);
        question.setChoiceB(choiceB);
        question.setChoiceC(choiceC);
        question.setChoiceD(choiceD);
        if (question.getType().equals(2)) {
            question.setChoiceA("对");
            question.setChoiceB("错");
            question.setChoiceC(null);
            question.setChoiceD(null);
            if (!answer.get(0).equals(1) && !answer.get(0).equals(2)) {
                result.put("code", "fail");
                result.put("msg", "判断题答案只能填A/B！");
                return result;
            }
        }
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
            result.put("msg", "题目修改成功！");
        } else {
            result.put("code", "fail");
            result.put("msg", "修改失败，请重新尝试");
        }
        return result;
    }

    @Override
    public ResponseEntity<byte[]> getQuestionTemplate(HttpServletRequest request) {
        String fileName = "question_template.xlsx";
//        ServletContext servletContext=request.getServletContext();
//        String path=servletContext.getRealPath("/WEB-INF/templates/"+fileName);
        String path = "./src/main/resources/templates/question_template.xlsx";
        File file = new File(path);
        InputStream in;
        ResponseEntity<byte[]> response = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            HttpStatus statusCode = HttpStatus.OK;
            response = new ResponseEntity<byte[]>(bytes, headers, statusCode);
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    //导入试题excel
    @Override
    public JSONObject importQuestionByFile(MultipartFile questionFile) {
        JSONObject result = new JSONObject();

        result.put("code", "fail");
        if (questionFile == null || questionFile.isEmpty()) {
            result.put("msg", "文件不能为空");
            return result;
        }
        String fileName = questionFile.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            result.put("msg", "文件格式错误");
            return result;
        }
        InputStream in;
        try {
            in = questionFile.getInputStream();
            Map<String, List<Map<String, String>>> map = ExcelUtil.readXls(in);
            if (map.isEmpty()) {
                result.put("msg", "上传文件数据为空");
                return result;
            }
            Set<String> excelSheets = map.keySet();
            int rowIndex = 1;
            int totalCount = 0;
            int errorCount = 0;//数据或格式有误
            String errorTip = "";
            int noTrainingCount = 0;//培训不存在
            String noTrainingTip = "";
            Integer trainingId;
            String questionState, type, choiceA, choiceB, choiceC, choiceD, right1, right2, right3, right4;
            for (String excelSheet : excelSheets) {
                List<Map<String, String>> list = map.get(excelSheet);
                totalCount = totalCount + list.size();
                for (Map<String, String> row : list) {
                    rowIndex++;
                    // TODO 排错筛选
                    try {
                        trainingId = Integer.valueOf(row.get("所属培训"));
                        questionState = row.get("题干");
                        if (questionState == null || questionState.equals("题干示例"))
                            continue;
                        type = row.get("类型(单选/多选/判断)");
                        if (!type.equals("单选") && !type.equals("多选") && !type.equals("判断")) {
                            throw new Exception();
                        }
                        choiceA = row.get("A");
                        choiceB = row.get("B");
                        choiceC = row.get("C");
                        choiceD = row.get("D");
                        right1 = row.get("正确选项1");
                        right2 = row.get("正确选项2");
                        right3 = row.get("正确选项3");
                        right4 = row.get("正确选项4");
                    } catch (Exception e) {
                        errorCount++;
                        errorTip += (rowIndex + ",");
                        continue;
                    }
                    if (choiceA == null || choiceA.equals("") || choiceB == null || choiceB.equals("")
                            || choiceC == null || choiceC.equals("") || choiceD == null || choiceD.equals("")) {
                        errorCount++;
                        errorTip += (rowIndex + ",");
                        continue;
                    }
                    if (type.equals("判断")) {
                        if (right1.length() != 1 || right1.matches(".*[^AB]+.*")) {
                            errorCount++;
                            errorTip += (rowIndex + ",");
                            continue;
                        }
                    } else if (type.equals("单选")) {
                        if ((right1 + right2 + right3 + right4).length() > 1) {
                            errorCount++;
                            errorTip += (rowIndex + ",");
                            continue;
                        }
                    } else {
                        if ((right1 + right2 + right3 + right4).length() < 2) {
                            errorCount++;
                            errorTip += (rowIndex + ",");
                            continue;
                        }
                    }
                    if (right1.length() > 1 || right2.length() > 1 || right3.length() > 1 || right4.length() > 1) {
                        errorCount++;
                        errorTip += (rowIndex + ",");
                        continue;
                    }
                    if (right1.matches(".*[^A-D]+.*") || right2.matches(".*[^A-D]+.*")
                            || right3.matches(".*[^A-D]+.*") || right4.matches(".*[^A-D]+.*")) {
                        if (right1.matches(".*[^AB]+.*")) {
                            errorCount++;
                            errorTip += (rowIndex + ",");
                            continue;
                        }
                    }
                    Training training = null;
                    if (!trainingId.equals(0)) {
                        training = trainingMapper.selectByPrimaryKey(trainingId);
                        if (training == null) {
                            noTrainingCount++;
                            noTrainingTip += (rowIndex + ",");
                        }
                    }

                    // TODO 批量添加
                    Question question = new Question();
                    question.setTrainingId(training == null ? null : trainingId);
                    question.setQuestionState(questionState);
                    if (type.equals("单选")) question.setType(0);
                    else if (type.equals("多选")) question.setType(1);
                    else {
                        question.setType(2);
                    }
                    question.setChoiceA(choiceA);
                    question.setChoiceB(choiceB);
                    question.setChoiceC(choiceC);
                    question.setChoiceD(choiceD);
                    if (type.equals("判断")) {
                        question.setChoiceA("对");
                        question.setChoiceB("错");
                        question.setChoiceC(null);
                        question.setChoiceD(null);
                    }
                    StringBuilder rightChoice = new StringBuilder();
                    rightChoice.append(right1).append(right2).append(right3).append(right4);
                    if (rightChoice.toString().toUpperCase().contains("A"))
                        question.setRightChoiceOne((byte) 1);
                    if (rightChoice.toString().toUpperCase().contains("B"))
                        question.setRightChoiceTwo((byte) 2);
                    if (rightChoice.toString().toUpperCase().contains("C"))
                        question.setRightChoiceThree((byte) 3);
                    if (rightChoice.toString().toUpperCase().contains("D"))
                        question.setRightChoiceFour((byte) 4);
                    questionMapper.insert(question);
                }
            }
            String msg = "尝试导入" + totalCount + "条数据,其中" + (totalCount - errorCount) + "条导入成功。";
            if (errorCount > 0) {
                msg += ("  有" + errorCount + "条数据数据格式错误-行号为:" + errorTip + "请检查格式。");
            }
            if (noTrainingCount > 0) {
                msg += ("  有" + noTrainingCount + "条数据所属的培训不存在-行号为:" + noTrainingTip + "默认设置为通用题目(无固定所属培训)");
            }
            result.put("code", "success");
            result.put("msg", msg);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "导入异常，请检查格式");
        }
        return result;
    }


    @Override
    public JSONObject getQuestionInfo(Integer questionId) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (question == null) {
            result.put("code", "fail");
            result.put("msg", "题库中无相应题目");
            result.put("data", null);
            return result;
        }
        data.put("state", question.getQuestionState());
        if (question.getType().equals(0))
            data.put("type", "单选题");
        else if (question.getType().equals(1))
            data.put("type", "多选题");
        else
            data.put("type", "判断题");
        StringBuilder an = new StringBuilder();
        an.append(question.getRightChoiceOne());
        an.append(question.getRightChoiceTwo());
        an.append(question.getRightChoiceThree());
        an.append(question.getRightChoiceFour());
        String answerString = an.toString();
        String answer = "";
        if (answerString.contains("1")) answer += "A";
        if (answerString.contains("2")) answer += "B";
        if (answerString.contains("3")) answer += "C";
        if (answerString.contains("4")) answer += "D";
        data.put("answer", answer);
        if (question.getType().equals(2)) {
            data.put("choiceA", "对");
            data.put("choiceB", "错");
        } else {
            data.put("choiceA", question.getChoiceA());
            data.put("choiceB", question.getChoiceB());
            data.put("choiceC", question.getChoiceC());
            data.put("choiceD", question.getChoiceD());
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "试题查询成功");
        return result;
    }
}
