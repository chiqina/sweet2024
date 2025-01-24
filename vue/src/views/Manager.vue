<template xmlns="http://www.w3.org/1999/html">
  <div>
    <el-container>
      <el-aside :width="asideWidth" style="min-height: 100vh; background-color:#001529;">
        <div style="height: 60px;  color:white; display: flex; align-items: center; justify-content: center;">
          <img src="@/assets/img/logo.jpg" alt="" style="width: 40px; height: 40px;border-radius: 50%;">
          <transition name="el-fade-in-linear">
            <span style="margin-left:5px;font-size: 20px; line-height: 40px;" v-show="!isCollapse">honey2024</span>
          </transition>
        </div>
        <el-menu router :collapse="isCollapse"  style="border:none;" background-color="#001529"
          text-color="rgba(255,255,255,0.65)" active-text-color="#fff" :default-active="$route.path">
          <el-menu-item index="/home">
            <template>
              <i class="el-icon-s-home"></i>
              <span slot="title">系统首页</span>
            </template>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>信息管理</span>
            </template>
            <el-menu-item index="/user">用户信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <div style="display: flex; align-items: center; height: 100%;">
            <i :class="collapseIcon" style="font-size: 26px;" @click="handleCollapse"></i>
           <el-breadcrumb>
             <el-breadcrumb-item :to="{ path: '/' }" style="margin-left: 20px">首页</el-breadcrumb-item>
             <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
           </el-breadcrumb>
          </div>
          <div style="flex:1;width:0;display: flex; align-items: center; justify-content: flex-end;">
              <i class="el-icon-quanping" style="font-size: 26px" @click="headleFull"></i>
              <el-dropdown placement="bottom">
                 <div style="display: flex; align-items: center; cursor: default;">
                   <img src="@/assets/img/logo.jpg" alt="" style="width: 40px; height: 40px;border-radius: 50%; margin:0 5px">
                   <span>管理员</span>
                 </div>
                  <el-dropdown-menu>
                  <el-dropdown-item>个人信息</el-dropdown-item>
                  <el-dropdown-item>修改密码</el-dropdown-item>
                  <el-dropdown-item @click.native="logout">退出入</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src



import request from "@/utils/request.js";

export default {
  name: 'HomeView',
  data() {
    return {
      isCollapse: false,
      asideWidth: '200px',
      collapseIcon: 'el-icon-s-fold',
      users:[],
    }
  },
  mounted(){
    // request.get('/user/selectAll').then(res => {
    //   this.users = res.data;
    // })
  },
  methods: {
    logout(){
      localStorage.removeItem('honey-user');
      this.$router.push('/login');
    },
    handleCollapse() {
      this.isCollapse = !this.isCollapse;
      this.asideWidth = this.isCollapse ? '64px' : '200px';
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold';
    },
    headleFull(){
       document.documentElement.requestFullscreen()
    }
  }
}
</script>

<style>
.el-menu--inline {
  background-color: #000c17 !important;
}

/*  这个是整个菜单栏+菜单栏列的样式 */
.el-menu--inline .el-menu-item {
  background-color: #000c17 !important;
  padding-left: 49px !important;

}

.el-menu-item:hover,
.el-submenu__title:hover {
  color: #fff !important;
}

.el-menu-item.is-active {
  /* margin: 4px !important; */
  background-color: #1890ff !important;
  border-radius: 5px !important;
  width: calc(100% - 8px);
  margin-left: 4px;
}

.el-submenu .el-menu-item {
  min-width: 0 !important;
}

.el-menu--inline .el-menu-item.is-active {
  padding-left: 45px !important;
}

.el-menu-item.is-active i,
.el-menu-item.is-active .el-tooltip {
  margin-left: -4px;
}

.el-menu-item {
  /* margin: 4px; */
  height: 40px !important;
  line-height: 40px !important;
}

.el-submenu__title {
  /* margin: 4px; */
  height: 40px !important;
  line-height: 40px !important;
}

.el-aside {
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
}

.el-header {
  display: flex;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
}
</style>
