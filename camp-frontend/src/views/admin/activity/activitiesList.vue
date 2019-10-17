<template>
    <div class="all-container">
        <h3>已发布的活动</h3>
        <div class="divider"></div>
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
                        <el-button size="mini" @click="checkDetail(row)">更多</el-button>
                        <el-button size="mini" @click="handleSEAT(row)">安排座位</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>

        <el-drawer class="drawer-container" title="活动详情" :visible.sync="drawer" size="35%">
            <div class="tac">
                <h3>{{drwaerInfo.name}}</h3>
                <p>活动时间：{{drwaerInfo.date}}</p>
                <p>活动地点：{{drwaerInfo.address}}</p>
                <p>活动开发时间：{{drwaerInfo.openDate}}</p>
                <p>报名人数：{{drwaerInfo.joinNum}}</p>
            </div>
            <div class="divider"></div>
            <div class="activity-detail">
                <p v-for="(item,i) in drwaerInfo.introduce" :key="i">{{item}}</p>
                <p>如有疑问，请联系：{{drwaerInfo.contacts}}。</p>
            </div>
            <div class="drawer-footer">
                <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :before-remove="beforeRemove"
                    :limit="1"
                    :on-exceed="handleExceed"
                    :file-list="fileList"
                >
                    <el-button type="primary">导入报名表</el-button>
                </el-upload>
                <el-button style="margin-left:5px" @click="exportEntryForm" type="primary">导出报名表</el-button>
                <el-button style="margin:0 5px" type="primary">导出座位表模板</el-button>
                <el-button style="margin:0 5px" type="primary">导出座位表</el-button>
                <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :before-remove="beforeRemove"
                    :limit="1"
                    :on-exceed="handleExceed"
                    :file-list="fileList"
                >
                    <el-button type="primary">导入座位表</el-button>
                </el-upload>
            </div>
        </el-drawer>

        <el-dialog
            title="座位信息"
            v-if="flag"
            :visible.sync="flag"
            fullscreen
            :before-close="beforeClose"
            ref="dialog"
        >
            <s-table
                v-if="tableFlag"
                @lock="handleLock"
                @num="handleNum"
                :attr="seatInfo"
                :Arr="seatInfo.arr"
            />
            <div class="button-container">
                <el-button type="primary" @click="saveSEAT">保存座位信息</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import mTable from "@/components/mTable.vue";
import sTable from "./components/seatingTable.vue";
import {
    getActivityList,
    getSEATInfo,
    getEntryForm,
    getSEATForm,
    setSEATInfo
} from "@/api/admin/activity.js";
export default {
    components: {
        mTable,
        sTable
    },
    data() {
        return {
            activityTable: {
                tableConfig: [
                    { prop: "id", label: "活动序号", width: "100" },
                    { prop: "name", label: "活动名称" },
                    { prop: "openDate", label: "开放报名时间" },
                    { prop: "date", label: "举办时间" },
                    { prop: "address", label: "地点" },
                    { prop: "fee", label: "费用" },
                    {
                        prop: "opera",
                        label: "操作",
                        fixed: "right",
                        slot: "oper"
                    }
                ],
                tableData: [
                    {
                        id: 1,
                        name: "活动测试",
                        date: "2016-10-10 14:00:00-16:00:00",
                        openDate: "2016-10-10 14:00:00-16:00:00",
                        address: "广州市番禺区小谷围街道华南理工大学",
                        fee: 1000,
                        introduce: [
                            `企业网站的作用是展示企业网站，
                    为企业提供产品展示、企业宣传、形象建设、
                    联系企业等方面提供了重要信息渠道，企业如果能够做好网站宣传和网络口碑建设，
                    那么用户转化率就会大大提高，企业客户资源的源源不断带给我们企业的将会是订单，
                    所以企业网站建设不能只是摆设性的搭建一个域名、空间和程序，需要融合企业文化和企业精华到网站中。`
                        ],
                        joinNum:11,
                        seatInfo: true,
                        contacts: "唐先生 13535789321"
                    },
                    {
                        id: 2,
                        name: "活动测试",
                        date: "2016-11-11 14:00:00-16:00:00",
                        address: "广州市番禺区小谷围街道华南理工大学",
                        seatInfo: false,
                        fee: 1000,
                        introduciotn: [
                            `企业网站的作用是展示企业网站，为企业提供产品展示、企业宣传、形象建设、
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
            fileList: [],
            seatInfo: {
                id: null,
                xLen:0,
                yLen:0,
                x: 0,
                y: 0,
                arr: null
            },
            flag: false,
            tableFlag: true
        };
    },
    mounted(){
        this.init();
    },
    methods: {
        async init() {
            try {
                let listRes = await getActivityList();
                console.log(listRes)
                this.activityTable.tableData = listRes.data;
            } catch (error) {
                
            }
        },
        handleLock(i, j, flag) {
            this.seatInfo.arr[i][j].lock = flag;
        },
        handleNum(i, j, num) {
            this.tableFlag = false;
            if (num === "") {
                this.seatInfo.arr[i][j].num = null;
            } else {
                this.seatInfo.arr[i][j].num = num;
            }
            this.$nextTick(() => {
                this.tableFlag = true;
            });
        },
        checkDetail(row) {
            this.drawer = true;
            this.drwaerInfo = row;
        },
        async handleSEAT(row) {
            this.seatInfo.id = row.id;
            if (row.seatInfo == true) {
                try {
                    let res = await getSEATInfo(row.id);
                    this.seatInfo = res.data;
                    this.flag = true;
                } catch (error) {
                    this.$message.error("获取座位信息失败")
                }
            } else {
                this.$prompt("请输入座位行列数，如8*8", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    inputPattern: /[0-9]+\*[0-9]+/,
                    inputErrorMessage: "格式不正确"
                })
                    .then(obj => {
                        let t = obj.value.split("*");
                        t[0] = Number.parseInt(t[0]);
                        t[1] = Number.parseInt(t[1]);
                        if (t[0] == 0 || t[1] == 0) {
                            throw new Error("ss");
                            return;
                        }
                        if (t[0] > 30 || t[1] > 30) {
                            this.$message({
                                type: "info",
                                message: "行列数过多，建议使用Excel导入座位"
                            });
                            return;
                        }
                        this.seatInfo.xLen = t[0];
                        this.seatInfo.yLen = t[1];
                        this.seatInfo.arr = new Array(t[0]);
                        for (let i = 0; i < t[0]; i++) {
                            this.seatInfo.arr[i] = new Array(t[1]);
                            for (let j = 0; j < t[1]; j++) {
                                this.seatInfo.arr[i][j] = {
                                    num: null,
                                    lock: false
                                };
                            }
                        }
                        this.flag = true;
                    })
                    .catch(e => {
                        console.log(e);
                        if (e == "ss") {
                            this.$message({
                                type: "info",
                                message: "行列数必须大于0"
                            });
                        }
                    });
            }
        },
        beforeRemove() {},
        handleExceed() {},
        //座位表关闭前的回调
        beforeClose(done) {
            this.$confirm(
                "确定关闭座位表吗？",
                "请保存您的编辑，否则将会丢失此次编辑",
                {
                    confirmButtonText: "确定，我已经保存",
                    cancelButtonText: "取消",
                    type: "warning"
                }
            )
                .then(() => {
                    done();
                })
                .catch(() => {});
        },
        async saveSEAT() {
            let par = {
                activityId: this.seatInfo.id,
                data: this.seatInfo.arr
            };
            let res = await setSEATInfo(par);
            console.log(par);
        },
        async exportEntryForm(){
            try {
                let res = await getEntryForm(drwaerInfo.id);
                
            } catch (error) {
                
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.all-container {
    height: 100%;
    width: 100%;
}
.table-container {
    width: 99.9%;
}
.drawer-container {
    .tac {
        h3 {
            color: rgb(58, 158, 240);
        }
        p {
            color: rgb(114, 114, 114);
            margin: 5px 0;
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
        text-align: center;
        border-top: 1px solid rgb(58, 158, 240);
        width: 100%;
        display: flex;
        justify-content: flex-end;
        position: absolute;
        bottom: 0;
        padding: 30px;
        right: 0px;
    }
}
.el-dialog {
    .button-container {
        position: absolute;
        top: 8%;
        right: 10%;
        text-align: center;
    }
}
</style>