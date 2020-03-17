<template>
    <div>
        <h2>可报名的培训</h2>
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
                        <el-button type="primary" @click="checkDetail(row)">培训详情</el-button>
                        <el-button type="primary" v-if="idType==0" @click="studentJoin(row)">报名</el-button>

                        <el-button
                            type="primary"
                            v-if="idType==1"
                            @click="studentList.listFlag = true;studentList.id=row.id"
                        >报名</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>

        <training-detail
            :drawerInfo="drawerInfo"
            :flag.sync="drawerInfoFlag"
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
import mTable from "@/components/mTable.vue";
import list from "@/components/studentList.vue";
import trainingDetail from "./components/trainingDetail.vue";
import { getLocalStorage } from "@/assets/js/util";
import activityApi from "@/api/modules/activity";
import trainingApi from "@/api/modules/training";
export default {
    components: {
        mTable,
        list,
        trainingDetail
    },
    data() {
        return {
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
                tableData: [
                    {
                        id: 1,
                        name: "培训测试",
                        date: "2016-10-10 14:00:00-16:00:00",
                        address: "广州市番禺区小谷围街道华南理工大学",
                        fee: 1000,
                        introduciotn: [
                            `企业网站的作用是展示企业网站，
                    为企业提供产品展示、企业宣传、形象建设、
                    联系企业等方面提供了重要信息渠道，企业如果能够做好网站宣传和网络口碑建设，
                    那么用户转化率就会大大提高，企业客户资源的源源不断带给我们企业的将会是订单，
                    所以企业网站建设不能只是摆设性的搭建一个域名、空间和程序，需要融合企业文化和企业精华到网站中。`
                        ],
                        status: "未支付",
                        contacts: "唐先生 13535789321"
                    },
                    {
                        id: 2,
                        name: "培训测试",
                        date: "2016-11-11 14:00:00-16:00:00",
                        address: "广州市番禺区小谷围街道华南理工大学",
                        fee: 1000,
                        introduciotn: [
                            `企业网站的作用是展示企业网站，
                    为企业提供产品展示、企业宣传、形象建设、
                    联系企业等方面提供了重要信息渠道，企业如果能够做好网站宣传和网络口碑建设，
                    那么用户转化率就会大大提高，企业客户资源的源源不断带给我们企业的将会是订单，
                    所以企业网站建设不能只是摆设性的搭建一个域名、空间和程序，需要融合企业文化和企业精华到网站中。`
                        ],
                        status: "已支付",
                        contacts: "唐先生 13535789321"
                    },
                    {
                        id: 3,
                        name: "培训测试",
                        date: "2016-10-10 14:00:00-16:00:00",
                        address: "广州市番禺区小谷围街道华南理工大学",
                        fee: 1000,
                        introduciotn: [
                            `企业网站的作用是展示企业网站，
                    为企业提供产品展示、企业宣传、形象建设、
                    联系企业等方面提供了重要信息渠道，企业如果能够做好网站宣传和网络口碑建设，
                    那么用户转化率就会大大提高，企业客户资源的源源不断带给我们企业的将会是订单，
                    所以企业网站建设不能只是摆设性的搭建一个域名、空间和程序，需要融合企业文化和企业精华到网站中。`
                        ],
                        status: "已完结",
                        contacts: "唐先生 13535789321"
                    }
                ],
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
        async init() {
            try {
                let res = await trainingApi.getJoinableTraining(this.idType);
                this.trainingTable.tableData = res.data;
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
            this.$confirm("确定报名这个活动吗？", "提示", {
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
                    this.$message.error(error);
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
            this.$confirm("确定报名这个活动吗？", "提示", {
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
                    this.$message.success("报名成功");
                } catch (error) {
                    this.$message.error(error.message);
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
.table-container {
    margin-top: 50px;
    ::v-deep .el-table__header-wrapper {
        thead {
            .cell {
                font-size: 16px;
                font-weight: 100;
                color: black;
            }
        }
    }
}
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