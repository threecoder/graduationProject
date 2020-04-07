<template>
    <div class="single-choice box-card">
        <p>
            <span class="blue">{{item.index}}.</span>
            <span v-if="item.type==0" class="blue">[单选题]</span>
            <span v-else-if="item.type==1" class="blue">[多选题]</span>
            <span v-else-if="item.type==2" class="blue">[判断题]</span>
            {{item.title}}
            <span class="grey">({{item.score}}分)</span>
            <span class="fr" :class="{'redWords':!flag,'greenWords':flag}">{{flag==true?"正确":"错误"}}</span>
        </p>
        <ul>
            <li v-for="(t,i) in item.arr" :key="i" :class="getClass(i)">
                <span>{{getWord(i)}}{{t}}</span>
            </li>
        </ul>
        <div class="summary">
            <p>正确答案：{{getWord(item.rightAnswer)}}</p>
            <p>作答选择：{{getWord(item.studentAnswer)}}</p>
        </div>
    </div>
</template>
<script>
export default {
    props: ["item"],
    data() {
        return {};
    },
    computed: {
        flag() {
            let arr1 = this.item.rightAnswer;
            let arr2 = this.item.studentAnswer;
            if (arr1.length != arr2.length) {
                return false;
            }
            arr1.sort((a, b) => a - b);
            arr2.sort((a, b) => a - b);
            let t = true;
            for (let i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    t = false;
                    break;
                }
            }
            return t;
        }
    },
    methods: {
        getWord(i) {
            if (!Array.isArray(i)) {
                switch (i) {
                    case 0:
                        return "A.";
                    case 1:
                        return "B.";
                    case 2:
                        return "C.";
                    case 3:
                        return "D.";
                    default:
                        return "";
                }
            } else {
                let str = "";
                for (let index = 0; index < i.length; index++) {
                    switch (i[index]) {
                        case 0:
                            str += "A";
                            break;
                        case 1:
                            str += "B";
                            break;
                        case 2:
                            str += "C";
                            break;
                        case 3:
                            str += "D";
                            break;
                        default:
                            str += "";
                    }
                }
                return str;
            }
        },
        getClass(i) {
            let t1 = this.item.rightAnswer.indexOf(i);
            let t2 = this.item.studentAnswer.indexOf(i);
            let res = [];
            if (t1 != -1) {
                res.push("green");
            } else if (t2 != -1) {
                res.push("red");
            } else {
                res.push("");
            }
            return res;
        }
    }
};
</script>
<style lang="scss" scoped>
.single-choice {
    box-shadow: 0 0 10px #888888;
    margin: 50px auto 0;
    padding: 20px;
    width: 60%;
    p {
        font-size: 18px;
        color: rgb(150, 150, 150);
        .blue {
            color: rgb(27, 157, 253);
            margin-right: 10px;
        }
        .grey {
            color: grey;
        }
    }
    ul {
        list-style: none;
        margin-top: 20px;
        li {
            height: 40px;
            border: 1px solid rgb(234, 234, 234);
            font-size: 18px;
            margin-top: 15px;
            line-height: 40px;
            padding: 0 20px;
            .el-radio {
                width: 100%;
            }
            ::v-deep .el-radio__label {
                font-size: 16px;
            }
            ::v-deep .el-checkbox__label {
                font-size: 16px;
            }
        }
    }
    .green {
        background-color: rgb(61, 167, 57);
        opacity: 0.7;
        color: #fff;
    }
    .red {
        background-color: rgb(214, 54, 54);
        opacity: 0.7;
        color: #fff;
    }
    .greenWords {
        color: rgb(61, 167, 57);
    }
    .redWords {
        color: rgb(214, 54, 54);
    }
    .summary {
        margin-top: 20px;
        padding-left: 10px;
        p {
            margin-top: 5px;
        }
    }
}
</style>