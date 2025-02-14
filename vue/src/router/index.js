import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementView from '@/views/ElementView.vue'
import LoginView from '@/views/LoginView.vue'

Vue.use(VueRouter)

//解决Error: Avoided redundant navigation to current location: “/users”.
const originalPush = VueRouter.prototype.push
   VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: ()=>import('../views/Manager.vue'),
    redirect:'/home',
    children:[
      {path:'403', name:'Auth',meta:{name:'403'}, component:()=>import('../views/manager/Auth.vue')},
      {path:'home', name:'Home',meta:{name:'系统首页'}, component:()=>import('../views/manager/Home')},
      {path:'user',name:'User',meta:{name:'用户信息'},component:()=>import('../views/manager/User')},
      {path:'personal',name:'Personal',meta:{name:'个人信息'},component:()=>import('../views/manager/Personal')},
    ]
  },{
    path: '/login',
    name: 'Login',
    meta:{name:'登入'},
    component:LoginView
  },{
    path:'/register',
    name:'register',
    meta:{name:'注册'},
    component:()=> import('../views/RegisterView.vue')
  },
  {path:'*',name:'404',meta:{name:'404'},component:()=>import('../views/404.vue')}

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由神器（路由守卫）
// to  到达的路由信息
// from 来源的路由信息
// next 帮助我们跳转路由的函数
router.beforeEach((to,from,next)=>{
  //只有管理员可以访问的页面
  let adminRoule = ['/user']
  //获取当前登入的用户信息
  let user = JSON.parse(localStorage.getItem('honey-user') || '{}')
  //判断当前用户是否是管理员并且跳转的页面是否是有权限访问的
  if(user.role !== '管理员' && adminRoule.includes(to.path)){
    next('/403')
  }
  next()
})

export default router
