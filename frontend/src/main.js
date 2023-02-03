import { createApp } from 'vue'
import App from './App.vue'
import store from "@/script/store";
import router from "@/script/router";
import VueCookies from "vue-cookies";

createApp(App).use(store).use(VueCookies).use(router).mount('#app')
