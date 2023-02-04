<template>
  <div class="mt-3">
    <div class="input-group mb-3" style="width: 50%;">
      <span class="input-group-text" id="basic-addon1">제목</span>
      <input type="text" class="form-control" aria-label="Username"
             aria-describedby="basic-addon1" v-model="title" id="title">
    </div>
    <div>
      <div id="editor"></div>
    </div>
    <div class="d-flex justify-content-center my-3">
      <button class="btn btn-primary" style="width: 40%;" @click="submit">저장</button>
    </div>
  </div>
</template>
<script>
import VueCookies from "vue-cookies";
import axios from "axios";
import router from "@/script/router";
import {Editor} from "@toast-ui/editor";

export default {
  name: "PostModify",
  components: {

  },
  mounted() {
      this.editor = new Editor({
        el: document.querySelector('#editor'),
        initialEditType: 'markdown',
        previewStyle: 'vertical',
        height: '600px'
      });
      this.editor.setMarkdown('');

      this.postId = this.$route.params.id;

    //글 정보 가져오기
    axios.get(`/api/post/${this.postId}`)
        .then(({data}) => {
          this.title = data.title;
          this.content = data.content;
          this.editor.setMarkdown(data.content);
        })
        .catch((error) => {
          alert(error.response.data);
        });
  },
  data() {
    return {
      title: '',
      content: '',
      postId: 0
    }
  },
  methods: {
    //글 작성
    submit() {
      this.content = this.editor.getMarkdown();

      if (this.title.trim().length == 0) {
        alert('제목을 입력하세요');
        document.getElementById('title').focus();
        return;
      }
      if (this.content.trim().length == 0) {
        alert('내용을 입력하세요');
        this.editor.focus();
        return;
      }
      let config = {
        headers: {
          "Authorization": VueCookies.get('access_token')
        }
      }
      axios.put(`/api/post/${this.postId}`, {
        title: this.title,
        content: this.content
      }, config)
          .then(() => {
            router.push({path: `/post/${this.postId}`});
          })
          .catch((error) => {
            alert('글 작성 실패 : ' + error.response.data);
          });
    }
  }
}
</script>
<style>

</style>