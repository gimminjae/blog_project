<template>
  <div class="container">
    <div class="d-flex gap-3">
      <div>
        <h1>{{ this.member.nickname }}</h1>
        <h5>{{ this.member.email }}</h5>
      </div>
      <div>
        {{ this.member.introduce }}
      </div>
    </div>
    <table class="table table-hover">
      <thead class="table">
      <tr class="text-center">
        <th style="width: 70%;">제목</th>
        <th>작성일시</th>
      </tr>
      </thead>
      <tbody>
      <tr class="text-center" v-for="post in postList" :key="post">
        <td><router-link :to="{path: `/post/${post.id}`, }">{{ post.title }}</router-link></td>
        <td>{{ post.createDateTime }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import axios from "axios";
import VueCookies from "vue-cookies";

export default {
  name: "UserPage",
  components: {},
  data() {
    return {
      member: {},
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