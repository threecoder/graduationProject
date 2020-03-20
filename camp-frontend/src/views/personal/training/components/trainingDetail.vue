<template>
    <div>
        <el-drawer class="drawer-container" title="培训详情" :visible.sync="drawerInfoFlag" size="30%">
            <div class="tac">
                <h3>{{drawerInfo.name}}</h3>
                <p>培训时间：{{drawerInfo.date}}</p>
                <p>培训地点：{{drawerInfo.address}}</p>
            </div>
            <div class="divider"></div>
            <div class="activity-detail">
                <p v-for="(item,i) in drawerInfo.introduction" :key="i">{{item}}</p>
                <p>如有疑问，请联系：{{drawerInfo.contacts}}</p>
                <p>联系方式：{{drawerInfo.phone}}</p>
            </div>
            <div class="drawer-footer">
                <el-button @click="notDisplay">取 消</el-button>
                <el-button v-if="isEnrolableFlag" type="primary" @click="enroll">立即报名</el-button>
            </div>
        </el-drawer>
    </div>
</template>
<script>
export default {
    props: {
        drawerInfo: Object,
        flag: Boolean,
        isEnrolable: Boolean
    },
    data() {
        return {
            drawerInfoFlag: this.flag,
            isEnrolableFlag: this.isEnrolable
        };
    },
    watch: {
        flag(newVal) {
            this.drawerInfoFlag = newVal;
        },
        drawerInfoFlag(newVal) {
            if (newVal == false) {
                this.notDisplay();
            }
        }
    },
    methods: {
        notDisplay() {
            this.$emit("notDisplay");
        },
        enroll() {
            this.$emit("enroll");
        }
    }
};
</script>
<style lang="scss" scoped>
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