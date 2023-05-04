import axios from 'axios'

const instance =axios.create({
    baseURL: 'http://3.78.84.198:8800/operation',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    },
})

export default instance