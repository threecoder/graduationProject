<template>
    <div>
        <div class="form-container">
            <el-row>
                <el-col :span="12" :offset="6">
                    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
                        <el-form-item label="优惠券名称" prop="name">
                            <el-input v-model="ruleForm.name" placeholder="优惠券名称"></el-input>
                        </el-form-item>
                        <el-form-item label="优惠券价值" prop="value">
                            <el-input v-model="ruleForm.value" placeholder="输入大于0的数字"></el-input>
                        </el-form-item>
                        <el-form-item label="优惠券生效时间" prop="dateRange">
                            <el-date-picker
                                type="datetimerange"
                                placeholder="选择日期"
                                v-model="ruleForm.dateRange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                            ></el-date-picker>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>

            <div class="tac">
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            </div>
        </div>
    </div>
</template>
<script>
import { formatDateAndTime } from "../../../../assets/js/util";
import couponApi from "../../../../api/admin/coupon";
export default {
    data() {
        let valid = (rule, value, callback) => {
            let num = Number(value);
            if (num !== num) {
                callback(new Error("请输入数字"));
                return false;
            }
            if (num < 0) {
                callback(new Error("优惠券价值不能小于0"));
                return false;
            }
            callback();
        };
        return {
            ruleForm: {
                name: "",
                value: null,
                dateRange: null
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
                value: [
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
                        message: "请选择优惠券有效时间",
                        trigger: "change"
                    }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate(async valid => {
                if (valid) {
                    try {
                        let data = { ...this.ruleForm };
                        data.startTime = formatDateAndTime(data.dateRange[0]);
                        data.endTime = formatDateAndTime(data.dateRange[1]);
                        delete data.dateRange;
                        console.log(data);
                        let res = await couponApi.addCoupon(data);
                        this.$message.success("添加优惠券成功");
                        this.$emit("close");
                    } catch (error) {
                        this.$message.error(error.message);
                    }
                } else {
                    this.$message.error("请填完所有信息再添加优惠券");
                    this.flag = false;
                    return false;
                }
            });
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