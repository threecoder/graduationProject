<template>
    <div>
        <introductionMenu></introductionMenu>
        <router-view></router-view>
    </div>
        
</template>

<script>
import introductionMenu from "./components/introductionMenu.vue"
export default {
    data(){
        return{
            
        }
    },
    components:{
        introductionMenu
    },
    computed:{
        
    }
}
</script>
<style lang="scss">
.intText{
    margin-left: 5%;
    margin-top: 80px;
    float: left;
}
</style>