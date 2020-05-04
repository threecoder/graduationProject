<template>
    <div>
        <h3>{{$route.query.name}}——报名详情</h3>
        <div class="divider"></div>
        <div class="form-container">
            <el-form inline :model="form">
                <el-form-item label="学员ID">
                    <el-input v-model="form.idNum" clearable></el-input>
                </el-form-item>
                <el-form-item label="学员名字">
                    <el-input v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item label="所属公司">
                    <el-select v-model="form.isEnrolled" clearable multiple>
                        <el-option
                            v-for="(item,i) in memberList"
                            :key="i"
                            :label="item.name"
                            :value="item.id"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="getSituation">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="promptStudent">提醒学员报名</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button size="medium" type="primary" @click="promptMember">提醒会员报名</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table :tableConfig="table.config" :data="table.data">
                <!-- <el-table-column slot="oper" align="center" slot-scope="{params}" v-bind="params">
                    <div slot-scope="{row}">
                        <el-button size="small" type="primary" @click="prompt(row)">提示支付</el-button>
                    </div>
                </el-table-column>-->
            </m-table>
        </div>
        <div class="page-container">
            <page
                :pageSize="form.pageSize"
                :currentPage="form.currentPage"
                :total="form.total"
                @curChange="curChange"
            />
        </div>
        <el-dialog
            :visible.sync="studentDialog.flag"
            v-if="studentDialog.flag"
            title="未报名学员"
            width="60%"
        >
            <student :trainingId="studentDialog.trainingId" />
        </el-dialog>
        <el-dialog
            :visible.sync="memberDialog.flag"
            v-if="memberDialog.flag"
            title="所有会员"
            width="60%"
        >
            <member :trainingId="memberDialog.trainingId" />
        </el-dialog>
    </div>
</template>
<script>
import page from "../../../components/page.vue";
import mTable from "../../../components/mTable.vue";
import student from "./components/NotEnrollStudent.vue";
import member from "./components/NotEnrollMember.vue";
import trainingApi from "../../../api/admin/training";
export default {
    components: {
        page,
        mTable,
        student,
        member
    },
    data() {
        return {
            trainingId: this.$route.query.trainingId,
            loading: true,
            form: {
                idNum: null,
                name: null,
                isEnrolled: null,
                pageSize: 10,
                currentPage: 1,
                total: 100
            },
            table: {
                config: [
                    { prop: "idNum", label: "证件号码" },
                    { prop: "name", label: "名字" },
                    { prop: "phone", label: "联系电话" },
                    { prop: "company", label: "所属公司" },
                    { prop: "status", label: "是否支付" }
                    // { slot: "oper", label: "操作" }
                ],
                data: [
                    {
                        idNum: 1,
                        name: "模拟名字",
                        phone: "151551515",
                        status: "未支付"
                    }
                ]
            },
            memberList: [
                {
                    name: "bb公司",
                    idNum: 1,
                    phone: "2121"
                }
            ],
            studentDialog: {
                flag: false,
                trainingId: null
            },
            memberDialog: {
                flag: false,
                trainingId: null
            }
        };
    },
    watch: {
        "$route.query.trainingId"(newVal) {
            this.trainingId = newVal;
            this.getSituation();
        }
    },
    mounted() {
        this.getSituation();
    },
    methods: {
        async getSituation() {
            try {
                let par = { ...this.form };
                par.trainingId = this.trainingId;
                let res = await trainingApi.getEnrolledStudentList(par);
                this.table.data = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
            }
            console.log(this.trainingId);
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getSituation();
        },
        promptStudent() {
            this.studentDialog.trainingId = this.trainingId;
            this.studentDialog.flag = true;
        },
        promptMember() {
            this.memberDialog.trainingId = this.trainingId;
            this.memberDialog.flag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
</style>