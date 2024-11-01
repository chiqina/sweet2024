<template>
    <div>
        <!-- <el-row>
            <el-col :span="12">
                <div style="width:100%;height:300px;background-color: red;"></div>
            </el-col>
            <el-col :span="12">
                <div style="width:100%;height:300px;background-color: green;"></div>
            </el-col>
        </el-row> -->
        <el-row>
            <el-col :span="6">
                <div style="padding:10px;border: 1px solid #ccc; text-align: center;">
                    <img style="width: 80%;" src="@/assets/logo.png" alt="">
                    <div style="text-align: center;">这是一个很牛的描述</div>
                    <div style="text-align: center; color: red;">$19.99</div>
                </div>
            </el-col>
            <el-col :span="6">
                <div style="padding:10px;border: 1px solid #ccc; text-align: center;">
                    <img style="width: 80%;" src="@/assets/logo.png" alt="">
                    <div style="text-align: center;">这是一个很牛的描述</div>
                    <div style="text-align: center; color: red;">$19.99</div>
                </div>
            </el-col>
            <el-col :span="6">
                <div style="padding:10px;border: 1px solid #ccc; text-align: center;">
                    <img style="width: 80%;" src="@/assets/logo.png" alt="">
                    <div style="text-align: center;">这是一个很牛的描述</div>
                    <div style="text-align: center; color: red;">$19.99</div>
                </div>
            </el-col>
            <el-col :span="6">
                <div style="padding:10px;border: 1px solid #ccc; text-align: center;">
                    <img style="width: 80%;" src="@/assets/logo.png" alt="">
                    <div style="text-align: center;">这是一个很牛的描述</div>
                    <div style="text-align: center; color: red;">$19.99</div>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <!-- 普通html按钮 -->
            <button>普通的html按钮</button>
            <!-- element按钮 -->
            <el-button>默认按钮</el-button>
            <el-button type="primary">主要按钮</el-button>
            <el-button type="success">成功按钮</el-button>
            <el-button type="info">信息按钮</el-button>
            <el-button type="warning">警告按钮</el-button>
            <el-button type="danger">危险按钮</el-button>
        </el-row>
        <!-- 朴素(颜色变淡)按钮样式 使用 plain -->
        <el-row>
            <el-button plain>朴素按钮</el-button>
            <el-button type="primary" plain>主要按钮</el-button>
            <el-button type="success" plain>成功按钮</el-button>
            <el-button type="info" plain>信息按钮</el-button>
            <el-button type="warning" plain>警告按钮</el-button>
            <el-button type="danger" plain>危险按钮</el-button>
        </el-row>
        <!-- 圆角按钮样式 -->
        <el-row>
            <el-button round>圆角按钮</el-button>
            <el-button type="primary" round>主要按钮</el-button>
            <el-button type="success" round>成功按钮</el-button>
            <el-button type="info" round>信息按钮</el-button>
            <el-button type="warning" round>警告按钮</el-button>
            <el-button type="danger" round>危险按钮</el-button>
        </el-row>
        <!-- 图标按钮 -->
        <el-row>
            <el-button circle></el-button>
            <el-button type="primary" icon="el-icon-edit" circle></el-button>
            <el-button type="success" icon="el-icon-check" circle></el-button>
            <el-button type="info" icon="el-icon-message" circle></el-button>
            <el-button type="warning" icon="el-icon-star-off" circle></el-button>
            <el-button type="danger" icon="el-icon-delete" circle></el-button>
        </el-row>

        <el-row style="margin-top: 20px;">
            <el-col>
                <el-input style="width: 200px;" placeholder="请输入内容" v-model="userName"></el-input>
                <el-input style="width: 200px;" placeholder="请输入密码" v-model="passWord" show-password></el-input>
                <!-- 图标input -->
                <el-input style="width: 200px;" aria-placeholder="请输入内容" prefix-icon="el-icon-search"
                    v-model="value"></el-input>
            </el-col>
        </el-row>
        <!-- 条件选择框 -->
        <el-row style="margin-top: 30px;">
            <el-autocomplete class="inline-input" v-model="goods" :fetch-suggestions="querySearch" placeholder="请输入内容"
                ></el-autocomplete>
        </el-row>
        <!-- 计数器 -->
        <el-row style="margin-top: 20px">
            <el-input-number v-model="number" :min="1" :max="10" label="描述文字" @change="handleChange" :disabled="true"></el-input-number>
        </el-row>
        <!-- 表格 -->
        <el-row>
            <el-table :data="tableData">
                <el-table-column prop="date" label="日期"></el-table-column>
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
            </el-table>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userName: '',
            passWord: '',
            value: '',
            info:[{"value":'赵琴'},{"value":'赵芳琴'},{"value":'赵云'},{"value":'梁佳龙'},{"value":'田志清'},{"value":'田博宇'},{"value":'朱四海'},{"value":'朱郴州'},{"value":'朱大财'},{"value":'梁小龙'}],
            goods:'',
            number:1,
            tableData:[{
                date:'2016-05-02',
                name:'赵琴',
                address:'湖南省怀化市黄江山'
            },{
                date:'2016-06-02',
                name:'梁佳龙',
                address:'江西省宜春县' 
            },{
                date:'2016-07-02',
                name:'田志清',
                address:'湖南省邵东市'   
            }]
        }
    },
    methods:{
        querySearch(query,cb){
            let request = query?this.info.filter(val=>val.value.includes(query)): ['暂无数据！！'];
            console.log(request);
            cb(request)
        },
        handleChange(num){
            console.log(num);
        }

    }
}
</script>