<template>
    <div class="single-choice">
        <p>
            <span class="blue">{{index}}.</span>
            <span v-if="type==1" class="blue">[单选题]</span>
            <span v-else class="blue">[多选题]</span>
            {{title}}
            <span class="grey">({{score}}分)</span>
        </p>
        <ul v-if="type==1">
            <li v-for="(item,i) in arr" :key="i">
                <el-radio v-model="choice[index-1]" :label="i+1">{{getWord(i)}}{{item}}</el-radio>
            </li>
        </ul>
        <template v-else>
            <el-checkbox-group @change="mulChange" v-model="mulChoice">
                <ul>
                    <li v-for="(item,i) in arr" :key="i" >
                        <el-checkbox :label="item"></el-checkbox>
                    </li>
                </ul>
            </el-checkbox-group>
        </template>
    </div>
</template>
<script>
export default {
    props: ["index", "title", "score", "arr", "choice", "type"],
    data() {
        return {
            mulChoice: []
        };
    },
    methods: {
        mulChange(){
            console.log(this.mulChoice);
            while(this.choice[this.index-1].length!=0){
                this.choice[this.index-1].pop();
            }
            this.mulChoice.forEach(val=>{
                let t = this.arr.indexOf(val);
                if(t!=-1){
                    this.choice[this.index-1].push(t+1);
                }
            });
        },
        getWord(i){
            switch(i){
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
        }
    }
};
</script>
<style lang="scss" scoped>
.single-choice {
    margin-top: 50px;
    padding: 20px;
    p {
        font-size: 18px;
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
        li {
            height: 40px;
            border: 1px solid rgb(234, 234, 234);
            font-size: 18px;
            margin-top: 20px;
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
}
</style>