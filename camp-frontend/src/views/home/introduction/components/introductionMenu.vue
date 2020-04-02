<template>
    <div class="intMenu">
        <div class="title">协会介绍</div>
        <el-menu :default-active="deActive" class="el-menu-vertical-demo" :unique-opened="true">
            <el-menu-item @click="changeContent('简介')" index="简介">
                <i class="el-icon-minus"></i>协会简介
            </el-menu-item>
            <el-menu-item @click="changeContent('章程')" index="章程">
                <i class="el-icon-minus"></i>协会章程
            </el-menu-item>
            <el-menu-item @click="changeContent('架构')" index="架构">
                <i class="el-icon-minus"></i>组织架构
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script>
export default {
    data() {
        return {
            deActive: "简介"
        };
    },
    methods: {
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
    min-height: 680px;
    float: left;
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
        background-color: rgb(234, 242, 245);
        .el-menu-item {
            border-bottom: 1px solid #9ab2bc;
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