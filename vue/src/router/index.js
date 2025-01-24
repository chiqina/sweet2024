import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementView from '@/views/ElementView.vue'
import LoginView from '@/views/LoginView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: ()=>import('../views/Manager.vue'),
    children:[
      {path:'home', name:'Home', component:()=>import('../views/manager/Home')},
      {path:'user',name:'User',component:()=>import('../views/manager/User')}
    ]
  },{
    path: '/login',
    name: 'Login',
    component:LoginView
  },{
    path:'/register',
    name:'register',
    component:()=> import('../views/RegisterView.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
