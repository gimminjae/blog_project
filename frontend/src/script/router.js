import {createRouter, createWebHistory} from "vue-router";
import AppLogin from "@/pages/AppLogin";
import PostForm from "@/pages/PostForm";
import AppHome from "@/pages/AppHome";
import AppJoin from "@/pages/AppJoin";

const routes = [
    {path:'/', component: AppHome},
    // {path: '/detail/:id', component: BoardDetail},
    {path: '/login', component: AppLogin},
    {path: '/join', component: AppJoin},
    {path: '/write', component: PostForm},
    // {path: '/modify/:id', component: BoardModify},
]
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;