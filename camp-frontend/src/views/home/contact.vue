<template>
    <div>
        <div class="img-container">
            <p>您的反馈和建议是我们前进的动力</p>
        </div>
        <div class="contact-info">
            <div class="info">
                <p>
                    <i class="el-icon-phone"></i>联系电话：010-222222
                </p>
            </div>
            <div class="info">
                <p>
                    <i class="el-icon-message"></i>联系邮箱：222222222@qq.com
                </p>
            </div>
            <div class="info">
                <p>
                    <i class="el-icon-message"></i>地址：广东省广州市番禺区小谷围街道华南理工大学
                </p>
            </div>
        </div>
        <div class="footer">
            <div class="QRCode-container">
                <div class="QRImg">
                    <img
                        src="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090743294,1994693594&fm=26&gp=0.jpg"
                        alt
                    />
                </div>
                <p>扫描关注公众号</p>
            </div>
        </div>
    </div>
</template>
<script>
export default {};
</script>
<style lang="scss" scoped>
.img-container {
    height: 150px;
    width: 100%;
    background: url("../../assets/images/contact.jpg") no-repeat center;
    p {
        font-size: 30px;
        line-height: 150px;
        margin-left: 10%;
    }
}
.contact-info {
    background-color: #ccc;
    padding: 30px;  
    .info {
        // width: 40%;
        height: 100px;
        p {
            color: grey;
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
.footer {
    width: 100%;
    height: 340px;
    background-color: rgb(100,100,100);
    overflow: hidden;
    .QRCode-container {
        float: right;
        width: 250px;
        display: flex;
        flex-direction: columns;
        flex-wrap: wrap;
        justify-content: center;
        .QRImg {
            width: 250px;
            height: 250px;
            img {
                width: 100%;
                height: 100%;
            }
        }
    }
}
</style>