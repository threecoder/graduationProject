<template>
    <div>
        <h2>已经报名的活动</h2>
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
                            v-if="row.status=='未支付'"
                            @click="pay"
                        >支付</el-button>
                        <el-button
                            size="small"
                            type="primary"
                            v-if="row.status=='已支付'"
                            @click="checkSEAT(row)"
                        >座位号</el-button>
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
            :isEnrolable="false"
            :flag.sync="drawerInfoFlag"
            @notDisplay="drawerInfoFlag = false"
        />

        <el-dialog :visible.sync="seatDia.flag" v-if="seatDia.flag" title="座位情况">
            <seat-dialog :seatList="seatDia.seatList" />
        </el-dialog>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import page from "../../../components/page.vue";
import activityDetail from "./components/activityDetail.vue";
import seatDialog from "./components/seatNumTable.vue";
import { getLocalStorage } from "../../../assets/js/util";
import activityApi from "../../../api/modules/activity";
export default {
    components: {
        mTable,
        page,
        activityDetail,
        seatDialog
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
                    { prop: "status", label: "状态" },
                    {
                        label: "操作",
                        fixed: "right",
                        width: "200px",
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
            seatDia: {
                seatList: [],
                flag: false
            }
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
        curChange(newVal) {
            this.form.currentPage = newVal;
            this.getList();
        },
        async init() {
            try {
                let res = await activityApi.getsignedActivities(
                    this.idType,
                    this.form
                );
                this.activityTable.tableData = res.data.list;
                this.form.total = res.data.total;
            } catch (error) {
                this.$message.error(error.message);
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
                let res = await activityApi.getSeatNum(this.idType, row.id);
                //显示查询到的结果
                if (this.idType == 0) {
                    this.$alert("你的座位号是 " + res.data, "查询结果", {
                        confirmButtonText: "确定",
                        cancelButton: false,
                        customClass: "myclass",
                        type: "info"
                    });
                } else {
                    this.seatDia.seatList = res.data.list;
                    this.seatDia.flag = true;
                }
            } catch (error) {
                this.$message.error(error.message);
            }
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