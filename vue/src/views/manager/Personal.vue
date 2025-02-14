<template>
  <el-card style="width: 50%">
    <el-form :model="user" label-width="80px">
      <div style="margin: 15px; text-align: center">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:9090/file/upload"
            :headers="{ token:user.token }"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="user.avatar" :src="user.avatar" class="avatar"/>
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="user.username" placeholder="用户名" disabled=""/>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="user.name" placeholder="姓名"/>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="user.phone" placeholder="手机号"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="user.email" placeholder="邮箱"/>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input type="textarea" v-model="user.address" placeholder="地址"/>
      </el-form-item>
      <div style="text-align: center;margin-bottom: 20px">
        <el-button type="primary" @click="saveUser">保存</el-button>
      </div>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "personal",
  data(){
    return{
      user:JSON.parse(localStorage.getItem('honey-user') || '{}')
    }
  },
  methods:{
    saveUser(){
      this.$request.put('/user/update',this.user).then(res=>{
        if(res.code = '200'){
          this.$message.success('修改成功');
          this.$emit('update:user',this.user)
          localStorage.setItem('honey-user',JSON.stringify(this.user));
        }else{
          this.$message.error('修改失败');
        }
      })
    },
    handleAvatarSuccess(response,file,fileList){
     this.user.avatar = response.data;
    }
  }
}
</script>


<style scoped>
::v-deep(.avatar-uploader .el-upload) {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
::v-deep(.avatar-uploader){
  position:relative;
}

::v-deep(.el-upload){
  border-radius: 50% !important;
}

::v-deep(.avatar-uploader .el-upload:hover) {
  border-color: var(--el-color-primary);
}

::v-deep(.avatar-uploader-icon) {
   font-size: 117px;
   color: #8c939d;
  text-align: center;
}
.el-icon-plus{
  border-radius: 50%;
  border:1px dashed #8c939d;
  width: 178px;
  height: 178px;
}
.avatar-uploader-icon:before {
  font-size: 28px;
}
.avatar{
  width: 178px;
  height: 178px;
  border-radius: 50%;
  display: block;
}
</style>