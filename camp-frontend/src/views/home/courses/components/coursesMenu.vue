<template>
    <div class="intMenu">
        <el-menu
            :default-active="deActive"
            class="el-menu-vertical-demo"
            :unique-opened="true"
            mode="horizontal"
        >
            <el-menu-item
                v-for="(item,i) in itemList"
                :key="i"
                @click="changeContent(item.key)"
                :index="item.key"
            >
                <i
                    :class="{'el-icon-caret-bottom':isActive(item.key),'el-icon-caret-right':!isActive(item.key)}"
                ></i>
                {{item.title}}
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
export default {
    data() {
        return {
            deActive: "往期培训",
            itemList: [
                {
                    title: "往期培训",
                    key: "previous"
                },
                {
                    title: "正在培训",
                    key: "now"
                },
                {
                    title: "未来培训",
                    key: "future"
                }
            ]
        };
    },
    methods: {
        isActive(val) {
            return val === this.deActive;
        },
        changeContent(val) {
            this.deActive = val;
            this.$emit("switch", val);
        }
    }
};
</script>

<style lang="scss" scoped>
.intMenu {
    width: 100%;
    border: 1px solid rgb(188, 225, 237);
    .title {
        width: 90%;
        margin: 10px auto;
        background-color: rgb(112, 189, 234);
        height: 30px;
        line-height: 30px;
        color: #fff;
    }
    .el-menu {
        height: 30px;
        background-color: rgb(234, 242, 245);
        .el-menu-item {
            border-bottom: 1px solid #9ab2bc;
            height: 30px;
            line-height: 30px;
            &:hover {
                background-color: rgb(112, 189, 234);
            }
        }
    }
    .is-active {
        color: rgb(250, 56, 57);
    }
}
</style>