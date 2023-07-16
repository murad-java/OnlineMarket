import axios from 'axios';
// import Toasty from "@/api/Toasty";


const ax = axios.create({
  baseURL: 'https://gamit.az:8800/operation/buy',
  withCredentials: true,
  headers: {
    accept: 'application/json'
  },
})

class BuyService{
  async getBuy() {
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

  async buy(productId) {
    return this.operation('/product',productId)
  }
  async buyFromCart() {
    return this.operation('/fromCart',null)
  }
  async getOrders(){
    return this.operation('/get/products',null)
  }
  async operation(path,id) {
    const jwt = localStorage.getItem('jwt');
    if (jwt != null) {
      try {
        const response = await ax.post(path,  {
          productId:id
        },{
          headers: {
            'Authorization': `Bearer ${jwt}`
          },

        });
        return response;
      } catch (error) {
        console.log(error);
        return null;
      }
    }
    return null;
  }

}
export default new BuyService()
