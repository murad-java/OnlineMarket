import axios from 'axios'

const instance =axios.create({
    baseURL: 'https://gamit.az:8800/operation',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    },
})

export default instance