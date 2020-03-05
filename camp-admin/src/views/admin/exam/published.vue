<template>
    <div>
        <h4>已发布的考试</h4>
        <div class="divider"></div>
        <div class="table-container">
            <mTable :tableConfig="table.config" :data="table.data" :loading="table.loading">
                <el-table-column align="center" slot="oper" slot-scope="{params}" v-bind="params">
                    <div slot-scope="{row}">
                        <!-- 考试的状态，2-发布了还没开始考试，3-正在考试，4-已经考完试（未成绩审核），5-成绩审核完成 -->
                        <el-button
                            size="small"
                            @click="modify(row)"
                            v-if="row.status==2"
                            type="primary"
                        >修改信息</el-button>
                        <el-button
                            @click="close(row)"
                            v-if="row.status==2||row.status==3"
                            type="primary"
                        >关闭考试</el-button>
                        <el-button
                            size="small"
                            @click="finishedNumber(row)"
                            v-if="row.status==3"
                            type="primary"
                        >已作答人数</el-button>
                        <el-button
                            size="small"
                            @click="uploadGrade(row)"
                            v-if="row.status==4"
                            type="primary"
                        >上传成绩单</el-button>
                        <el-button
                            size="small"
                            type="primary"
                            v-if="row.status==4"
                            @click="beforeSubmit(row)"
                        >提交审核</el-button>
                        <el-button
                            size="small"
                            @click="finishedNumber(row)"
                            v-if="row.status==4||row.status==5"
                            type="primary"
                        >成绩统计</el-button>
                    </div>
                </el-table-column>
            </mTable>
            <page
                :currentPage="form.currentPage"
                :pageSize="form.pageSize"
                :total="form.total"
                @curChange="curChange"
            />
        </div>

        <!-- 修改考试信息弹窗 -->
        <el-dialog v-if="diaInfo.flag" :visible.sync="diaInfo.flag" title="修改考试信息">
            <exam-publish
                :id="diaInfo.examId"
                :type="false"
                @close="diaInfo.flag=false;getPubedList()"
            />
        </el-dialog>

        <!-- 查看已完成考试学生弹窗 -->
        <el-dialog v-if="diaFini.flag" :visible.sync="diaFini.flag" title="学生列表（已完成考试）">
            <p class="tip">
                当前完成人数为{{diaFini.num}} ，
                <span v-if="diaFini.type=='已完成'">其中及格人数为：{{diaFini.pass}}，</span>列表如下：
            </p>
            <m-table :data="diaFini.data" :tableConfig="diaFini.config" :loading="diaFini.loading">
                <el-table-column align="center" slot="oper" slot-scope="{params}" v-bind="params">
                    <div slot-scope="{row}">
                        <el-button size="small" type="primary" @click="checkDetail(row)">查看</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </el-dialog>

        <!-- 下载模板和上传成绩弹窗 -->
        <el-dialog v-if="diaGrade.flag" :visible.sync="diaGrade.flag" title="上传成绩单">
            <h3 class="tac">上传成绩单之前请先下载成绩单模板，填写数据后再上传</h3>
            <div class="tac upload-container">
                <el-button @click="getGradeTemp" type="primary" style="margin-bottom: 10px;">下载成绩单模板</el-button>
                <upload
                    :uploadUrl="diaGrade.uploadUrl"
                    :autoUpload="false"
                    :formData="diaGrade.data"
                    size="normal"
                />
            </div>
        </el-dialog>

        <!-- 提交审核弹窗 -->
        <el-dialog :visible.sync="submit.flag" v-if="submit.flag" title="提交成绩给审核员审核">
            <grade-list :examId="submit.examId" @submitClose="submit.flag=false" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import examPublish from "./components/examPublish.vue";
import gradeList from "./components/gradeList.vue";
import upload from "../../../components/upload.vue";
import adminExamApi from "../../../api/admin/exam.js";
import { download } from "../../../api/request.js";
export default {
    components: {
        mTable,
        page,
        examPublish,
        upload,
        gradeList
    },
    data() {
        return {
            table: {
                config: [
                    { prop: "name", label: "考试名称" },
                    { prop: "belong", label: "所属培训" },
                    { prop: "startTime", label: "开始时间" },
                    { prop: "endTime", label: "结束时间" },
                    { prop: "min", label: "考试时长（分钟）" },
                    //考试的状态，2-发布了还没开始考试，3-正在考试，4-已经考完试（未成绩审核），5-成绩审核完成
                    { prop: "statusText", label: "状态" },
                    {
                        slot: "oper",
                        label: "操作",
                        fixed: "right",
                        width: "300px"
                    }
                ],
                data: [
                    {
                        examId: "1",
                        name: "测试考试",
                        belong: "测试培训",
                        startTime: "2019-10-10 15:00:00",
                        endTime: "2019-10-10 15:00:00",
                        min: 120,
                        status: 4,
                        statusText: "已发布"
                    }
                ],
                loading: false
            },
            form: {
                currentPage: 1,
                total: 10,
                pageSize: 10
            },
            diaInfo: {
                flag: false,
                examId: null
            },
            diaFini: {
                examId: null,
                flag: false,
                config: [
                    { prop: "name", label: "学员姓名" },
                    { prop: "idNum", label: "学员编号" },
                    { prop: "member", label: "所属会员" },
                    { prop: "times", label: "作答次数" },
                    { prop: "grade", label: "分数" },
                    { slot: "oper", label: "操作" }
                ],
                data: [
                    {
                        idNum: "11111",
                        name: "张",
                        member: "所属公司",
                        grade: 60,
                        times: 2
                    }
                ],
                loading: false,
                num: 0,
                pass: 0,
                type: "考试中"
            },
            diaGrade: {
                flag: false,
                examId: null,
                uploadUrl: "/campback/admin/uploadGradeOfExam",
                formData: {
                    examId: null
                }
            },
            submit: {
                flag: false,
                examId: null
            }
        };
    },
    mounted() {
        this.getPubedList();
    },
    methods: {
        curChange(val) {
            this.form.currentPage = val;
            this.getPubedList();
        },
        async getPubedList() {
            this.table.loading = true;
            try {
                let res = await adminExamApi.getPublishedList(this.form);
                this.form.total = res.data.total;
                let t = (this.table.data = res.data.list);
                //考试的状态，2-发布了还没开始考试，3-正在考试，4-已经考完试（未成绩审核），5-成绩审核完成
                t.forEach((val, i) => {
                    if (val.status == 2) {
                        t[i].statusText = "已发布";
                    } else if (val.status == 3) {
                        t[i].statusText = "考试中";
                    } else if (val.status == 4) {
                        t[i].statusText = "成绩未审核";
                    } else if (val.status == 5) {
                        t[i].statusText = "成绩已审核";
                    }
                });
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        close(row) {
            this.$confirm("确定要关闭考试吗？", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(async () => {
                    try {
                        let res = await closeExam(row.examId);
                        this.$message.success("关闭考试成功");
                    } catch (error) {
                        this.$message.error(error.message);
                    }
                })
                .catch(e => {});
        },
        modify(row) {
            this.diaInfo.examId = row.examId;
            this.diaInfo.flag = true;
        },
        async finishedNumber(row) {
            this.diaFini.examId = row.examId;
            this.diaFini.flag = true;
            this.diaFini.loading = true;
            this.diaFini.type = row.status == 3 ? "考试中" : "已完成";
            try {
                let res = await adminExamApi.getFinishedStudentList(row.examId);
                this.diaFini.data = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.diaFini.loading = false;
        },
        beforeSubmit(row) {
            this.submit.examId = row.examId;
            this.submit.flag = true;
        },
        uploadGrade(row) {
            this.diaGrade.flag = true;
            this.diaGrade.uploadUrl =
                this.diaGrade.uploadUrl + "?examId=" + row.examId;
        },
        async getGradeTemp() {
            try {
                let res = await adminExamApi.getGradeTemplate();
                download(res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        checkDetail(row) {
            this.$router.push({
                path: `/studentExamDetail/${row.name}/${this.diaFini.examId}/${row.idNum}`
            });
        }
    }
};
</script>
<style lang="scss" scoped>
.tip {
    margin: -10px 0 20px;
    padding: 0 20px;
    font-size: 16px;
}
.upload-container {
    padding: 10px;
}
</style>