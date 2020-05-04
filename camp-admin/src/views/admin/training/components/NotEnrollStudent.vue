<template>
    <div>
        <div class="form-container">
            <el-form :model="form" inline>
                <el-form-item size="mini" label="姓名">
                    <el-input v-model="form.name" clearable></el-input>
                </el-form-item>
                <el-form-item size="mini" label="证件号码">
                    <el-input v-model="form.idNum" clearable></el-input>
                </el-form-item>
                <el-form-item size="mini" label="所属公司">
                    <el-input v-model="form.company" clearable></el-input>
                </el-form-item>
                <el-form-item size="mini">
                    <el-button size="mini" type="primary" @click="getNotEnrollStudent">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table-container">
            <m-table
                :tableConfig="table.config"
                :data="table.data"
                :tableAttr="table.attr"
                :tableEvent="table.event"
            >
                <el-table-column
                    slot="select"
                    type="selection"
                    slot-scope="{params}"
                    v-bind="params"
                ></el-table-column>
                <el-table-column slot="oper" align="center" slot-scope="{params}" v-bind="params">
                    <div slot-scope="{row}">
                        <el-button size="mini" type="primary" @click="prompt('single', row)">提醒报名</el-button>
                    </div>
                </el-table-column>
            </m-table>
        </div>
        <div class="page-container">
            <page
                :total="form.total"
                :currentPage="form.currentPage"
                :pageSize="form.pageSize"
                @curChange="curChange"
            />
        </div>
        <div class="button-container tac">
            <el-button size="small" type="primary" @click="prompt('select')">提示所选</el-button>
            <el-button size="small" type="primary" @click="prompt('current')">提示当前页</el-button>
            <el-button size="small" type="primary" @click="promptAll">提示所有人</el-button>
        </div>
    </div>
</template>
<script>
import mTable from "../../../../components/mTable.vue";
import page from "../../../../components/page.vue";
import studentApi from "../../../../api/admin/student";
import trainingApi from "../../../../api/admin/training";
export default {
    components: {
        mTable,
        page
    },
    data() {
        return {
            form: {
                trainingId: this.$route.query.trainingId,
                name: null,
                idNum: null,
                currentPage: 1,
                pageSize: 10,
                total: 2,
                company: null
            },
            table: {
                config: [
                    {
                        slot: "select",
                        fixed: "left",
                        "reserve-selection": true
                    },
                    { prop: "idNum", label: "学员id" },
                    { prop: "name", label: "学员名字" },
                    { prop: "phone", label: "学员电话" },
                    { prop: "company", label: "所属公司" },
                    { slot: "oper", label: "操作" }
                ],
                data: [
                    {
                        idNum: "445281199308310056",
                        name: "模拟数据",
                        phone: "123456",
                        company: "公司"
                    },
                    {
                        idNum: "445281199308310037",
                        name: "模拟数据",
                        phone: "123456",
                        company: "公司"
                    }
                ],
                attr: {
                    "row-key": "idNum"
                },
                event: {
                    "selection-change": this.selectChange
                }
            },
            selected: []
        };
    },
    mounted() {
        this.getNotEnrollStudent();
    },
    methods: {
        async getNotEnrollStudent() {
            try {
                console.log(this.form);
                let res = await trainingApi.getNotEnrollStudentList(this.form);
                this.form.total = res.data.total;
                this.table.data = res.data.list;
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        getPromptTarget(type, row) {
            if (type == "single") {
                return [row.idNum];
            } else {
                let res = [],
                    tar = [];
                if (type == "select") {
                    tar = this.selected;
                } else if (type == "current") {
                    tar = this.table.data;
                }
                res = tar.map(val => val.idNum);
                return res;
            }
        },
        prompt(type, row) {
            this.$confirm("确定要提醒报名吗", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(async () => {
                try {
                    let tar = this.getPromptTarget(type, row);
                    if (tar.length <= 0) {
                        throw new Error("请选择要提醒报名的目标");
                    }
                    let data = {
                        idNums: tar,
                        trainingId: this.form.trainingId
                    };
                    let res = await trainingApi.promptStudentEnrollTraining(
                        data
                    );
                    this.$message.success("提醒成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        promptAll() {
            this.$confirm("确定要提醒报名吗", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(async () => {
                try {
                    let res = await trainingApi.promptAllStudentEnrollTraining(
                        this.form.trainingId
                    );
                    this.$message.success("提醒成功");
                } catch (error) {
                    this.$message.error(error.message);
                }
            });
        },
        curChange(newVal) {
            this.form.currentPage = newVal;
            // if (newVal == 1) {
            //     this.table.data = [
            //         {
            //             idNum: "111",
            //             name: "模拟数据",
            //             phone: "123456",
            //             company: "公司"
            //         }
            //     ];
            // } else {
            //     this.table.data = [
            //         {
            //             idNum: "2222",
            //             name: "模拟数据",
            //             phone: "123456",
            //             company: "公司"
            //         }
            //     ];
            // }
            this.getNotEnrollStudent();
        },
        selectChange(newVal) {
            this.selected = newVal;
            console.log(newVal);
        }
    }
};
</script>
<style lang="scss" scoped>
.page-container {
    overflow: hidden;
}
.button-container {
    margin-top: 30px;
}
</style>