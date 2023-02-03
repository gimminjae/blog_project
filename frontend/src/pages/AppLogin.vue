<template>
  <div class="mx-auto my-auto" style="max-width: 50%; min-width: 40%;">
    <div class="mb-3 mt-3">
      <h3>ID</h3>
      <input class="form-control form-control-lg" type="text" placeholder="ID(username)" id="username"
             aria-label=".form-control-lg example" v-model="this.MemberDto.username" @keyup.enter="login">
    </div>
    <div class="mb-3">
      <h3>PW</h3>
      <input class="form-control form-control-lg" type="password" placeholder="password" id="password"
             aria-label=".form-control-lg example" v-model="this.MemberDto.password" @keyup.enter="login">
    </div>
    <div class="d-flex justify-content-around mb-3">
      <button style="width: 48%;" type="button" class="btn btn-primary" @click="login">로그인</button>
      <router-link to="/join" style="width: 48%;" type="button" class="btn btn-info">회원가입</router-link>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import VueCookies from "vue-cookies";
import router from "@/script/router";

export default {
  name: "AppLogin",
  components: {},
  methods: {
    login() {
      if (this.MemberDto.username.trim().length == 0) {
        alert('아이디를 입력하세요');
        document.getElementById('username').focus();
        return;
      }
      if (this.MemberDto.password.trim().length == 0) {
        alert('비밀번호를 입력하세요');
        document.getElementById('password').focus();
        return;
      }
      axios.post("/api/member/login", this.MemberDto)
          .then(({data}) => {
            //토큰
            const jwt = data.accessToken;
            const nickname = data.nickname;

            //토큰의 유효기간
            const expire = 60 * 60 * 24;

            //쿠키에 토큰을 저장한다.
            //보안 옵션을 줘도 https를 사용하지 않으면 보안 옵션은 작동하지 않는다.
            VueCookies.set('access_token', jwt, {
              expires: expire,
              secure: true,
              httpOnly: true,
              sameSite: 'strict'
            });
            VueCookies.set('nickname', nickname, {
              expires: expire,
              secure: true,
              httpOnly: true,
              sameSite: 'strict'
            });

            //로그인 후 홈페이지로 이동
            router.push({path: '/'});
          })
          .catch((error) => {
            alert(error.response.data);
          });
    }
  },
  data() {
    return {
      MemberDto: {
        username: "",
        password: ""
      }
    }
  },
}
</script>
<style scoped>

</style>