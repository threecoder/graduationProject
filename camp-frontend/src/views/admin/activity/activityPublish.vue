<template>
    <div>
        <h3>新建活动</h3>
        <div class="divider"></div>
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
                        <el-form-item label="活动名称" prop="name">
                            <el-input v-model="ruleForm.name" placeholder="活动名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="活动价格" prop="fee">
                            <el-input v-model="ruleForm.fee" placeholder="输入大于0的数字"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label="活动地址" prop="address">
                            <el-input v-model="ruleForm.address" placeholder="请输入活动地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="联系人" prop="contacts">
                            <el-input v-model="ruleForm.contacts" placeholder="请输入联系人"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="电话号码" prop="phone">
                            <el-input v-model="ruleForm.phone" placeholder="请输入联系人号码"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-form-item label="活动时间" required>
                        <el-col :span="8">
                            <el-form-item prop="date">
                                <el-date-picker
                                    type="date"
                                    placeholder="选择日期"
                                    v-model="ruleForm.date"
                                    style="width: 100%;"
                                ></el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item prop="time">
                                <el-time-picker
                                    placeholder="选择时间"
                                    v-model="ruleForm.time"
                                    style="width: 100%;"
                                ></el-time-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="活动时长" prop="len">
                                <el-select v-model="ruleForm.len">
                                    <el-option label="1小时" value="1"></el-option>
                                    <el-option label="2小时" value="2"></el-option>
                                    <el-option label="3小时" value="3"></el-option>
                                    <el-option label="4小时" value="4"></el-option>
                                    <el-option label="5小时" value="5"></el-option>
                                    <el-option label="6小时" value="6"></el-option>
                                    <el-option label="7小时" value="7"></el-option>
                                    <el-option label="8小时" value="8"></el-option>
                                    <el-option label="8小时以上" value="morethan8"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                </el-row>

                <el-row :gutter="20">
                    <el-form-item label="活动开放时间" prop="dateRange" label-width="200">
                        <el-col :span="10">
                            <el-form-item prop="openTime">
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
                    <el-col :span="24">
                        <el-form-item label="活动简介" prop="desc">
                            <el-input
                                type="textarea"
                                v-model="ruleForm.desc"
                                placeholder="请输入活动简介，段落可用回车符隔开"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                            <el-popover placement="top" width="160" v-model="visible" style="margin-left: 30px;">
                                <p>重置将清空填写的所有信息，确定重置吗？</p>
                                <div style="text-align: right; margin: 0">
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
import { newActivity } from "@/api/admin/activity.js";
export default {
    data() {
        let valid = (rule, value, callback) => {
            let num = Number(value);
            if (num !== num) {
                callback(new Error("请输入数字"));
                return false;
            }
            if (num < 0) {
                callback(new Error("活动价格不能小于0"));
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
                fee: null,
                address: "",
                date: "",
                time: "",
                len: null,
                contacts: null,
                phone: null,
                dateRange: null,
                openTime: null,
                closeTime: null,
                desc: ""
            },
            par: null,
            flag: false,
            visible: false,
            rules: {
                name: [
                    {
                        required: true,
                        message: "请输入活动名称",
                        trigger: "blur"
                    }
                ],
                fee: [
                    {
                        required: true,
                        message: "请输入活动价格",
                        trigger: "blur"
                    },
                    { validator: valid, trigger: "blur" }
                ],
                address: [
                    {
                        required: true,
                        message: "请输入活动地址",
                        trigger: "change"
                    }
                ],
                contacts: [
                    {
                        required: true,
                        message: "请输入活动联系人",
                        trigger: "change"
                    }
                ],
                phone: [
                    {
                        required: true,
                        message: "请输入联系人电话号码",
                        trigger: "blur"
                    },
                    { validator: valid2, trigger: "blur" }
                ],
                date: [
                    {
                        type: "date",
                        required: true,
                        message: "请选择日期",
                        trigger: "change"
                    }
                ],
                time: [
                    {
                        type: "date",
                        required: true,
                        message: "请选择时间",
                        trigger: "change"
                    }
                ],
                dateRange: [
                    {
                        type: "date",
                        required: true,
                        message: "请选择开放报名时间",
                        trigger: "change"
                    }
                ],
                len: [
                    {
                        required: true,
                        message: "请选择活动时长",
                        trigger: "blur"
                    }
                ],
                dateRange: [
                    {
                        required: true,
                        message: "请选择活动开放范围",
                        trigger: "blur"
                    }
                ],
                desc: [
                    {
                        required: true,
                        message: "请填写活动形式",
                        trigger: "blur"
                    }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    console.log(this.ruleForm);
                    let par = { ...this.ruleForm };
                    par.date = `${formatDate(par.date)} ${formatTime(
                        par.date
                    )}`;
                    par.openTime = formatDateAndTime(par.dateRange[0]);
                    par.closeTime = formatDateAndTime(par.dateRange[1]);
                    par.contacts = par.contacts + " " + par.phone;
                    par.desc = par.desc.split("\n");
                    console.log(par);
                    this.par = par;
                    this.flag = true;
                    this.addNewActivity();
                } else {
                    this.$message.error("请填完所有信息再创建活动");
                    this.flag = false;
                    return false;
                }
            });
        },
        async addNewActivity() {
            if (this.flag) {
                try {
                    let res = await newActivity(par);
                    this.$message.success("新建活动成功");
                } catch (error) {}
            }
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.visible = false;
            this.$message.success("重置成功！");
        }
    }
};
</script>
<style lang="scss" scoped>
.form-container {
    width: 70%;
    margin: 10px auto;
    text-align: center;
}
</style>