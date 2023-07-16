<template>
  <div class="modal" id="popup-modal" v-if="showModal" @click="closeModal">
    <div class="login rounded-4" v-if="logreg && !logModel">
      <section class="section-conten padding-y " style="min-height:84vh">


        <div class="card mx-auto rounded-4" style="max-width: 380px; margin-top:100px; border-radius: 10px" @click.stop>

          <div class="card-body" @click.stop>
            <span class="close-mod" @click="closeModal">&times;</span>
            <h4 class="card-title mb-4 text-center">Sign in</h4>
            <form>
              <div class="form-group">
                <input name="" class="form-control" placeholder="Username" type="text" v-model="userName">
              </div> <!-- form-group// -->
              <div class="form-group">
                <input name="" class="form-control" placeholder="Password" type="password" v-model="password">
              </div> <!-- form-group// -->

              <div class="form-group">
                <a href="#" class="float-right">Forgot password?</a>
                <label class="float-left custom-control custom-checkbox"> <input type="checkbox"
                                                                                 class="custom-control-input"
                                                                                 checked="">
                  <div class="custom-control-label"> Remember</div>
                </label>
              </div> <!-- form-group form-check .// -->
              <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" @click="login"> Login</button>
              </div> <!-- form-group// -->
            </form>
          </div> <!-- card-body.// -->
          <p class="text-center mt-4">Don't have account? <a href="#" style="color: #ea7629" @click="toReg">Sign up</a>
          </p>
          <br><br>
        </div> <!-- card .// -->


      </section>
    </div>

    <!-- ============================ COMPONENT REGISTER   ================================= -->

    <div class="registration" v-if="!logreg && !logModel">
      <section class="section-content padding-y">
        <div class="card mx-auto" style="max-width:520px;border-radius: 10px; margin-top:40px;">

          <article class="card-body" @click.stop>
            <span class="close-mod" @click="closeModal">&times;</span>
            <header class="mb-4"><h4 class="card-title text-center ">Sign up</h4></header>
            <form>
              <div class="form-row">
                <div class="form-group col-md-6">
                  <label>Email</label>
                  <input type="email" class="form-control" :class="{ 'err': showEmailError }" v-model="regEmail"
                         placeholder="">
                </div>
                <div class="col form-group col-md-6">
                  <label>Login</label>
                  <input type="text" class="form-control errClass" :class="{ 'err': showLoginError }"
                         v-model="regUserName" placeholder="">
                </div>
              </div>
              <div class="form-row">
                <div class="form-group col-md-6">
                  <label>Create password</label>
                  <input class="form-control errClass" :class="{ 'err': showPasswordError }" v-model="regPassword"
                         type="password">
                </div> <!-- form-group end.// -->
                <div class="form-group col-md-6">
                  <label>Repeat password</label>
                  <input class="form-control errClass" :class="{ 'err': showPasswordError }" v-model="regRepPassword"
                         type="password">
                </div> <!-- form-group end.// -->
              </div>
              <div class="form-row">
                <div class="form-group col-md-6">
                  <label>Country</label>
                  <select id="inputState" class="form-control" v-model="selectedCountry">
                    <option selected="">Azerbaijan</option>
                    <option>Uzbekistan</option>
                    <option>Russia</option>
                    <option>United States</option>
                    <option>India</option>
                  </select>
                </div>
                <div class="form-group col-md-6">
                  <label>Address</label>
                  <input type="text" class="form-control" v-model="regAddress">
                </div> <!-- form-group end.// -->
                <!-- form-group end.// -->
              </div> <!-- form-row.// -->


            </form>
            <div class="form-row">
              <div class="form-group col-md-3">
                <label>Code</label>
                <select id="inputState" class="form-control" v-model="selectedCode">
                  <option selected="">070</option>
                  <option>077</option>
                  <option>050</option>
                  <option>051</option>
                  <option>055</option>
                  <option>040</option>
                  <option>044</option>
                  <option>012</option>
                </select>
              </div>
              <div class="form-group col-md-9">
                <label>Phone</label>
                <input type="phone" class="form-control" v-model="regPhone">
              </div> <!-- form-group end.// -->
              <!-- form-group end.// -->
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-block" @click="registration"> Register</button>
            </div>
            <p class="text-center mt-4">Have an account? <a href="#" style="color: #ea7629" @click="toLogin">Log In</a>
            </p>
          </article><!-- card-body.// -->
        </div> <!-- card .// -->
        <br><br>
      </section>
    </div>
    <div v-if="logModel">
      <section class="section-content padding-y">
        <div class="card mx-auto" style="max-width:520px;border-radius: 10px;  margin-top:40px;">
          <article class="card-body" @click.stop>
            <span class="close-mod" @click="closeModal">&times;</span>
            <header class="mb-4"><h4 class="card-title text-center">{{ name }}</h4></header>
            <hr/>
            <div class="modal-body">
              <div class="row">
                <div class="col-md-4"><strong>Email:</strong></div>
                <div class="col-md-8">{{ user.email }}</div>
              </div>
              <div class="row">
                <div class="col-md-4"><strong>Адрес:</strong></div>
                <div class="col-md-8">{{ user.address }}</div>
              </div>
              <div class="row">
                <div class="col-md-4"><strong>Телефон:</strong></div>
                <div class="col-md-8">{{ user.phone }}</div>
              </div>
            </div>
            <hr/>
            <div class="modal-body">
              <div class="row">
                <div class="col-md-6"></div>
                <div class="col-md-3">
                  <button type="submit" class="btn btn-primary btn-block " @click="logout">LogOut</button>
                </div>
                <div class="col-md-3">
                  <button type="submit" class="btn btn-info btn-block " @click="closeModal"> Close</button>
                </div>
              </div>
            </div>
          </article>
        </div>
      </section>
    </div>
  </div>

</template>

<script>
import AuthService from "@/api/AuthService";
import Toasty from "@/api/Toasty";
import { mapActions } from 'vuex'
export default {
  data() {
    return {
      img: null,
      logreg: true,
      userName: '',
      password: '',
      name: this.getUserName(),
      showEmailError: false,
      showPasswordError: false,
      showLoginError: false,
      regUserName: null,
      regEmail: null,
      regPassword: null,
      regAddress: null,
      regPhone: null,
      regRepPassword: null,
      selectedCode: '070',
      selectedCountry: 'Azerbaijan'
    }
  },
  props: {
    showModal: Boolean,
    logModel: Boolean,
    user: Object
  },
  methods: {
    closeModal() {
      this.logreg = true
      this.$emit('close');
    },
    toReg() {
      this.logreg = false
    },
    toLogin() {
      this.logreg = true

    },
    ...mapActions(['incrementCount']),
    logout() {
      AuthService.logout()
      this.$emit('dataChanged', this.name)
      this.incrementCount()
      this.closeModal()
    },
    login() {
      if (this.userName === '' || this.password === '') return
      AuthService.login({
        username: this.userName,
        password: this.password
      }).then(value => {
        if(value==null) return
        this.name = value.data.user.username
        console.log(this.name)
        this.$emit('dataChanged', this.name)
        this.incrementCount()
        this.closeModal()
      })

    },
    getUserName() {
      let username = ''
      try {
        username = AuthService.getCurrentUser().user.username
      } catch (err) {
        username = ''
      }
      return username;
    },
    registration() {
      if (this.regEmail === null || this.regEmail === '') {
        Toasty.showError("Email is not empty!")
        this.showEmailError = true;
        return
      } else if (!this.emailValid(this.regEmail)) {
        this.showEmailError = true;
        Toasty.showError("Email is not valid!")
        return;
      }
      if (this.regUserName === '' || this.regUserName === null) {
        this.showLoginError = true;
        Toasty.showError('Login cannot be empty!')
        return;
      }
      if (this.regUserName.length < 3) {
        this.showLoginError = true
        Toasty.showError('Login must be at least 3 characters long!')
        return;
      }
      if (this.regPassword == null || this.regPassword != this.regRepPassword || this.regPassword.length < 6) {
        this.showPasswordError = true
        Toasty.showError('Password cannot be empty, shorter than 6 characters and Password must match Repeat password')
        return;
      }
      let uName = this.regUserName
      let email = this.regEmail
      let password = this.regPassword
      let address = ''
      let phone = ''
      if (this.regAddress != null && this.regAddress != '') {
        address = this.selectedCountry + ' ' + this.regAddress;
      }
      if (this.regPhone != null && this.regPhone != '') {
        phone = this.selectedCode + ' ' + this.regPhone
      }

      AuthService.registration({
        username: uName,
        password: password,
        email: email,
        address: address,
        phone: phone,
        photoUrl: ''

      }).then(value => {
            this.name = value.data.user.username
            console.log(this.name)
            this.$emit('dataChanged', this.name)
            this.closeModal()
          })


    },
    emailValid(email) {
      const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return regex.test(email)
    }
  }
}
</script>

<style>
/* Стили модального окна */
.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0%;
  top: 0%;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4) !important;
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 90%;
}

.close-mod {
  text-align: right;
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.err {
  border: red solid 1px;
}

.central {
  left: 20%;
  top: auto;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
