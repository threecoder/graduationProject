<template>
    <div>
        <h3>会员统计</h3>
        <div class="divider"></div>
        <div class="tab-container">
            <el-tabs type="border-card" @tab-click="handleClick">
                <el-tab-pane label="按地区统计">
                    <by-area />
                </el-tab-pane>
                <el-tab-pane label="按会费即将到期统计">
                    <by-expire />
                </el-tab-pane>
                <el-tab-pane label="按时间区间统计">
                    <by-time />
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>
<script>
import byTime from "./components/timeStatistics.vue";
import byArea from "./components/areaStatistics.vue";
import byExpire from "./components/expireStatistics.vue";
import event from "../../../assets/js/eventBus";
export default {
    components: {
        byTime,
        byArea,
        byExpire
    },
    data() {
        return {};
    },
    methods: {
        handleClick(com, Event) {
            let content = Event.target.innerHTML;
            if (content.includes("地区")) {
                event.$emit("diqu");
            } else if (content.includes("会费")) {
                event.$emit("huifei");
            } else {
                event.$emit("shijian");
            }
        }
    }
};
</script>
<style lang="scss" scoped>
</style>