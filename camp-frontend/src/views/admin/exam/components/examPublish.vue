<template>
    <div>
        <div class="form-container">
            <el-form
                :model="ruleForm"
                :rules="rules"
                ref="ruleForm"
                label-width="100px"
                class="demo-ruleForm"
            >
                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label="考试名称" prop="name">
                            <el-input v-model="ruleForm.name" placeholder="考试名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="题目数量" prop="num">
                            <el-input v-model="ruleForm.num" placeholder="输入大于0的数字"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="40">
                    <el-col :span="10">
                        <el-form-item label="考试时长(分钟)" prop="len" label-width="120px">
                            <el-input v-model="ruleForm.len" placeholder="以分钟为单位"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :span="10">
                        <el-form-item label="及格分数(百分制)" prop="pass" label-width="130px">
                            <el-input v-model="ruleForm.pass" placeholder="输入及格分数"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="所属培训" prop="training">
                            <el-select v-model="ruleForm.training">
                                <el-option
                                    v-for="(item,i) in trainingList"
                                    :value="item.id"
                                    :label="item.label"
                                    :key="i"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-form-item label="考试开放时间" prop="dateRange" label-width="200">
                        <el-col :span="10">
                            <el-form-item prop="dateRange">
                                <el-date-picker
                                    type="datetimerange"
                                    placeholder="选择日期"
                                    v-model="ruleForm.dateRange"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期"
                                    style="width: 100%;"
                                ></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                </el-row>

                <el-row>
                    <el-col>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                            <el-popover
                                placement="top"
                                width="160"
                                v-model="visible"
                                style="margin-left: 30px;"
                            >
                                <p>重置将清空填写的所有信息，确定重置吗？</p>
                                <div>
                                    <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                                    <el-button
                                        type="primary"
                                        size="mini"
                                        @click="resetForm('ruleForm')"
                                    >确定</el-button>
                                </div>
                                <el-button slot="reference">重置表单</el-button>
                            </el-popover>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </div>
</template>
<script>
import { formatDate, formatTime, formatDateAndTime } from "@/assets/js/util.js";
import { newExam, getTrainingList } from "@/api/admin/exam.js";
export default {
    data() {
        let valid = (rule, value, callback) => {
            let num = Number(value);
            if (num !== num) {
                callback(new Error("请输入数字"));
                return false;
            }
            if (num < 0) {
                callback(new Error("题目数量不能小于0"));
                return false;
            }
            callback();
        };
        let valid2 = (rule, value, callback) => {
            let num = Number(value);
            if (num !== num) {
                callback(new Error("请输入数字"));
                return false;
            }
            callback();
        };
        return {
            ruleForm: {
                name: "",
                num: null,
                pass: null,
                len: null,
                dateRange: null,
                training: null,
                openTime: null,
                closeTime: null
            },
            flag: false,
            visible: false,
            trainingList: [],
            rules: {
                name: [
                    {
                        required: true,
                        message: "请输入考试名称",
                        trigger: "blur"
                    }
                ],
                num: [
                    {
                        required: true,
                        message: "请输入考试题目数量",
                        trigger: "blur"
                    },
                    { validator: valid, trigger: "blur" }
                ],
                dateRange: [
                    {
                        required: true,
                        message: "请选择开放考试时间",
                        trigger: "change"
                    }
                ],
                len: [
                    {
                        required: true,
                        message: "请输入考试时长",
                        trigger: "blur"
                    },
                    { validator: valid2, trigger: "blur" }
                ],
                pass: [
                    {
                        required: true,
                        message: "请输入百分制及格分数",
                        trigger: "blur"
                    },
                    { validator: valid2, trigger: "blur" }
                ],
                training: [
                    {
                        required: true,
                        message: "请选择考试所属培训",
                        trigger: "blur"
                    }
                ]
            }
        };
    },
    mounted() {
        this.getTraining();
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    let par = { ...this.ruleForm };
                    par.openTime = formatDateAndTime(par.dateRange[0]);
                    par.closeTime = formatDateAndTime(par.dateRange[1]);
                    this.flag = true;
                    this.addNewExam(par);
                } else {
                    this.$message.error("请填完所有信息再创建活动");
                    this.flag = false;
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.visible = false;
            this.$message.success("重置成功！");
        },
        async addNewExam(par) {
            if (this.flag) {
                try {
                    let res = await newExam(par);
                    this.$message.success("新建考试成功");
                    this.$emit("close");
                } catch (error) {}
            }
        },
        async getTraining() {
            try {
                let res = await getTrainingList();
                this.trainingList = res.data;
                console.log(res);
            } catch (error) {}
        }
    }
};
</script>
<style lang="scss" scoped>
.form-container {
    padding: 20px;
    text-align: center;
}
</style>