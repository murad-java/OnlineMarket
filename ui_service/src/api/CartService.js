import axios from 'axios';
// import Toasty from "@/api/Toasty";


const ax = axios.create({
    baseURL: 'https://gamit.az:8800/cart',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    },
})

class CartService{
    async getCart() {
        const jwt = localStorage.getItem('jwt');
        if (jwt != null) {
            try {
                const response = await ax.get('/get', {
                    headers: {
                        'Authorization': `Bearer ${jwt}`
                    }
                });
                return response.data;
            } catch (error) {
                console.log(error);
                return [];
            }
        }
        return [];
    }
    async getCount() {
        const jwt = localStorage.getItem('jwt');
        if (jwt != null) {
            try {
                const response = await ax.get('/count', {
                    headers: {
                        'Authorization': `Bearer ${jwt}`
                    }
                });
                return response.data;
            } catch (error) {
                return null;
            }
        }
        return null;
    }
    async delete(id) {
        return this.operation('/delete',id)
    }
    async upCount(id) {
        return this.operation('/up',id)
    }
    async downCount(id) {
        return this.operation('/down',id)
    }
    async addToCart(productId) {
        return this.operation('/add',productId)
    }
    async operation(path,id) {
        const jwt = localStorage.getItem('jwt');
        if (jwt != null) {
            try {
                const response = await ax.post(path,  {
                    id:id
                },{
                    headers: {
                        'Authorization': `Bearer ${jwt}`
                    },

                });
                return response.data;
            } catch (error) {
                console.log(error);
                return null;
            }
        }
        return null;
    }

}
export default new CartService()