import axios from "axios";

const ax =axios.create({
    baseURL: 'http://192.168.1.123:8804',
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

