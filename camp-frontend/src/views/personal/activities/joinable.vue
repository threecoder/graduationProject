<template>
    <div>
        <h2 v-if="type==0">可参与的活动</h2>
        <h2 v-else>已经报名的活动</h2>
        <div class="table-container">
            <m-table
                :data="tableData"
                :tableConfig="tableConfig"
                :loading="loading"
                :tableAttr="tableAttr"
            >
                <el-table-column
                    align="center"
                    slot="oper"
                    slot-scope="{ params }"
                    v-bind="params"
                    class="myoper"
                >
                    <div slot-scope="{ row }">
                        <el-button @click="checkDetail(row)">详情</el-button>
                        <el-button v-if="type==1 && row.status=='未支付'" @click="pay">支付</el-button>
                        <el-button v-if="type==0" @click="join(row)">报名</el-button>
                        <el-button v-if="type==1 && row.status=='已支付'" @click="checkSEAT(row)" >座位号</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>

        <el-drawer class="drawer-container" title="活动详情" :visible.sync="drawer" size="30%">
            <div class="tac">
                <h3>{{drwaerInfo.name}}</h3>
                <p>活动时间：{{drwaerInfo.date}}</p>
                <p>活动地点：{{drwaerInfo.address}}</p>
            </div>
            <div class="divider"></div>
            <div class="activity-detail">
                <p v-for="(item,i) in drwaerInfo.introduciotn" :key="i">{{item}}</p>
                <p>如有疑问，请联系：{{drwaerInfo.contacts}}。</p>
            </div>
            <div class="drawer-footer">
                <el-button @click="drawer = false">取 消</el-button>
                <el-button
                    v-if="type==0"
                    type="primary"
                    @click="join(null)"
                    :loading="joinLoading"
                >{{ joinLoading ? '提交中 ...' : '立即报名' }}</el-button>
            </div>
        </el-drawer>
    </div>
</template>
<script>
import mTable from "../../../components/mTable.vue";
import {
    getJoinableActivities,
    joinActivties
} from "@/api/modules/activity.js";
export default {
    components: {
        mTable
    },
    data() {
        return {
            type: this.$route.params.id,
            tableConfig: [
                { prop: "id", label: "活动序号", width: "100" },
                { prop: "name", label: "活动名称" },
                { prop: "date", label: "举办时间" },
                { prop: "address", label: "地点" },
                { prop: "fee", label: "费用" },
                { prop: "opera", label: "操作", fixed: "right",width:200, slot: "oper" }
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
                },
                
            ],
            tableAttr: {
                stripe: true
            },
            drwaerInfo: {
                name: null,
                date: null,
                address: null,
                fee: null,
                introduciotn: [],
                contacts: "唐先生 13535789321"
            },
            loading: false,
            drawer: false,
            joinLoading: false
        };
    },
    mounted() {
        this.init();
    },
    methods: {
        async init() {
            console.log(this.type)
            if(this.type == 1){
                let t = this.tableConfig.pop();
                this.tableConfig.push({prop: "status",label: "状态"},t);
            }
            try {
                let res = await getJoinableActivities();
                this.tableData = res.data;
            } catch (error) {
                this.$message.error("获取活动信息失败，请刷新重试");
            }
        },
        async join(params) {
            try {
                this.joinLoading = true;
                let id = params ? params.id : this.drwaerInfo.id;
                let res = await joinActivties(id);
                this.$message.success("")
                this.joinLoading = false;
            } catch (error) {
                this.$message.error("报名失败")
                this.joinLoading = false;
            }

            setTimeout(() => {
            }, 1000);
        },
        checkDetail(row) {
            this.drawer = true;
            this.drwaerInfo = row;
        },
        pay(){

        },
        checkSEAT(){
            
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
</style>