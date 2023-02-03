<template>
  마이페이지

  {{ this.member.nickname }}
  {{ this.member.email }}
  {{ this.member.createDateTime }}
  {{ this.postList }}
</template>
<script>
import axios from "axios";
import VueCookies from "vue-cookies";

export default {
  name: "UserPage",
  components: {

  },
  data() {
    return {
      member: {

      },
      postList: []
    }
  },
  mounted() {
    this.getMember();
  },
  methods: {
    getMember() {
      axios.get(`/api/member/${VueCookies.get("nickname")}`)
          .then(({data}) => {
            this.member = data.member;
            this.postList = data.postList;
          }).catch((error) => {
            console.log(error);
      })
    }
  }
}
</script>
<style scoped>

</style>