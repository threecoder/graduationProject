<template>
    <div class="all-container">
        <div class="index-container">
            <div class="header">
                <h1>课程名</h1>
            </div>
            <div class="divider"></div>
            <div class="content">
                <div>
                    <h3>考试名称</h3>
                    <h4>考试说明</h4>
                    <ul>
                        <li><p>考试次数：剩余3次</p></li>
                        <li><p>考试时长：2小时</p></li>
                        <li><p>及格分数：60分</p></li>
                        <li><p>如果遇到考试异常，在考试时间内可以重新进入考试</p></li>
                    </ul>
                </div>
                <el-button class="button" type='primary'>进 入 考 试</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {};
</script>
<style lang="scss" scoped>
.all-container {
    background-color: rgb(243,244,248);
    height: 100%;
}
.index-container {
    position: absolute;
    background-color: #fff;
    top: 100px;
    left: calc(50% - 300px);
    width: 600px;
    height: 660px;
    padding: 20px;
    text-align: center;
    .header {
        height: 150px;
        h1 {
            color: orange;
            font-weight: 400;
            line-height: 150px;
            font-size: 45px;
        }
    }
    .content {
        h3 {
            color: rgb(64, 158, 255);
            font-weight: 400;
            margin-top: 30px;
        }
        h4 {
            font-weight: 200;
            margin-top: 40px;
        }
        ul {
            max-width: 388px;
            list-style: none;
            margin: 50px auto 50px;
            li {
                // text-align: left;
                margin: 20px 0;
                border-bottom: 1px solid rgb(240, 240, 240);
                height: 25px;
                line-height: 25px;
                p {
                    color: rgb(100, 100, 100);
                }
            }
        }
        .button {
            min-width: 300px;
            height: 50px;
        }
    }
}
</style>