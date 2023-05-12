import axios from "axios";

const ax =axios.create({
    baseURL: 'https://gamit.az:8800/operation',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    },
})

class ImageService {
    getImagesByProductId(id){
       return  ax.get('/product/img/'+id)
    }
}

export default new ImageService

