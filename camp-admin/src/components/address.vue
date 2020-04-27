<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="5">
                <el-select v-model="cprovince" placeholder="请选择省份" :disabled="disabled">
                    <el-option
                        v-for="(item,i) in provinceList"
                        :key="i"
                        :label="item"
                        :value="item"
                    ></el-option>
                </el-select>
            </el-col>
            <el-col :span="5">
                <el-select v-model="ccity" placeholder="请选择城市" :disabled="disabled">
                    <el-option v-for="(item,i) in cityList" :key="i" :label="item" :value="item"></el-option>
                </el-select>
            </el-col>
            <el-col :span="5">
                <el-select v-model="carea" placeholder="请选择区县" :disabled="disabled">
                    <el-option v-for="(item,i) in areaList" :key="i" :label="item" :value="item"></el-option>
                </el-select>
            </el-col>
            <el-col :span="6">
                <el-input v-model="czone" placeholder="请输入详细地址" :disabled="disabled"></el-input>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import cityApi from "../assets/js/city";
export default {
    props: {
        province: {
            type: String,
            default: null
        },
        area: {
            type: String,
            default: null
        },
        city: {
            type: String,
            default: null
        },
        zone: {
            type: String,
            default: null
        },
        disabled: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            provinceList: [],
            cityList: [],
            areaList: [],
            czone: this.zone,
            cprovince: this.province,
            ccity: this.city,
            carea: this.area
        };
    },
    watch: {
        cprovince(val) {
            this.ccity = null;
            this.carea = null;
            this.getCityList();
            this.getAreaList();
            this.$emit("provinceChange", val);
        },
        ccity(val) {
            this.carea = null;
            this.getAreaList();
            this.$emit("cityChange", val);
        },
        carea(val) {
            this.$emit("areaChange", val);
        },
        czone(val) {
            this.$emit("zoneChange", val);
        }
    },
    mounted() {
        this.getProvinceList();
        this.getCityList();
        this.getAreaList();
    },
    methods: {
        getProvinceList() {
            this.provinceList = cityApi.getProvinceList();
        },
        getCityList() {
            this.cityList = cityApi.getCityList(this.cprovince);
        },
        getAreaList() {
            this.areaList = cityApi.getAreaList(this.cprovince, this.ccity);
        }
    }
};
</script>

<style>
</style>