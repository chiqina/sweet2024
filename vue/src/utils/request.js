import axios from 'axios'
import router from "@/router";

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 3000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
     let user = JSON.parse(localStorage.getItem('honey-user') || '{}')
    config.headers['token'] = user.token
    return config
}, error => {
    console.log('request error:', error)
    return Promise.reject(error)
})

//request 拦截
request.interceptors.response.use(
    response => {
        let res = response.data
        if (typeof res === 'string'){
            res = res ?JSON.parse(res) :res
        }
        if(res.code == '401'){
            router.push('/login')
        }
        return res
    },
    error => {
        return Promise.reject(error)
    })

export default request

