<template>
    <div>
        <h2>
            <span class="red">4</span>
            <span class="green">0</span>
            <span class="blue">4</span>
        </h2>
        <p>不好意思，你访问的页面走失啦</p>
    </div>
</template>
<script>
export default {};
</script>
<style lang="scss" scoped>
div {
    overflow: hidden;
    h2 {
        margin-top: 200px;
        text-align: center;
        font-size: 90px;
        .red {
            color: red;
        }
        .green {
            color: green;
        }
        .blue {
            color: blue;
        }
    }
    p {
        text-align: center;
        font-size: 20px;
        color: rgb(32, 32, 32);
    }
}
</style>