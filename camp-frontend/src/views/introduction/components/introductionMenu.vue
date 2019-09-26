<template>
    <div class="intMenu">
        <el-row class="tac">
            <h3>协会介绍</h3>
            <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :router="true"
            :unique-opened="true">
            <el-menu-item index="/brief">协会简介</el-menu-item>
            <el-menu-item index="/constitution">协会章程</el-menu-item>
            <el-menu-item index="/framework">组织架构</el-menu-item>
            </el-menu>
        </el-row>
    </div>
</template>

<script>
export default {
    data(){
        return{
            
        }
    }
}
</script>

<style lang="scss">
.intMenu{
    width: 130px;
    float: left;
    margin-left: 30px;
    h3{
        font-size: 22px;
    }
}
</style>