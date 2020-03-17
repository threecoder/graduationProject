<template>
    <div>
        <h2>已经报名的活动</h2>
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
                        <el-button type="primary" @click="checkDetail(row)">详情</el-button>
                        <el-button type="primary" v-if="row.status=='未支付'" @click="pay">支付</el-button>
                        <el-button
                            type="primary"
                            v-if="row.status=='已支付'"
                            @click="checkSEAT(row)"
                        >座位号</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <activity-detail
            :drawerInfo="drawerInfo"
            :flag.sync="drawerInfoFlag"
            @notDisplay="drawerInfoFlag = false"
        />
    </div>
</template>
<script>
import mTable from "@/components/mTable.vue";
import activityDetail from "./components/activityDetail.vue";
import { getLocalStorage } from "@/assets/js/util.js";
import activityApi from "@/api/modules/activity.js";
export default {
    components: {
        mTable,
        activityDetail
    },
    data() {
        return {
            type: this.$route.params.id,
            activityTable: {
                tableConfig: [
                    { prop: "id", label: "活动序号", width: "100" },
                    { prop: "name", label: "活动名称" },
                    { prop: "date", label: "举办时间" },
                    { prop: "address", label: "地点" },
                    { prop: "fee", label: "费用" },
                    { prop: "status", label: "状态" },
                    {
                        prop: "opera",
                        label: "操作",
                        fixed: "right",
                        width: 200,
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
            drawerInfoFlag: false
        };
    },
    mounted() {
        this.init();
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    methods: {
        async init() {
            try {
                let res = await activityApi.getsignedActivities(this.idType);
                this.activityTable.tableData = res.data;
            } catch (error) {
                this.$message.error(error);
                console.log(error);
            }
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