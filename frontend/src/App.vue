<template>
  <AppHeader></AppHeader>
  <RouterView></RouterView>
</template>

<script>
import {useRoute} from "vue-router";
import {watch} from "vue";
import VueCookies from "vue-cookies";
import AppHeader from "@/pages/AppHeader";
import store from "@/script/store";
import router from "@/script/router";
import axios from "axios";

export default {
  name: 'App',
  components: {
    AppHeader
  },
  methods: {
    check() {
      const jwt = VueCookies.get('access_token');

      let config = {
        headers: {
          "Authorization": jwt
        }
      }
      axios.get("/api/member/me", config)
          .then(res => {
            const user = res.data.member;
            console.log(res);

            if (user != null) {
              store.commit('setUser', user);
            }
          })
          .catch(() => {
            router.push({path: '/login'});
              }
          );
    }
  },
  created() {
    //쿠키의 jwt로 회원정보를 가져옴
    const route = useRoute();

    watch(route, () => {
      this.check();
    });
  }
}
</script>

<style>

</style>
