<template>
  <div class="container" style="width: 80%;">
    <h1>{{ post.title }}</h1>
    <div id="viewer" class="my-3"></div>
  </div>
</template>
<script>
import axios from "axios";
import router from "@/script/router";
import VueCookies from "vue-cookies";
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';

export default {
  name: "PostDetail",
  data() {
    return {
      post: {},
      viewer: null,
      postId: 0
    }
  },
  mounted() {
    this.postId = this.$route.params.id;
    this.getPost();
  },
  methods: {
    getPost() {
      axios.get(`/api/post/${this.postId}`)
          .then(({data}) => {
            this.post = data;
            this.getViewer();
            this.viewer.setMarkdown(this.post.content);
          }).catch((error) => {
        alert(error);
        router.push({path: `/${VueCookies.get('nickname')}`});
      })
    },
    getViewer() {
      this.viewer = new Viewer({
        el: document.querySelector('#viewer'),
        // 표시하고자 하는 내용은 여기에 들어간다.
      });
    }
  }
}
</script>
<style scoped>

</style>