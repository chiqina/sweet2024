import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ElementView from '@/views/ElementView.vue'
import LoginView from '@/views/LoginView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },{
    path: '/element',
    name: 'home',
    component: ElementView
  },{
    path: '/login',
    name: 'Login',
    component:LoginView
  },{
    path:'/register',
    name:'register',
    component:()=> import('../views/registerView.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
