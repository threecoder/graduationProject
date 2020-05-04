<template>
    <div class="params">
        <h3>系统功能设置</h3>
        <div class="divider"></div>
        <div class="params-container" v-loading="loading">
            <div class="single-param" v-for="(item,i) in params" :key="i">
                <span>{{item.label}}</span>
                <div>
                    <el-select size="medium" v-if="item.type=='bool'" v-model="valueObj[item.key]">
                        <el-option value="是" label="是"></el-option>
                        <el-option value="否" label="否"></el-option>
                    </el-select>
                    <el-input-number
                        v-if="item.type=='number'"
                        size="small"
                        :min="10"
                        :max="120"
                        :step="10"
                        :step-strictly="true"
                        v-model="valueObj[item.key]"
                    ></el-input-number>
                    <el-input v-if="item.type=='input'" v-model="valueObj[item.key]"></el-input>
                </div>
            </div>
        </div>
        <div class="tac">
            <el-button type="primary" @click="modify" :loading="loading">修改参数</el-button>
        </div>
    </div>
</template>
<script>
import { systemParams } from "../../../const";
import systemApi from "../../../api/admin/system";
export default {
    data() {
        return {
            valueObj: {},
            params: systemParams,
            loading: false
        };
    },
    mounted() {
        this.getParams();
    },
    methods: {
        init() {
            systemParams.forEach(val => {
                this.$set(this.valueObj, val.key, null);
            });
        },
        async getParams() {
            this.loading = true;
            try {
                let res = await systemApi.getSystemParams();
                this.valueObj = res.data;
                // this.valueObj = {
                //     studentTrainig: true,
                //     trainingOrderTime: 20,
                //     activityOrderTime: 30,
                //     certificateOrderTime: 40,
                //     memberOrderTime: 50
                // };
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        },
        async modify() {
            this.loading = true;
            try {
                let res = await systemApi.modifySystemParams(this.valueObj);
                this.$message.success("修改系统参数成功");
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        }
    }
};
</script>
<style lang="scss" scoped>
.params {
    width: 100%;
    overflow: hidden;
    box-sizing: border-box;
    background-color: #fff;
    padding: 20px;
    margin: auto;
    .params-container {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        .single-param {
            width: 50%;
            overflow: hidden;
            span {
                margin-right: 15px;
                color: rgb(96, 98, 102);
            }
        }
    }
}
</style>