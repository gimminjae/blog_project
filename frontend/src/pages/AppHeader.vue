<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <div>
        <router-link to="/" class="navbar-brand" href="#">Log</router-link>
        <router-link to="/write" class="navbar-brand" v-if="$store.state.user.id != 0" href="#">작성</router-link>
      </div>
      <div>
        <a @click="logout" class="navbar-brand" href="#" v-if="$store.state.user.id != 0">로그아웃</a>
        <router-link to="/login" class="navbar-brand" v-else>로그인</router-link>
        <router-link to="/join" class="navbar-brand" href="#" v-if="$store.state.user.id == 0">회원가입</router-link>
        <router-link :to="{path: `/${$store.state.user.nickname}`, }"
                     class="navbar-brand" v-if="$store.state.user.id != 0">마이페이지</router-link>
      </div>
    </div>
  </nav>
</template>
<script>
import store from "@/script/store";
import VueCookies from "vue-cookies";
import router from "@/script/router";

export default {
  name: 'AppHeader',
  components: {
  },
  methods: {
    logout() {
      store.commit('removeUser');
      VueCookies.remove('access_token');
      VueCookies.remove('nickname');
      router.push({path: '/'});
    }
  }
}
</script>
<style>

</style>