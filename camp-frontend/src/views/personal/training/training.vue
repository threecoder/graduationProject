<template>
    <div>
        <h2 v-if="type==0">可参与的培训</h2>
        <h2 v-else>已经报名的培训</h2>
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
                        <el-button @click="checkDetail(row)">培训详情</el-button>
                        <el-button v-if="type==1 && row.status=='未支付'" @click="pay">支付</el-button>
                        <el-button v-if="type==0 && idType==0" @click="dialogVisible = true">报名</el-button>
                        <el-dialog
                            title="提示"
                            :visible.sync="dialogVisible"
                            :modal-append-to-body="false"
                            width="30%"
                        >
                            <span>您确定报名这个培训吗？</span>
                            <span slot="footer" class="dialog-footer">
                                <el-button
                                    type="primary"
                                    @click="studentJoin(row) , dialogVisible = false"
                                >确定</el-button>
                                <el-button @click="dialogVisible = false">取消</el-button>
                            </span>
                        </el-dialog>
                        <el-button
                            v-if="type==0 && idType==1"
                            @click="studentList.listFlag = true;studentList.id=row.id"
                        >报名</el-button>
                        <el-button v-if="type==1 && row.status=='已支付'" @click="checkSEAT(row)">座位号</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>

        <el-drawer class="drawer-container" title="培训详情" :visible.sync="drawer" size="30%">
            <div class="tac">
                <h3>{{drwaerInfo.name}}</h3>
                <p>培训时间：{{drwaerInfo.date}}</p>
                <p>培训地点：{{drwaerInfo.address}}</p>
            </div>
            <div class="divider"></div>
            <div class="activity-detail">
                <p v-for="(item,i) in drwaerInfo.introduction" :key="i">{{item}}</p>
                <p>如有疑问，请联系：{{drwaerInfo.contacts}}。</p>
            </div>
            <div class="drawer-footer">
                <el-button @click="drawer = false">取 消</el-button>
                <el-button
                    v-if="type==0 && idType==0"
                    type="primary"
                    @click="studentJoin(null)"
                    :loading="joinLoading"
                >{{ joinLoading ? '提交中 ...' : '立即报名' }}</el-button>
                <el-button
                    v-if="type==0 && idType==1"
                    type="primary"
                    @click="studentList.listFlag=true;drawer=false;studentList.id=drwaerInfo.id"
                    :loading="joinLoading"
                >{{ joinLoading ? '提交中 ...' : '立即报名' }}</el-button>
            </div>
        </el-drawer>

        <el-dialog
            title="选择人员"
            v-if="studentList.listFlag"
            :visible.sync="studentList.listFlag"
            width="40%"
        >
            <list :tableConfig="studentList.config" :tableData="studentList.list" @selectChange="selectChange" />
            <div slot="footer" class="dialog-footer">
                <el-button @click="studentList.listFlag = false">取 消</el-button>
                <el-button type="primary" @click="memberJoin">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import mTable from "@/components/mTable.vue";
import list from "@/components/studentList";
import { getLocalStorage } from "@/assets/js/util.js";
import { getList } from "@/api/modules/activity.js";
import {
    getJoinableTraining,
    studentJoinTraining,
    getsignedTraining,
    memberJoinTraining
} from "@/api/modules/training.js";
export default {
    components: {
        mTable,
        list
    },
    data() {
        return {
            idType: getLocalStorage("user").type,
            type: this.$route.params.id,
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
            drwaerInfo: {
                id: null,
                name: null,
                date: null,
                address: null,
                fee: null,
                introduciotn: [],
                contacts: "唐先生 13535789321"
            },
            drawer: false,
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
    mounted() {
        this.init();
        if (this.idType == 1) {
            this.getStudentList();
        }
    },
    methods: {
        async init() {
            try {
                let res = null;
                if (this.type == 1) {
                    let t = this.trainingTable.tableConfig.pop();
                    this.trainingTable.tableConfig.push(
                        { prop: "status", label: "状态" },
                        t
                    );
                    res = await getsignedTraining(this.idType);
                } else {
                    res = await getJoinableTraining(this.idType);
                }
                this.trainingTable.tableData = res.data;
            } catch (error) {
                console.log(error);
            }
        },
        async studentJoin(params) {
            try {
                this.joinLoading = true;
                let id = params ? params.id : this.drwaerInfo.id;
                let res = await studentJoinTraining(id);
                this.$message.success("报名成功");
                this.joinLoading = false;
            } catch (error) {
                this.joinLoading = false;
            }
        },
        async getStudentList() {
            try {
                console.log("执行");
                let res = await getList();
                console.log(res);
                this.studentList.list = res.data;
            } catch (error) {}
        },
        async memberJoin(id) {
            if (this.studentList.data.length == 0) {
                this.$message.error("报名人数不能为0");
                return false;
            }
            try {
                let par = {
                    idNums: this.studentList.data,
                    trainingId: this.studentList.id
                };
                let res = await memberJoinTraining(par);
                this.$message.success("报名成功");
            } catch (error) {}
        },
        checkDetail(row) {
            this.drawer = true;
            this.drwaerInfo = row;
        },
        pay() {},
        async checkSEAT(row) {
            try {
                let res = await getSeatNum(row.id);
            } catch (error) {}
        },
        selectChange(val) {
            this.studentList.data = val.map(val => val.idNum);
            console.log("父节点", this.studentList.data);
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