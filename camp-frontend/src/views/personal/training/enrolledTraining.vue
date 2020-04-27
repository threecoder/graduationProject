<template>
    <div>
        <h2>已报名的培训</h2>
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
                            v-if="row.status=='未支付'"
                            @click="toPay"
                        >去支付</el-button>
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
            :isEnrollable="false"
            @notDisplay="drawerInfoFlag = false"
        />
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import trainingDetail from "./components/trainingDetail.vue";
import { getLocalStorage } from "../../../assets/js/util";
import activityApi from "../../../api/modules/activity";
import trainingApi from "../../../api/modules/training";
export default {
    components: {
        trainingDetail,
        mTable,
        page
    },
    data() {
        return {
            form: {
                pageSize: 10,
                currentPage: 1,
                total: 100,
                name: ""
            },
            trainingTable: {
                tableConfig: [
                    { prop: "id", label: "培训序号", width: "100" },
                    { prop: "name", label: "培训名称" },
                    { prop: "date", label: "举办时间" },
                    { prop: "address", label: "地点" },
                    { prop: "fee", label: "费用" },
                    { prop: "status", label: "状态" },
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
                        introduction: [
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
                        introduction: [
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
                        introduction: [
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
                introduction: [],
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
    },
    methods: {
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getList();
        },
        async init() {
            try {
                let res = await trainingApi.getsignedTraining(
                    this.idType,
                    this.form
                );
                this.trainingTable.tableData = res.data.list;
                this.form.total = res.data.form;
            } catch (error) {
                this.$message.error(error.message);
                console.log(error);
            }
        },
        checkDetail(row) {
            this.drawerInfo = row;
            this.drawerInfoFlag = true;
        },
        toPay() {
            this.$router.push("/order");
        },
        selectChange(val) {
            this.studentList.data = val.map(val => val.idNum);
            console.log("父节点", this.studentList.data);
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

.list-container {
    padding: 20px;
    width: 60%;
    height: 500px;
}
</style>