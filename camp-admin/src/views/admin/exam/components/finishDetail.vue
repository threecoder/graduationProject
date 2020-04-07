<template>
    <div>
        <p class="tip">
            当前完成人数为{{num}},
            <span v-if="type=='已完成'">其中及格人数为：{{pass}}，</span>列表如下：
        </p>
        <m-table :data="table.data" :tableConfig="table.config" :loading="table.loading">
            <el-table-column align="center" slot="oper" slot-scope="{params}" v-bind="params">
                <div slot-scope="{row}">
                    <el-button size="small" type="primary" @click="checkDetail(row)">查看</el-button>
                </div>
            </el-table-column>
        </m-table>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import adminExamApi from "../../../../api/admin/exam";
export default {
    props: {
        examInfo: {
            require: true,
            type: Object
        }
    },
    components: {
        mTable
    },
    data() {
        return {
            type: "考试中",
            num: 0,
            pass: 1,
            table: {
                config: [
                    { prop: "name", label: "学员姓名" },
                    { prop: "idNum", label: "学员编号" },
                    { prop: "member", label: "所属会员" },
                    { prop: "times", label: "作答次数" },
                    { prop: "grade", label: "分数" },
                    { slot: "oper", label: "操作" }
                ],
                data: [
                    // {
                    //     idNum: "11111",
                    //     name: "张",
                    //     member: "所属公司",
                    //     grade: 60,
                    //     times: 2
                    // }
                ],
                loading: false
            }
        };
    },
    mounted() {
        this.type = this.examInfo.status == 3 ? "考试中" : "已完成";
        this.getFinishStudentList();
    },
    methods: {
        async getFinishStudentList() {
            this.table.loading = true;
            try {
                let res = await adminExamApi.getFinishedStudentList(
                    this.examInfo.examId
                );
                this.table.data = res.data;
                this.num = res.data.length;
                let count = 0;
                res.data.forEach(val => {
                    console.log(val);
                    if (val.grade >= this.examInfo.pass) {
                        count++;
                    }
                });
                this.pass = count;
            } catch (error) {
                this.$message.error(error.message);
            }
            this.table.loading = false;
        },
        checkDetail(row) {
            console.log(this.examInfo);
            this.$router.push({
                path: `/studentExamDetail/${row.name}/${this.examInfo.examId}/${row.idNum}`
            });
        }
    }
};
</script>
<style lang="scss" scoped>
</style>