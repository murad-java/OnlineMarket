import axios from 'axios'

const instance =axios.create({
    baseURL: 'http://192.168.1.123:8800/operation',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    },
})

export default instance