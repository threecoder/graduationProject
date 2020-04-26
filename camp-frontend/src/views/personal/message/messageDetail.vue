<template>
    <div class="detail-content-container">
        <div v-if="msgID==null" class="no-msg-container">
            <p>暂无消息内容</p>
        </div>
        <div v-else class="content-container" v-loading="loading">
            <div v-if="msgInfo.id">
                <div class="info-contaner">
                    <h3>{{msgInfo.title}}</h3>
                    <p>
                        <span>类型：</span>
                        <span>{{getMsgType(msgInfo.type)}}</span>
                    </p>
                    <p>
                        <span>时间：</span>
                        <span>{{msgInfo.time}}</span>
                    </p>
                    <p>
                        <span>状态：</span>
                        <span>{{msgInfo.read}}</span>
                    </p>
                </div>
                <div class="divider"></div>
                <div class="content">{{msgInfo.msg}}</div>
                <div class="tac">
                    <el-button type="primary" size="small" @click="toSee(msgInfo.type)">去看看</el-button>
                    <el-button type="primary" size="small" @click="deleteMsg">删除本条消息</el-button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import msgApi from "../../../api/modules/message";
import event from "../../../assets/js/eventBus";
import { msgTypeList } from "../../../const";
export default {
    data() {
        return {
            msgInfo: {
                id: 11,
                type: "报名",
                time: "2020-02-02 20:02",
                title: "报名活动啦",
                msg: "简短报名活动",
                read: "未读"
            },
            msgID: null,
            loading: false
        };
    },
    watch: {
        "$route.query"(newVal) {
            this.msgID = newVal.msgID;
            this.getMsgDetail();
        }
    },
    mounted() {
        this.msgID = this.$route.query.msgID;
        if (this.msgID) {
            this.getMsgDetail();
        }
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    methods: {
        getMsgType(type) {
            for (let i = 0; i < msgTypeList.length; i++) {
                if (msgTypeList[i].key == type) {
                    return msgTypeList[i].label;
                }
            }
        },
        async getMsgDetail() {
            this.loading = true;
            try {
                let res = await msgApi.getMsgDetail(this.idType, this.msgID);
                this.msgInfo = res.data;
                if (res.data.read == "未读") {
                    await msgApi.signAsRead(this.idType, this.msgID);
                    event.$emit("updateMsgList");
                }
            } catch (e) {
                this.$message.error(e.message);
                this.msgID = null;
            }
            this.loading = false;
        },
        toSee(type) {
            let path = "/";
            for (let i = 0; i < msgTypeList.length; i++) {
                if (msgTypeList[i].key == type) {
                    path = msgTypeList[i].path;
                }
            }
            this.$router.push(path);
        },
        deleteMsg() {
            this.$confirm("确定删除这条消息吗？删除后将无法恢复", "提示", {
                cancelButtonText: "取消",
                confirmButtonText: "确定",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await msgApi.deleteMsg(this.idType, this.msgID);
                    this.$message.success("删除消息成功！");
                    this.msgID = null;
                    event.$emit("updateMsgList");
                } catch (e) {
                    this.$message.error(e.message);
                }
            });
        }
    }
};
</script>
<style lang="scss" scoped>
.detail-content-container {
    height: 100%;
    background-color: #fff;
    position: relative;
    .no-msg-container {
        overflow: hidden;
        p {
            position: absolute;
            top: 50%;
            left: 50%;
            text-align: center;
            font-size: 20px;
            color: rgb(32, 32, 32);
        }
    }
    .content-container {
        overflow: hidden;
        height: 100%;
        .info-contaner {
            margin-top: 20px;
            text-align: center;
            p {
                margin: 10px auto;
            }
        }
        .content {
            padding: 15px 30px;
        }
    }
}
</style>