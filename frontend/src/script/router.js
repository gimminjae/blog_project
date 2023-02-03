import {createRouter, createWebHistory} from "vue-router";

const routes = [
    // {path:'/', component: AppHome},
    // {path: '/detail/:id', component: BoardDetail},
]
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;