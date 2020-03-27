<template>
    <div>
        <student-certificate :type="studentType" v-if="idType==0" />
        <member-certificate v-else />
    </div>
</template>
<script>
import studentCertificate from "./studentCertificate.vue";
import memberCertificate from "./memberCertificate.vue";
export default {
    components: {
        studentCertificate,
        memberCertificate
    },
    computed: {
        idType: function() {
            return this.$store.getters.idType;
        },
        studentType: function() {
            let path = this.$route.path;
            return path == "/certificate";
        }
    }
};
</script>
<style lang="scss" scoped>
</style>