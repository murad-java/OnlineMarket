import axios from 'axios';
import Toasty from "@/api/Toasty";


const ax = axios.create({
    baseURL: 'https://gamit.az:8800/user',
    withCredentials: true,
    headers: {
        accept: 'application/json'
    },
})

class AuthService {

    login(user) {
        const res = ax
            .post('/login', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                    if (response.data.jwt) {
                        localStorage.setItem('user', JSON.stringify(response.data));
                        localStorage.setItem('jwt',response.data.jwt)
                    }

                    return response;
                }
            ).catch(error => {
                Toasty.showError(error.message)
            });
        return res;
    }
    registration(data){
        return  ax
            .post('/register', {
                username: data.username,
                password: data.password,
                email: data.email,
                address: data.address,
                phone: data.phone,
                photoUrl: data.photoUrl,

            })
            .then(response => {

                    if (response.data.jwt) {
                        localStorage.setItem('user', JSON.stringify(response.data));
                        localStorage.setItem('jwt',JSON.stringify(response.data.jwt))
                    }
                    return response;
                }
            ).catch(error => {
                Toasty.showError(error.message)
            });

    }

    logout() {
        localStorage.removeItem('user')
        localStorage.removeItem('jwt')
    }
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
    iAM() {
       return  ax.get('/iam', {
            headers: {
                'Authorization': `Bearer ${this.getJWT()}`
            }
        })
            .then(response => {
                    if (response.data.id === this.getCurrentUser().user.id) return  true
                    else {
                        this.logout()
                        return false
                    }
                }
            ).catch(() => {

            return  false
        });
    }

    getJWT(){
      let jwt='';
      try {
          jwt = this.getCurrentUser().jwt
      }catch (err){
          jwt=''
      }
      return jwt;
    }

    getUserName(){
        let un=''
        try {
           un= this.getCurrentUser().user.username
        }catch (err){
            un=''
        }
        return un
    }
}

export default new AuthService();