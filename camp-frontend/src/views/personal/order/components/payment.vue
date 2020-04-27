<template>
    <div></div>
</template>
<script>
import orderApi from "../../../../api/modules/order";
export default {
    props: {
        orderId: {
            required: true,
            type: String
        }
    },
    data() {
        return {
            content: null
        };
    },
    mounted() {
        this.pay();
    },
    methods: {
        async pay() {
            let data = {
                orderId: this.orderId,
                returnUrl: "/order",
                serverPort: "8888"
            };
            try {
                let res = await orderApi.aliPay(data);
                this.$_putForm(res.data);
                console.log("支付返回", res);
            } catch (error) {
                this.$message.error(error.message);
            }
        },
        $_putForm(str) {
            let div = document.createElement("form-container");
            div.innerHTML = str;
            let divs = document.getElementsByTagName("form-container");
            if (divs.length != 0) {
                document.body.removeChild(divs[0]);
            }
            document.body.appendChild(div);
            let myForm = document.forms[document.forms.length - 1];
            myForm.setAttribute("target", "_blank"); // 新开窗口跳转
            myForm.submit();
        }
    }
};
</script>
<style lang="scss" scoped>
</style>