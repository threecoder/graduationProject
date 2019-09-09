<template>
    <div>
        <el-form :model="searchParams">
            <el-row :gutter="40">
                <el-col :span="6">
                    <el-form-item>
                        <el-input v-model="searchParams.keyWord" placeholder="课程关键字"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="7">
                    <el-form-item>
                        <el-date-picker
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            v-model="searchParams.date"
                        ></el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item>
                        <el-button type="primary" round @click="search" >搜索</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </div>
</template>
<script>
import {formatDate} from '@/assets/js/util.js'
export default {
    data() {
        return {
            searchParams: {
                keyWord:null,
                date:null
            }
        };
    },
    methods: {
        search(){
            let par = {
                ...this.searchParams,
            }
            if(this.searchParams.date){
                par.startDate = formatDate(this.searchParams.date[0]),
                par.endDate = formatDate(this.searchParams.date[1])
            }else{
                par.startDate = par.endDate = null;
            }
            this.$emit("searchInfo",par);
        }
    }
};
</script>
<style lang="scss" scoped>
</style>