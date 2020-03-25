<template>
    <div>
        <p class="tip">
            <span>左侧列表项目</span>为
            <span>不开放的权限</span>，
            <span>右侧列表项目</span>则为
            <span>开放权限</span>，
            <span>移动列表项</span>修改当前账号权限
        </p>
        <div class="form-container">
            <el-transfer v-model="value" :data="data" :titles="titles"></el-transfer>
        </div>
        <div class="tac">
            <el-button size="small" @click="cancel">取消</el-button>
            <el-button type="primary" size="small" @click="modify">确定</el-button>
        </div>
    </div>
</template>
<script>
import { authorityList } from "../../../../const";
import systemApi from "../../../../api/admin/system";
export default {
    props: {
        account: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            valueObj: {},
            value: [],
            data: [],
            titles: ["不开放权限列表", "开放权限列表"]
        };
    },
    mounted() {
        this.init();
        this.getAuList();
    },
    methods: {
        init() {
            this.data = authorityList.map(val => {
                let obj = { ...val };
                obj.value = null;
                obj.disabled = false;
                this.$set(this.valueObj, val.key, false);
                return obj;
            });
        },
        async getAuList() {
            try {
                let res = await systemApi.getAuthority(this.account);
                for (let key in res.data) {
                    this.valueObj[key] = res.data[key];
                }
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        cancel() {
            this.$emit("cancel");
        },
        async modify() {
            this.value.forEach(val => {
                this.valueObj[val] = true;
            });
            try {
                let data = {
                    account: this.account,
                    authority: this.valueObj
                };
                let res = await systemApi.modifyAuthority(data);
                this.$message.success("修改当前账号权限成功");
                this.cancel();
            } catch (error) {
                this.$message.error(error.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.tip {
    font-size: 16px;
    span {
        color: rgb(64, 158, 255);
    }
}
.form-container {
    overflow: hidden;
    .el-transfer {
        max-width: 500px;
        margin: 20px auto;
    }
}
</style>