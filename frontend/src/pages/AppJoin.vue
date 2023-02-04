<template>
  <div class="mx-auto my-auto" style="max-width: 50%; min-width: 40%;">
    <div class="mb-3 mt-3">
      <h3>ID</h3>
      <input class="form-control form-control-lg" type="text" placeholder="ID(username)" id="username"
             aria-label=".form-control-lg example" v-model="this.MemberDto.username" @keyup.enter="join">
    </div>
    <div class="mb-3">
      <h3>PW</h3>
      <input class="form-control form-control-lg" type="password" placeholder="password" id="password1"
             aria-label=".form-control-lg example" v-model="this.MemberDto.password1" @keyup.enter="join">
    </div>
    <div class="mb-3">
      <h3>confirm PW</h3>
      <input class="form-control form-control-lg" type="password" placeholder="password" id="password2"
             aria-label=".form-control-lg example" v-model="this.MemberDto.password2" @keyup.enter="join">
    </div>
    <div class="mb-3">
      <h3>Nickname</h3>
      <input class="form-control form-control-lg" type="text" placeholder="nickname" id="nickname"
             aria-label=".form-control-lg example" v-model="this.MemberDto.nickname" @keyup.enter="join">
    </div>
    <div class="mb-3">
      <h3>Email</h3>
      <input class="form-control form-control-lg" type="email" placeholder="이메일은 비밀번호 변경, 혹은 아이디 찾기 등에 이용될 수 있습니다." id="email"
             aria-label=".form-control-lg example" v-model="this.MemberDto.email" @keyup.enter="join">
    </div>
    <div class="mb-3">
      <h3>Introduce</h3>
      <textarea class="form-control form-control-lg" type="text" placeholder="어떤 것에 관심이 있고, 무엇을 하는지, 무엇을 목표로 하는지, 직업은 무엇인지, 자신을 소개해 보세요!" id="introduce"
                aria-label=".form-control-lg example" v-model="this.MemberDto.introduce" @keyup.enter="join"></textarea>
    </div>
    <div class="d-flex justify-content-around mb-3">
      <button style="width: 48%;" type="button" class="btn btn-info" @click="join">회원가입</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import router from "@/script/router";

export default {
  name: "AppJoin",
  methods: {
    join() {
      if (this.MemberDto.username.trim().length == 0) {
        alert('아이디를 입력하세요');
        document.getElementById('username').focus();
        return;
      }
      if (this.MemberDto.password1.trim().length == 0) {
        alert('비밀번호를 입력하세요');
        document.getElementById('password1').focus();
        return;
      }
      if (this.MemberDto.password2.trim().length == 0) {
        alert('비밀번호를 입력하세요');
        document.getElementById('password2').focus();
        return;
      }
      if (this.MemberDto.nickname.trim().length == 0) {
        alert('닉네임을 입력하세요');
        document.getElementById('nickname').focus();
        return;
      }
      if (this.MemberDto.email.trim().length == 0) {
        alert('이메일을 입력하세요');
        document.getElementById('email').focus();
        return;
      }
      axios.post(`/api/member/join`, this.MemberDto)
          .then(() => {
            router.push({path: '/login'});
          }).catch((error) => {
            alert(error.response.data);
      });
    }
  },
  data() {
    return {
      MemberDto: {
        username: "",
        password1: "",
        password2: "",
        nickname: "",
        email: "",
        introduce: ""
      }
    }
  },
}
</script>