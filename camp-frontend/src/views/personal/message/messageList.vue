<template>
    <div class="msg-list" v-loading="loading">
        <div class="msg-container" v-for="(item,i) in msgList" :key="i" @click="toDetail(item)">
            <div class="type">{{getMsgType(item.type)}}</div>
            <div class="info">
                <p class="title">{{item.title}}</p>
            </div>
            <div class="short-msg">
                <p>{{item.shortMsg}}</p>
            </div>
            <div>
                <span class="time">{{item.time}}</span>
                <span class="fr" :class="getReadClass(item)">{{item.read}}</span>
            </div>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :pageSize="form.pageSize"
                :currentPage="form.currentPage"
                @curChange="curChange"
            />
        </div>
    </div>
</template>
<script>
import page from "../../../components/page.vue";
import msgApi from "../../../api/modules/message";
import event from "../../../assets/js/eventBus";
import { msgTypeList } from "../../../const";
export default {
    components: {
        page
    },
    data() {
        return {
            form: {
                total: 50,
                pageSize: 5,
                currentPage: 1
            },
            msgList: [
                // {
                //     id: 11,
                //     type: "报名",
                //     time: "2020-02-02 20:02",
                //     title: "报名活动啦",
                //     shortMsg: "简短报名活动",
                //     read: "未读"
                // },
                // {
                //     id: 22,
                //     type: "报名",
                //     time: "2020-02-02 20:02",
                //     title: "报名活动啦",
                //     shortMsg: "简短报名活动",
                //     read: "未读"
                // }
            ],
            loading: true
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    mounted() {
        this.getMessageList();
        // if (event._events.updateMsgList) {
        //     return false;
        // }
        event.$on("updateMsgList", () => {
            this.getMessageList();
        });
    },
    methods: {
        async getMessageList() {
            this.loading = true;
            try {
                let res = await msgApi.getMsgList(this.idType, this.form);
                this.msgList = res.data.data;
                this.form.total = res.data.total;
            } catch (e) {
                this.$message.error(e.message);
            }
            this.loading = false;
        },
        getReadClass(item) {
            if (item.read == "未读") {
                return "unread";
            }
            return "read";
        },
        getMsgType(type) {
            for (let i = 0; i < msgTypeList.length; i++) {
                if (msgTypeList[i].key == type) {
                    return msgTypeList[i].label;
                }
            }
        },
        curChange(val) {
            this.form.currentPage = val;
            this.getMessageList();
        },
        toDetail(item) {
            this.$router.push("/message?msgID=" + item.id);
        }
    }
};
</script>
<style lang="scss" scoped>
.msg-list {
    overflow-y: scroll;
    height: 100%;
    .msg-container {
        background-color: #fff;
        padding: 10px;
        margin-bottom: 20px;
        overflow: hidden;
        &:hover {
            cursor: pointer;
        }
        .type {
            color: orange;
            text-align: right;
        }
        .info {
            .title {
                color: rgb(64, 158, 255);
                font-size: 18px;
            }
        }
        .short-msg {
            p {
                font-size: 14px;
                line-height: 20px;
                color: rgb(100, 100, 100);
                margin: 10px auto;
            }
        }
        .time {
            color: rgb(155, 155, 155);
        }
        .unread {
            color: red;
        }
        .read {
            color: green;
        }
    }
}
</style>