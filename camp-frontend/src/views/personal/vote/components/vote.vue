<template>
    <div v-loading="loading">
        <div class="vote-title">
            <h2>{{voteInfo.name}}</h2>
        </div>
        <div class="info-container">
            <p>
                <span class="span-tip">类型：</span>
                <span class="span-content">{{voteInfo.type}}</span>
                <span class="span-tip">最大可选数目：</span>
                <span class="span-content">{{voteInfo.num}}</span>
            </p>
        </div>
        <div>
            <div class="vote-choose">
                <el-radio-group v-if="voteInfo.type=='单选'" v-model="radio" class="radio-group">
                    <el-radio
                        v-for="(item,i) in voteInfo.options"
                        :key="i"
                        :label="item"
                        class="radio"
                    ></el-radio>
                </el-radio-group>
                <el-checkbox-group
                    v-if="voteInfo.type=='多选'"
                    v-model="voteArray"
                    class="checkbox-group"
                >
                    <el-checkbox
                        v-for="(item,i) in voteInfo.options"
                        :key="i"
                        :label="item"
                        class="checkbox"
                    ></el-checkbox>
                </el-checkbox-group>
            </div>
        </div>
        <div class="tac">
            <el-button type="primary" @click="vote">提交</el-button>
            <el-button @click="$emit('cancel')">取消</el-button>
        </div>
    </div>
</template>
<script>
import voteApi from "../../../../api/modules/vote";
export default {
    props: {
        voteId: {
            require: true,
            type: String
        }
    },
    data() {
        return {
            voteInfo: {
                id: "123",
                name: "投票",
                date: "2020-02-02 18:00:00",
                type: "多选",
                num: 10,
                options: [
                    "选项",
                    "选项2选项2选项2选项2选项2选项2选项2选项2选项2选项2选项2选项2选项2选项2选项2"
                ]
            },
            radio: -1,
            voteArray: [],
            loading: false
        };
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        }
    },
    methods: {
        async getVoteOptions() {
            try {
                let res = null;
                if (this.idType == 0) {
                    res = await voteApi.studentGetVoteDetail(this.voteId);
                } else {
                    res = await voteApi.memberGetVoteDetail(this.voteId);
                }
                this.voteInfo = res.data;
            } catch (e) {
                this.$message.error(e.message);
            }
        },
        async vote() {
            this.loading = true;
            let select = this.voteArray;
            if (this.voteInfo.type == "单选") {
                select = [this.radio];
            }
            let data = [];
            select.forEach(val => {
                let index = this.voteInfo.options.indexOf(val);
                data.push({ index, text: val });
            });
            let loadData = { voteId: this.voteId, data };
            try {
                if (this.idType == 0) {
                    await voteApi.studentVote(loadData);
                } else {
                    await voteApi.memberVote(loadData);
                }
                this.$message.success("投票成功！");
                this.$emit("vote");
            } catch (error) {
                this.$message.error(error.message);
            }
            this.loading = false;
        }
    }
};
</script>
<style lang="scss">
.table-container {
    margin-top: 50px;
    ::v-deep .el-table__header-wrapper {
        thead {
            .cell {
                font-size: 16px;
                font-weight: 100;
                color: black;
            }
        }
    }
}
.vote-title {
    width: auto;
    text-align: center;
    font-size: 16px;
    color: black;
}
.info-container,
.option-container {
    text-align: center;
    margin: 20px auto;
    font-size: 16px;
    .span-tip {
        margin-right: 10px;
        margin-left: 30px;
        color: rgb(96, 98, 102);
    }
    .span-content {
        color: rgb(64, 158, 255);
    }
}
.vote-choose {
    margin-top: 20px;
    margin-bottom: 20px;
    .radio-group {
        display: block;
        width: 100%;
        .el-radio {
            padding: 10px 20px;
            display: block;
            margin: 0 auto;
            .el-radio__inner {
                border-width: 3px;
            }
        }
    }
    .checkbox-group {
        margin: 20px auto;
        .checkbox {
            padding: 10px 0 10px 20px;
            display: block;
            .el-checkbox__label {
                width: 100%;
            }
        }
    }
}
</style>