import {createRouter, createWebHistory} from "vue-router";
import AppLogin from "@/pages/AppLogin";
import PostForm from "@/pages/PostForm";
import AppHome from "@/pages/AppHome";
import AppJoin from "@/pages/AppJoin";
import UserPage from "@/pages/UserPage";
import PostDetail from "@/pages/PostDetail";

const routes = [
    {path:'/', component: AppHome},
    {path: '/post/:id', component: PostDetail},
    {path: '/login', component: AppLogin},
    {path: '/join', component: AppJoin},
    {path: '/write', component: PostForm},
    {path: '/:nickname', component: UserPage}
    // {path: '/modify/:id', component: BoardModify},
]
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;