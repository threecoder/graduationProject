<template>
    <div>
        <h3>发起投票</h3>
        <div class="divider"></div>
        <el-col :span="16" :offset="4">
            <el-form :model="form" ref="ruleForm" label-position="top">
                <el-row :gutter="80">
                    <el-col :span="10">
                        <el-form-item label="投票名称" prop="name">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="投票类型" prop="type">
                            <el-select v-model="form.type" placeholder="投票的类型">
                                <el-option :value="0" label="仅限会员投票"></el-option>
                                <el-option :value="1" label="仅限学员投票"></el-option>
                                <el-option :value="2" label="学员会员均可"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="80">
                    <el-col :span="10">
                        <el-form-item label="最大可选数量" prop="num">
                            <el-select v-model="form.num" prop="num">
                                <el-option v-for="i in optionsNum" :key="i" :label="i" :value="i"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="投票截止时间" prop="time">
                            <el-date-picker type="datetime" v-model="form.time" :clearable="false" />
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-form-item
                    v-for="(item,i) in options"
                    :key="i"
                    :label="`选项${i+1}`"
                    prop="option"
                >
                    <el-col :span="20">
                        <el-input v-model="item.text"></el-input>
                    </el-col>
                    <el-col :span="4" class="option-oper">
                        <el-tooltip content="在这个选项下面添加一个选项">
                            <i class="el-icon-plus" @click="addOption(i)"></i>
                        </el-tooltip>
                        <el-tooltip content="删除这个选项">
                            <i class="el-icon-delete" @click="deleteOption(i)"></i>
                        </el-tooltip>
                    </el-col>
                </el-form-item>
                <el-col :span="8" :offset="8">
                    <el-button type="primary" @click="submit">新建投票</el-button>
                </el-col>
            </el-form>
        </el-col>
    </div>
</template>
<script>
import voteApi from "../../../api/admin/vote";
import { formatDateAndTime } from "../../../assets/js/util";
export default {
    data() {
        return {
            form: {
                name: null,
                type: null,
                num: null,
                time: null
            },
            rules: {
                name: [
                    {
                        required: true,
                        message: "请输入投票名称",
                        trigger: "blur"
                    }
                ],
                option: [
                    {
                        required: true,
                        message: "投票选项不能为空",
                        trigger: "blur"
                    }
                ],
                type: [
                    {
                        required: true,
                        message: "投票类型必选",
                        trigger: "blur"
                    }
                ],
                num: [
                    {
                        required: true,
                        message: "可选选项数目不能为空",
                        trigger: "blur"
                    }
                ],
                time: [
                    {
                        required: true,
                        message: "截止时间不能为空",
                        trigger: "blur"
                    }
                ]
            },
            options: [{ text: null }, { text: null }]
        };
    },
    computed: {
        optionsNum() {
            return this.options.length;
        }
    },
    methods: {
        addOption(index) {
            if (this.options.length >= 10) {
                this.$message.error("最多只有十个选项");
                return false;
            }
            this.options.splice(index + 1, 0, { text: null });
        },
        deleteOption(index) {
            if (this.options.length <= 2) {
                this.$message.error("至少需要有两个选项");
                return false;
            }
            this.options.splice(index, 1);
        },
        checkFormItem() {
            let res = { flag: true };
            if (!this.form.name) {
                res.flag = false;
                res.message = "投票名称必须填";
            } else if (this.form.type === null) {
                res.flag = false;
                res.message = "投票的类型必须选";
            } else if (this.form.num < 1) {
                res.flag = false;
                res.message = "最大可选数量必选";
            } else if (this.form.time === null) {
                res.flag = false;
                res.message = "投票截止时间必填";
            } else {
                for (let i = 0; i < this.options.length; i++) {
                    if (!this.options[i].text) {
                        res.flag = false;
                        res.message = "所有选项不能为空";
                        break;
                    }
                }
            }
            return res;
        },
        async submit() {
            let valid = this.checkFormItem();
            if (valid.flag) {
                this.$confirm(
                    "发起投票之后不能修改，请确认填写信息无误",
                    "提示",
                    {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }
                ).then(async () => {
                    let data = { ...this.form };
                    data.options = this.options.map(val => val.text);
                    data.time = formatDateAndTime(this.form.time);
                    console.log(data);
                    try {
                        let res = await voteApi.addNewVote(data);
                        this.$message.success("添加新投票成功");
                        this.$router.push("/publishedVote");
                    } catch (error) {
                        this.$message.error(error.message);
                    }
                });
            } else {
                this.$message.error(valid.message);
            }
        }
    }
};
</script>
<style lang="scss" scoped>
.option-oper {
    // text-align: center;
    i {
        margin: auto 10px;
        cursor: pointer;
        // color: rgb(58, 144, 255);
        color: rgb(96, 98, 158);
        font-size: 18px;
        &:hover {
            color: rgb(64, 158, 255);
        }
    }
}
</style>