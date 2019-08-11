<template>
  <el-form>
    <el-form-item>
      <el-input suffix="el-icon-user" placeholder="请输入账号" v-model="userForm.username">
        <template slot="prepend">
          <i class="userIcon"></i>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-input suffix="el-icon-user" placeholder="请输入密码" v-model="userForm.password">
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
.el-form {
  
  width: 60%;
  .el-input-group__prepend {
    padding: 0 10px;
  }
  .userIcon {
    background: url("./images/user.png") no-repeat center center;
    display: inline-block;
    width: 40px;
    height: 30px;
  }
  .pasIcon {
    background: url("./images/psw.png") no-repeat center center;
    display: inline-block;
    width: 40px;
    height: 30px;
  }
  
}
</style>
