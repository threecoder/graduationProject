<template>
    <div>
        <h2>可参与的活动</h2>
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
                :data="activityTable.tableData"
                :tableConfig="activityTable.tableConfig"
                :loading="activityTable.loading"
                :tableAttr="activityTable.tableAttr"
            >
                <el-table-column
                    align="center"
                    slot="oper"
                    slot-scope="{ params }"
                    v-bind="params"
                    class="myoper"
                >
                    <div slot-scope="{ row }">
                        <el-button size="small" type="primary" @click="checkDetail(row)">详情</el-button>
                        <el-button
                            size="small"
                            type="primary"
                            v-if="idType==0"
                            @click="studentJoin"
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

        <activity-detail
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
                :tableData="studentList.list"
                :tableConfig="studentTableConfig"
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
import activityDetail from "./components/activityDetail.vue";
import list from "../../../components/studentList.vue";
import { getLocalStorage } from "../../../assets/js/util";
import activityApi from "../../../api/modules/activity";
export default {
    components: {
        mTable,
        list,
        activityDetail,
        page
    },
    data() {
        return {
            type: this.$route.params.id,
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                name: ""
            },
            activityTable: {
                tableConfig: [
                    { prop: "id", label: "活动序号", width: "100px" },
                    { prop: "name", label: "活动名称" },
                    { prop: "date", label: "举办时间" },
                    { prop: "address", label: "地点" },
                    { prop: "fee", label: "费用" },
                    {
                        label: "操作",
                        fixed: "right",
                        width: "200px",
                        slot: "oper"
                    }
                ],
                tableData: [
                    {
                        id: 1,
                        name: "活动测试",
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
                        name: "活动测试",
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
                        name: "活动测试",
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
                    // stripe: true
                },
                loading: false
            },
            studentTableConfig: [
                { slot: "select" },
                { prop: "name", label: "姓名" },
                { prop: "idNum", label: "身份证号码" },
                { prop: "phone", label: "手机号码" },
                { prop: "position", label: "职务" }
            ],
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
            dialogVisible: false,
            studentList: {
                id: null,
                listFlag: false,
                data: [],
                list: [
                    {
                        name: "1",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "2",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "3",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "4",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "5",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    },
                    {
                        name: "ss",
                        idNum: "445122222222",
                        phone: "15222222",
                        position: "经理"
                    }
                ]
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
            this.init();
        },
        async init() {
            try {
                let res = await activityApi.getJoinableActivities(
                    this.idType,
                    this.form
                );
                this.activityTable.tableData = res.data ? res.data.list : [];
                this.form.total = res.data ? res.data.total : 0;
            } catch (error) {
                this.$message.error(error.message);
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
                this.joinLoading = true;
                try {
                    let id = params ? params.id : this.drawerInfo.id;
                    let res = await activityApi.studentJoinActivties(id);
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
            this.$confirm("确定报名这个活动吗？", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let par = {
                        idNums: this.studentList.data,
                        activityId: this.studentList.id
                    };
                    let res = await activityApi.memberJoinActivity(par);
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
        pay() {},
        async checkSEAT(row) {
            try {
                let res = await activityApi.getSeatNum(row.id);
            } catch (error) {}
        },
        selectChange(val) {
            this.studentList.data = val.map(val => val.idNum);
        }
    }
};
</script>
<style lang="scss" scoped>
// .table-container {
//     margin-top: 50px;
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
.drawer-container {
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
    .drawer-footer {
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