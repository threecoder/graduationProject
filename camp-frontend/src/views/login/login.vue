<template>
  <el-form>
    <el-form-item>
      <el-input suffix="el-icon-user" placaholder="请输入账号" v-model="userForm.username">
        <template slot="prepend">
          <i class="userIcon"></i>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-input suffix="el-icon-user" placaholder="请输入密码">
        <template slot="prepend">
          <i class="pasIcon"></i>
        </template>
      </el-input>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      userForm: {
        username: null,
        password: null
      }
    };
  },
  methods: {}
};
</script>
<style lang="scss" scoped>
.userIcon {
    
}
</style>
