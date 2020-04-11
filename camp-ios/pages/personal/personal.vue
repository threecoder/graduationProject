<template>
    <view>
        <view class="my-top"></view>
        <view class="person-info" hover-class="person-info--hover">
            <view class="item">
                <text>
                    <text class="name">{{name}}</text>
                    <text class="type">{{type}}</text>
                </text>
            </view>
            <view class="item item-arrow">
                <uni-icons :size="20" class="my-arrow" color="#bbb" type="arrowright" />
            </view>
        </view>
        <uni-list>
            <uni-list-item
                v-for="(item, i) in list"
                :key="i"
                :title="item.title"
                @click="to(item)"
                :show-extra-icon="true"
                :extra-icon="{ color: '#4cd964', size: '16', type: 'info' }"
            ></uni-list-item>
        </uni-list>
    </view>
</template>

<script>
import uniIcons from "../../components/uni-icons/uni-icons.vue";
import uniList from "../../components/uni-list/uni-list.vue";
import uniListItem from "../../components/uni-list-item/uni-list-item.vue";
export default {
    components: {
        uniIcons,
        uniList,
        uniListItem
    },
    data() {
        return {
            list: [
                {
                    title: "我的信息",
                    name: "info"
                },
                {
                    title: "我的证书",
                    name: "info"
                },
                {
                    title: "我的培训",
                    name: "info"
                },
                {
                    title: "我的考试",
                    name: "info"
                },
                {
                    title: "我的活动",
                    name: "info"
                },
                {
                    title: "我的投票",
                    name: "info"
                },
                {
                    title: "我的订单",
                    name: "info"
                }
            ],
            name: "张三",
            type: "学员"
        };
    },
    methods: {
        to(item) {
            this.$Router.push({name: 'info'})
            // uni.navigateTo({
            //     url: "/pages/info/info?id=111"
            // });
        }
    }
};
</script>

<style lang="scss" scoped>
.my-top {
    background-color: rgb(64, 158, 255);
}

.person-info {
    height: 0.8rem;
    width: 100%;
    background-color: rgb(64, 158, 255);
    display: flex;
    justify-content: flex-end;
    align-items: center;
    .item {
        width: 50%;
        height: 0.8rem;
        line-height: 0.8rem;
        margin-top: 0.2rem;
    }
    .name {
        display: block;
        font-size: 0.20rem;
        color: #fff;
        margin-left: 0.3rem;
        float: left;
    }
    .type {
        display: block;
        font-size: 0.14rem;
        color: #fff;
        margin-left: 0.3rem;
        float: left;
    }
    .item-arrow {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        padding-right: 0.3rem;
    }
    .my-arror {
        margin-right: 0.3rems;
    }
}
.person-info--hover {
    background-color: rgb(84, 178, 255);
}
</style>
