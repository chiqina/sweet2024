import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 3000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
    return config
}, error => {
    console.log('request error:', error)
    return Promise.reject(error)
})

request.interceptors.response.use(response => {
        let res = response.data
        if (typeof res === 'string'){
            res = res ?JSON.parse(res) :res
        }
        return res
    },
        error => {
        console.log('response error:', error)
        return Promise.reject(error)
})

export default request

