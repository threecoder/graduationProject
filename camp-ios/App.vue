<template>
    <view class="all-container">
        <router-view></router-view>
    </view>
</template>

<script>
import { request, Encrypt } from "./api/request.js";
export default {
    data() {
        return {
            res: null
        };
    },
    onLaunch: function() {
        console.log("App Launch");
    },
    onShow: function() {
        console.log("App Show");
        // let data = {
        //     username: "15521054785",
        //     password: Encrypt("123456")
        // };
        // request("/login", "post", data)
        //     .then(res => {
        //         this.res = res;
        //         console.log("登录", res);
        //     })
        //     .catch(e => {
        //         // uni.
        //     });
    },
    onHide: function() {
        console.log("App Hide");
    }
};
</script>

<style>
/*每个页面公共css */
* {
    padding: 0;
    margin: 0;
}
html {
    font-size: 100px!important;
}
.my-top {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 0.2rem;
    z-index: 998;
	background-color: rgb(64, 158, 255);
}
.container {
	background-color: #eee;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
}
</style>
