<template>
    <div>
        <h3>新建培训</h3>
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
                    <el-col :span="8">
                        <el-form-item label="培训名称" prop="name">
                            <el-input v-model="ruleForm.name" placeholder="培训名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="普通价格" prop="fee">
                            <el-input v-model="ruleForm.fee" placeholder="输入大于0的数字"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="会员价格" prop="vipFee">
                            <el-input v-model="ruleForm.vipFee" placeholder="输入大于0的数字"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="8">
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
                    <el-col :span="12">
                        <el-form-item
                            label="培训开放时间"
                            prop="dateRange"
                            label-position="left"
                            label-width="110px"
                        >
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
                    <el-col :span="8">
                        <el-form-item prop="level" label="培新难度">
                            <el-select v-model="ruleForm.level">
                                <el-option value="1" label="简单"></el-option>
                                <el-option value="2" label="中等"></el-option>
                                <el-option value="3" label="困难"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label="培训地址" prop="address">
                            <el-input v-model="ruleForm.address" placeholder="请输入培训地址"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="培训简介" prop="desc">
                            <el-input
                                type="textarea"
                                v-model="ruleForm.desc"
                                :show-word-limit="true"
                                :maxlength="450"
                                :autosize="{minRows: 4, maxRows: 11}"
                                placeholder="请输入培训简介，段落可用回车符隔开"
                            ></el-input>
                        </el-form-item>
                    </el-col>
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
import {
    formatDate,
    formatTime,
    formatDateAndTime
} from "../../../assets/js/util";
import adminTrainingApi from "../../../api/admin/training";
export default {
    data() {
        let valid = (rule, value, callback) => {
            let num = Number(value);
            if (num !== num) {
                callback(new Error("请输入数字"));
                return false;
            }
            if (num < 0) {
                callback(new Error("培训价格不能小于0"));
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
                vipFee: null,
                address: "",
                contacts: null,
                phone: null,
                dateRange: null,
                openTime: null,
                closeTime: null,
                desc: "",
                level: null
            },
            par: null,
            flag: false,
            visible: false,
            rules: {
                name: [
                    {
                        required: true,
                        message: "请输入培训名称",
                        trigger: "blur"
                    }
                ],
                fee: [
                    {
                        required: true,
                        message: "请输入培训价格",
                        trigger: "blur"
                    },
                    { validator: valid, trigger: "blur" }
                ],
                vipFee: [
                    {
                        required: true,
                        message: "请输入会员价格",
                        trigger: "blur"
                    },
                    { validator: valid, trigger: "blur" }
                ],
                address: [
                    {
                        required: true,
                        message: "请输入培训地址",
                        trigger: "change"
                    }
                ],
                contacts: [
                    {
                        required: true,
                        message: "请输入培训联系人",
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
                level: [
                    {
                        required: true,
                        message: "请选择培训的难度",
                        trigger: "blur"
                    }
                ],
                dateRange: [
                    {
                        required: true,
                        message: "请选择培训开放范围",
                        trigger: "blur"
                    }
                ],
                desc: [
                    {
                        required: true,
                        message: "请填写培训形式",
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
                    let par = { ...this.ruleForm };
                    par.openTime = formatDateAndTime(par.dateRange[0]);
                    par.closeTime = formatDateAndTime(par.dateRange[1]);
                    par.desc = par.desc.replace("|", " ");
                    par.desc = par.desc.split("\n");
                    console.log(par);
                    this.par = par;
                    this.flag = true;
                    this.newTraining(par);
                } else {
                    this.$message.error("请填完所有信息再创建培训");
                    this.flag = false;
                    return false;
                }
            });
        },
        async newTraining(par) {
            if (this.flag) {
                try {
                    let res = await adminTrainingApi.addNewTraining(par);
                    this.$message.success("新建培训成功");
                    this.$emit("cancel");
                } catch (error) {
                    this.$message.error(error.message);
                }
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
    margin: 60px auto;
    text-align: center;
}
</style>