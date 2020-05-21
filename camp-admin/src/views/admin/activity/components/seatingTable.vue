<template>
    <div class="all-container">
        <div class="panel">
            <el-input
                ref="input"
                autofocus
                :disabled="tem.lock"
                v-model="tem.num"
                placeholder="输入人员编号，编号查看导出的报名表"
            ></el-input>
            <el-button
                size="small"
                type="primary"
                @click="lock"
                :disabled="tem.x<1||tem.y<1"
            >{{tem.lock==false?`锁定席位`:`解除锁定`}}</el-button>
            <el-button
                size="small"
                type="primary"
                @click="saveOneNum"
                :disabled="tem.x<1||tem.y<1"
            >确认修改</el-button>
        </div>

        <div class="contain">
            <div class="index-container">
                <template v-for="i in attr.xLen">
                    <div :key="i">
                        <span class="index">{{i}}</span>
                    </div>
                </template>
            </div>
            <div class="table-container">
                <template v-for="i in attr.xLen">
                    <div class="row" :key="i">
                        <template v-for="j in attr.yLen">
                            <div class="colunm" :key="j">
                                <span
                                    class="seat"
                                    :class="{green:Arr[i-1][j-1].num !== null,lock:Arr[i-1][j-1].lock}"
                                    @click="showPanel(i,j)"
                                >{{Arr[i-1][j-1].num===null?"":Arr[i-1][j-1].num}}</span>
                            </div>
                        </template>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props: {
        attr: {
            type: Object,
            default() {
                return {};
            }
        },
        Arr: {
            type: Array
        }
    },
    data() {
        return {
            tem: {
                num: "",
                x: null,
                y: null,
                lock: false
            },
            flag: false
        };
    },
    mounted() {
        console.log("座位表挂载");
    },
    methods: {
        showPanel(i, j) {
            this.tem.num = this.Arr[i - 1][j - 1].num;
            this.tem.x = i;
            this.tem.y = j;
            this.tem.lock = this.Arr[i - 1][j - 1].lock;
            this.flag = true;
            this.$refs.input.focus();
        },
        lock() {
            let t = this.tem;
            t.lock = !t.lock;
            this.$emit("lock", t.x - 1, t.y - 1, t.lock);
        },
        saveOneNum() {
            let t = this.tem;
            this.$emit("num", t.x - 1, t.y - 1, t.num);
        }
    }
};
</script>
<style lang="scss" scoped>
.all-container {
    text-align: center;
    .contain {
        display: flex;
        flex-direction: row;
        justify-content: center;
        .table-container {
            flex-shrink: 1;
            display: flex;
            flex-direction: column;
            .row {
                display: flex;
                flex-direction: row;
                .seat {
                    display: block;
                    width: 35px;
                    height: 35px;
                    border: 1px solid orange;
                    margin: 5px;
                    text-align: center;
                    line-height: 35px;
                    cursor: pointer;
                    color: orange;
                    background-color: #fff;
                    color: black;
                }
                .seat:hover {
                    background-color: orange;
                    border-color: orange;
                }
                .green {
                    background-color: green;
                    border-color: green;
                }
                .lock {
                    background-color: #000;
                    border-color: #000;
                }
            }
        }
        .index-container {
            flex-shrink: 1;
            display: flex;
            flex-direction: column;
            overflow: hidden;
            .index {
                display: block;
                width: 30px;
                height: 35px;
                margin: 6px 0;
                line-height: 30px;
                text-align: center;
            }
        }
    }
    .panel {
        width: 30%;
        margin: 0 auto 20px;
        .el-input {
            margin-bottom: 10px;
        }
    }
}
</style>