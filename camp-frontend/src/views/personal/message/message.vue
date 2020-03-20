<template>
    <div class="message-all-container">
        <div class="list-container">
            <message-list />
        </div>
        <resize-line :firstMaxValue="500" :firstMinValue="250" />
        <div class="detail-container">
            <message-detail />
        </div>
    </div>
</template>
<script>
import resizeLine from "../../../components/resizeLine.vue";
import messageList from "./messageList.vue";
import messageDetail from "./messageDetail.vue";
export default {
    components: {
        resizeLine,
        messageList,
        messageDetail
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