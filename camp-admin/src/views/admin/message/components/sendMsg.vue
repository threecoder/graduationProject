<template>
    <div>
        <div class="form-container" v-loading="loading">
            <el-form
                :model="form"
                :rules="rules"
                label-position="left"
                ref="msgForm"
                label-width="90px"
            >
                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label="标题" prop="title" label-width="90px">
                            <el-input v-model="form.title" placeholder="请输入消息标题"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="消息类型" prop="type">
                            <el-select v-model="form.type" placeholder="请选择消息类型">
                                <el-option
                                    v-for="(item,i) in msgTypeList"
                                    :key="i"
                                    :value="item.key"
                                    :label="item.label"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="40">
                    <el-col :span="12">
                        <el-form-item label="接受人" prop="recipientType">
                            <el-select v-model="form.recipientType" placeholder="请选择接收人类型">
                                <el-option
                                    v-for="(item,i) in recipientTypeList"
                                    :key="i"
                                    :value="item.key"
                                    :label="item.label"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item
                            v-if="form.recipientType=='partStudent'"
                            label="接收学员"
                            prop="student"
                        >
                            <el-select v-model="form.student" multiple filterable>
                                <el-option
                                    v-for="(item,i) in studentList"
                                    :key="i"
                                    :label="item.name"
                                    :value="item.idNum"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item
                            v-if="form.recipientType=='partMember'"
                            label="接受会员"
                            prop="member"
                        >
                            <el-select v-model="form.member" multiple filterable>
                                <el-option
                                    v-for="(item,i) in memberList"
                                    :key="i"
                                    :label="item.name"
                                    :value="item.id"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-form-item label="消息内容" prop="title" label-width="90px">
                    <el-input type="textarea" v-model="form.msg" placeholder="请输入消息内容"></el-input>
                </el-form-item>
            </el-form>
            <div class="tac">
                <el-button type="primary" @click="send">发送</el-button>
                <el-button @click="cancel">取消</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import { msgTypeList } from "../../../../const";
import msgApi from "../../../../api/admin/message";
import studentApi from "../../../../api/admin/student";
export default {
    data() {
        return {
            msgTypeList,
            recipientTypeList: [
                { label: "全体会员与学员", key: "all" },
                { label: "全体学员", key: "allStudent" },
                { label: "全体会员", key: "allMember" },
                { label: "部分学员", key: "partStudent" },
                { label: "部分会员", key: "partMember" }
            ],
            studentList: [
                // { name: "张三111", idNum: "11111" },
                // { name: "李四222", idNum: "22222" }
            ],
            memberList: [
                // { name: "公司111", id: "11111" },
                // { name: "公司222", id: "22222" }
            ],
            form: {
                title: null,
                type: null,
                recipientType: null,
                msg: null,
                student: [],
                member: []
            },
            rules: {
                title: {
                    required: true,
                    message: "标题不能为空",
                    trigger: "blur"
                },
                type: {
                    required: true,
                    message: "类型不能为空",
                    trigger: "blur"
                },
                recipientType: {
                    required: true,
                    message: "接收者类型不能为空",
                    trigger: "blur"
                },
                student: {
                    required: true,
                    message: "接收学员不能为空",
                    trigger: "blur"
                },
                member: {
                    required: true,
                    message: "接收会员不能为空",
                    trigger: "blur"
                },
                msg: {
                    required: true,
                    message: "内容不能为空",
                    trigger: "blur"
                }
            },
            loading: false
        };
    },
    watch: {
        "form.recipientType"(val) {
            if (val == "partMember" && this.memberList.length == 0) {
                this.getAllMember();
            } else if (val == "partStudent" && this.studentList.length == 0) {
                this.getAllStudent();
            }
        }
    },
    methods: {
        cancel() {
            this.$emit("cancel");
        },
        send() {
            this.$refs.msgForm.validate(async valid => {
                if (valid) {
                    console.log(this.form);
                    this.loading = true;
                    try {
                        let res = await msgApi.sendMsg(this.form);
                        this.$message.success("发送消息成功");
                        this.cancel();
                    } catch (error) {
                        this.$message.error(error.message);
                    }
                    this.loading = false;
                }
            });
        },
        async getAllStudent() {
            try {
                let res = await msgApi.getStudentSelect();
                this.studentList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async getAllMember() {
            try {
                let res = await studentApi.getMemSelectList();
                this.memberList = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>

<style lang="scss" scoped>
</style>