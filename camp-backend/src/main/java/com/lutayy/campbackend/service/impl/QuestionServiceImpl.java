package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.dao.QuestionMapper;
import com.lutayy.campbackend.dao.TrainingMapper;
import com.lutayy.campbackend.pojo.Question;
import com.lutayy.campbackend.pojo.Student;
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

    @Override
    public JSONObject modifyQuestion(JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        int questionId = jsonObject.getInteger("questionId");
        String state = jsonObject.getString("state");
        String type = jsonObject.getString("type");
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

    @Override  //导入试题excel
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
            System.out.println(excelSheets.size());
            for (String excelSheet : excelSheets) {
                List<Map<String, String>> list = map.get(excelSheet);
                System.out.println(list.size());
                for (Map<String, String> row : list) {
                    // TODO 批量添加
                    Question question = excelRowToQuestion(row);
                    if (question.getQuestionState() == null || question.getQuestionState().equals("题干实例"))
                        continue;
                    questionMapper.insert(question);
                }
            }
            result.put("code", "success");
            result.put("msg", "导入成功！");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "导入异常，请检查格式");
            return result;
        }
    }

    private Question excelRowToQuestion(Map<String, String> row) {
        Question question = new Question();
        String value;
        value = row.get("所属培训");
        if (value != null && !value.equals("")) {
            Integer trainingId;
            try {
                trainingId = Integer.valueOf(value);
                if (trainingMapper.selectByPrimaryKey(trainingId) == null) {
                    trainingId = null;
                }
            } catch (Exception e) {
                trainingId = null;
            }
            question.setTrainingId(trainingId);
        }
        value = row.get("题干");
        if (value != null && !value.equals(""))
            question.setQuestionState(value);
        value = row.get("类型(单选/多选/判断)");
        if (value != null && !value.equals("")) {
            if (value.equals("单选")) question.setType(0);
            else if (value.equals("多选")) question.setType(1);
            else question.setType(2);
        }
        if(question.getType().equals(2)){
            question.setChoiceA("对");
            question.setChoiceB("错");
        }else {
            value = row.get("A");
            if (value != null && !value.equals(""))
                question.setChoiceA(value);
            value = row.get("B");
            if (value != null && !value.equals(""))
                question.setChoiceB(value);
            value = row.get("C");
            if (value != null && !value.equals(""))
                question.setChoiceC(value);
            value = row.get("D");
            if (value != null && !value.equals(""))
                question.setChoiceD(value);
        }
        StringBuilder rightChoice = new StringBuilder();
        rightChoice.append(row.get("正确选项1") == null ? "" : row.get("正确选项1"));
        rightChoice.append(row.get("正确选项2") == null ? "" : row.get("正确选项2"));
        rightChoice.append(row.get("正确选项3") == null ? "" : row.get("正确选项3"));
        rightChoice.append(row.get("正确选项4") == null ? "" : row.get("正确选项4"));
        if (rightChoice.toString().toUpperCase().contains("A"))
            question.setRightChoiceOne((byte) 1);
        if (rightChoice.toString().toUpperCase().contains("B"))
            question.setRightChoiceTwo((byte) 2);
        if (rightChoice.toString().toUpperCase().contains("C"))
            question.setRightChoiceThree((byte) 3);
        if (rightChoice.toString().toUpperCase().contains("D"))
            question.setRightChoiceFour((byte) 4);
        return question;
    }

    private boolean checkRow(Map<String, String> row){
        String value;
        value=row.get("题干");
        if(value==null||value.equals(""))
            return false;
        value = row.get("类型(单选/多选/判断)");
        if (value == null || value.equals(""))
            return false;
        if (!value.equals("单选") && !value.equals("多选") && !value.equals("判断"))
            return false;
        return true;
    }

    @Override
    public JSONObject getQuestionInfo(Integer questionId) {
        Question question=questionMapper.selectByPrimaryKey(questionId);
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        if(question==null){
            result.put("code", "fail");
            result.put("msg", "题库中无相应题目");
            result.put("data", null);
            return result;
        }
        data.put("state", question.getQuestionState());
        if(question.getType().equals(0))
            data.put("type", "单选题");
        else if(question.getType().equals(1))
            data.put("type", "多选题");
        else
            data.put("type", "判断题");
        StringBuilder an=new StringBuilder();
        an.append(question.getRightChoiceOne());
        an.append(question.getRightChoiceTwo());
        an.append(question.getRightChoiceThree());
        an.append(question.getRightChoiceFour());
        String answerString=an.toString();
        String answer="";
        if(answerString.contains("1")) answer+="A";
        if(answerString.contains("2")) answer+="B";
        if(answerString.contains("3")) answer+="C";
        if(answerString.contains("4")) answer+="D";
        data.put("answer", answer);
        if(question.getType().equals(2)){
            data.put("choiceA", "对");
            data.put("choiceB", "错");
        }else {
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
