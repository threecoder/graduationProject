<template>
    <div class="container">
        <div class="logo-infor">
            <div class="logo">LOGO</div>
            <div class="infor">
                <p class="cn">协会考试与证书管理平台</p>
                <p class="en">Association examination and certificate management platform</p>
            </div>
        </div>
        <div class="key-word">
            <div class="word-container">
                <span>关键字</span>
                <span>关键字</span>
                <span>关键字</span>
            </div>
        </div>
    </div>
</template>
<script>
export default {};
</script>
<style lang="scss" scoped>
.container {
    background-color: rgb(64, 158, 255);
    border-bottom: 1px solid grey;
    min-width: 1030px;
    .logo-infor {
        float: left;
        height: 130px;
        .logo {
            float: left;
            width: 150px;
            height: 60px;
            margin-top: 35px;
            margin-left: 25px;
        }
        .infor {
            float: left;
            height: 60px;
            margin-top: 30px;
            margin-left: 50px;
            p.cn {
                line-height: 40px;
                font-size: 30px;
                color: rgb(96, 98, 102);
            }
            p.en {
                line-height: 20px;
                color: rgb(96, 98, 102);
            }
        }
    }
    .key-word {
        float: right;
        width: 280px;
        .word-container {
            margin-top: 40px;
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-content: center;
            span {
                color: rgb(96, 98, 102);
            }
        }
    }
}
</style>