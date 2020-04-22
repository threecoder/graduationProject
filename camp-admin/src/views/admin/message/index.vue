<template>
    <div class="message-all-container">
        <el-row>
            <el-col :span="20">
                <h3>我的消息</h3>
            </el-col>
            <el-col :span="4">
                <el-button type="primary" size="medium" class="fr" @click="sendMsg">发送消息</el-button>
            </el-col>
        </el-row>
        <div class="divider"></div>
        <div class="list-container">
            <message-list />
        </div>
        <resize-line :firstMaxValue="500" :firstMinValue="250" />
        <div class="detail-container">
            <message-detail />
        </div>

        <el-dialog :visible.sync="diaFlag" v-if="diaFlag" title="发送消息">
            <seng-msg />
        </el-dialog>
    </div>
</template>
<script>
import resizeLine from "../../../components/resizeLine.vue";
import messageList from "./components/messageList.vue";
import messageDetail from "./components/messageDetail.vue";
import sengMsg from "./components/sendMsg.vue";
export default {
    components: {
        resizeLine,
        messageList,
        messageDetail,
        sengMsg
    },
    data() {
        return {
            diaFlag: false
        };
    },
    methods: {
        sendMsg() {
            this.diaFlag = true;
        }
    }
};
</script>
<style lang="scss" scoped>
.message-all-container {
    height: 100%;
    width: 100%;
    .list-container {
        float: left;
        width: 300px;
        height: 100%;
        z-index: 1;
        // display: inline-block;
    }
    .detail-container {
        float: left;
        width: calc(100% - 320px);
        height: 100%;
        z-index: 1000;
        // display: inline-block;
    }
}
</style>