<template>
    <div>
        <h2>可报名的培训</h2>
        <div class="divider"></div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item label="活动名称">
                    <el-input v-model="form.name" placeholder="请输入活动名称" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="init" type="primary" size="medium">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table
                :data="trainingTable.tableData"
                :tableConfig="trainingTable.tableConfig"
                :loading="trainingTable.loading"
                :tableAttr="trainingTable.tableAttr"
            >
                <el-table-column
                    align="center"
                    slot="oper"
                    slot-scope="{ params }"
                    v-bind="params"
                    class="myoper"
                >
                    <div slot-scope="{ row }">
                        <el-button size="small" type="primary" @click="checkDetail(row)">培训详情</el-button>
                        <el-button
                            size="small"
                            type="primary"
                            v-if="idType==0"
                            @click="studentJoin(row)"
                        >报名</el-button>

                        <el-button
                            size="small"
                            type="primary"
                            v-if="idType==1"
                            @click="studentList.listFlag = true;studentList.id=row.id"
                        >报名</el-button>
                    </div>
                </el-table-column>
            </m-table>
            <page
                :currentPage="form.currentPage"
                :total="form.total"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>

        <training-detail
            :drawerInfo="drawerInfo"
            :flag.sync="drawerInfoFlag"
            :isEnrolable="true"
            @notDisplay="drawerInfoFlag = false"
            @enroll="enroll"
        />

        <el-dialog
            title="选择人员"
            v-if="studentList.listFlag"
            :visible.sync="studentList.listFlag"
            width="40%"
        >
            <list
                :tableConfig="studentList.config"
                :tableData="studentList.list"
                @selectChange="selectChange"
            />
            <div slot="footer" class="dialog-footer">
                <el-button @click="studentList.listFlag = false">取 消</el-button>
                <el-button type="primary" @click="memberJoin">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import list from "../../../components/studentList.vue";
import trainingDetail from "./components/trainingDetail.vue";
import { getLocalStorage } from "../../../assets/js/util";
import activityApi from "../../../api/modules/activity";
import trainingApi from "../../../api/modules/training";
export default {
    components: {
        mTable,
        page,
        list,
        trainingDetail
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                name: ""
            },
            dialogVisible: false,
            trainingTable: {
                tableConfig: [
                    { prop: "id", label: "培训序号", width: "100" },
                    { prop: "name", label: "培训名称" },
                    { prop: "date", label: "举办时间" },
                    { prop: "address", label: "地点" },
                    { prop: "fee", label: "费用" },
                    {
                        prop: "opera",
                        label: "操作",
                        fixed: "right",
                        width: 220,
                        slot: "oper"
                    }
                ],
                tableData: [],
                tableAttr: {
                    stripe: true
                },
                loading: false
            },
            drawerInfo: {
                id: null,
                name: null,
                date: null,
                address: null,
                fee: null,
                introduciotn: [],
                contacts: "唐先生 13535789321"
            },
            drawerInfoFlag: false,
            joinLoading: false,
            studentList: {
                id: null,
                listFlag: false,
                data: [],
                config: [
                    { slot: "select" },
                    { prop: "name", label: "姓名" },
                    { prop: "idNum", label: "身份证号码" },
                    { prop: "phone", label: "手机号码" },
                    { prop: "position", label: "职务" }
                ],
                list: []
            }
        };
    },
    watch: {
        "studentList.listFlag": {
            handler() {
                this.studentList.data.length = 0;
            }
        }
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    mounted() {
        this.init();
        if (this.idType == 1) {
            this.getStudentList();
        }
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getList();
        },
        async init() {
            try {
                let res = await trainingApi.getJoinableTraining(
                    this.idType,
                    this.form
                );
                this.trainingTable.tableData = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
                console.log(error);
            }
        },
        enroll() {
            if (this.idType == 0) {
                this.studentJoin(null);
            } else {
                this.studentList.listFlag = true;
                this.studentList.id = this.drawerInfo.id;
            }
        },
        async studentJoin(params) {
            this.$confirm("确定报名这个培训吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    this.joinLoading = true;
                    let id = params ? params.id : this.drawerInfo.id;
                    let res = await trainingApi.studentJoinTraining(id);
                    this.$message.success("报名成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
                this.joinLoading = false;
            });
        },
        async getStudentList() {
            try {
                let res = await activityApi.getList();
                this.studentList.list = res.data;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        async memberJoin(id) {
            if (this.studentList.data.length == 0) {
                this.$message.error("报名人数不能为0");
                return false;
            }
            this.$confirm("确定报名这个培训吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let par = {
                        idNums: this.studentList.data,
                        trainingId: this.studentList.id
                    };
                    let res = await trainingApi.memberJoinTraining(par);
                    this.$alert(res.msg, "提示");
                } catch (error) {
                    this.$alert(error.message, "提示");
                }
            });
        },
        checkDetail(row) {
            this.drawerInfo = row;
            this.drawerInfoFlag = true;
        },
        selectChange(val) {
            this.studentList.data = val.map(val => val.idNum);
        }
    }
};
</script>
<style lang="scss" scoped>
// .table-container {
//     ::v-deep .el-table__header-wrapper {
//         thead {
//             .cell {
//                 font-size: 16px;
//                 font-weight: 100;
//                 color: black;
//             }
//         }
//     }
// }
.drawerInfoFlag-container {
    .tac {
        h3 {
            color: rgb(58, 158, 240);
        }
        p {
            color: rgb(114, 114, 114);
        }
    }

    .divider {
        border-bottom: 1px solid rgb(58, 158, 240);
    }
    .activity-detail {
        padding: 20px 30px;
        p {
            line-height: 30px;
            text-indent: 2rem;
        }
    }
    .drawerInfoFlag-footer {
        border-top: 1px solid rgb(58, 158, 240);
        width: 100%;
        display: flex;
        justify-content: flex-end;
        position: absolute;
        bottom: 0;
        padding: 30px;
        right: 0px;
        .el-button {
            // flex: 1;
        }
    }
}
.list-container {
    padding: 20px;
    width: 60%;
    height: 500px;
}
</style>