<template>
    <div>
        <div class="img-container">
            <p>您的反馈和建议是我们前进的动力</p>
        </div>
        <div class="contact-info">
            <div class="info">
                <p><i class="el-icon-phone"></i>联系电话：010-222222</p>
            </div>
            <div class="info">
                <p><i class="el-icon-message"></i>联系邮箱：222222222@qq.com</p>
            </div>
            <div class="info">
                <p><i class="el-icon-message"></i>地址：广东省广州市番禺区小谷围街道华南理工大学</p>
            </div>
        </div>
        <div class="divider"></div>
    </div>
</template>
<script>
export default {
    
}
</script>
<style lang="scss" scoped>
.img-container {
    height: 150px;
    width: 100%;
    background: url("../assets/images/contact.jpg") no-repeat center;
    p {
        font-size: 30px;
        line-height: 150px;
        margin-left: 10%;
    }
}
.contact-info {

    .info {
        width: 40%;
        height: 100px;
        p {
            margin: auto;
            font-size: 24px;
            line-height: 100px;
            i {
                line-height: 30px;
                margin: 0 10px;
            }
        }
    }
}
</style>