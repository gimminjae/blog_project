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
// import {Editor} from "@toast-ui/vue-editor";
import {Editor} from "@toast-ui/editor";

export default {
  name: "PostForm",
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
  },
  data() {
    return {
      title: '',
      content: '',
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
      axios.post("/api/post", {
        title: this.title,
        content: this.content
      }, config)
          .then(() => {
            router.push({path: `/${VueCookies.get('nickname')}`});
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