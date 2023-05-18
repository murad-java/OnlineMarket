<template>
  <header class="section-header">
    <section class="header-main border-bottom">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-xl-2 col-lg-3 col-md-12">
            <a href="#" class="brand-wrap">
              <img class="logo" src="../assets/GAMIT.png">
            </a>
          </div>
          <div class="col-xl-6 col-lg-5 col-md-6">
          </div>
          <div class="col-xl-4 col-lg-4 col-md-6">
            <div class="widgets-wrap float-md-right">
              <div class="widget-header mr-3">
                <a href="#" class="widget-view" @click="openModal">
                  <div class="icon-area">
                    <i class="fa fa-user"></i>
                    <!--                    <span class="notify">3</span>-->
                  </div>
                  <small class="text"> {{ uname }} </small>
                </a>
              </div>
              <div class="widget-header mr-3">
                <a href="#" class="widget-view">
                  <div class="icon-area">
                    <i class="fa-solid fa-wallet"></i>
                    <!--                    <span class="notify">1</span>-->
                  </div>
                  <small class="text"> Wallet </small>
                </a>
              </div>
              <div class="widget-header mr-3">
                <a href="#" class="widget-view">
                  <div class="icon-area">
                    <i class="fa fa-store"></i>
                  </div>
                  <small class="text"> Orders </small>
                </a>
              </div>
              <login-registration :show-modal="showModal" :user="userInfo" :log-model="logModel"
                                  @dataChanged="userNameChange" @close="closeModal"/>
              <cart-model :show-cart="showCart" @close="closeCart"/>
              <info-model :show-info="showInfo" @close="closeInfo"/>
              <div class="widget-header mr-2">
                <a href="#" class="widget-view" @click="OpenCart">
                  <div class="icon-area">
                    <i class="fa fa-shopping-cart"></i>
                    <span class="notify" v-if="cartCount||cartCount>0">{{ cartCount }}</span>
                  </div>
                  <small class="text"> Cart </small>
                </a>
              </div>
              <div class="widget-header mr-1">
                <a href="#" class="widget-view" @click="OpenInfo">
                  <div class="icon-area">
                    <i class="fa-solid fa-info-circle"></i>
                    <!--                    <span class="notify">1</span>-->
                  </div>
                  <small class="text"> Info </small>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </header>

</template>

<script>
import LoginRegistration from "@/components/LoginRegistration";
import authService from "@/api/AuthService";
import cartModal from "@/components/cart-modal";
import info from "@/components/infoModal";
import CartService from "@/api/CartService";
import { mapState } from 'vuex'
export default {
  name: 'HeaderVue',
  data() {
    return {
      showModal: false, // флаг, указывающий, нужно ли показывать модальное окно
      showCart: false,
      showInfo: false,
      uname: 'Log In',
      unameDefault: 'Log In',
      logModel: null,
      userInfo: null,
      cartCount: 0,
    }
  },
  components: {
    "login-registration": LoginRegistration,
    "cart-model": cartModal,
    "info-model": info
  },
  watch: {
    showCart: function () {
      if (!this.showCart) {
        this.getCartCount()
      }
    }
  },
  created() {
    this.$store.subscribeAction((action) => {
      if (action.type === 'incrementCount') {
        this.getCartCount()
      }
    })

  },
  computed: {
    ...mapState(['count'])
  },
  methods: {
    OpenCart() {
      this.showCart = true
    },
    OpenInfo() {
      this.showInfo = true
    },
    openModal() {
      this.showModal = true;
      let un = authService.getUserName()
      if (un === '') this.logModel = false
      else {
        this.logModel = true
        this.userInfo = authService.getCurrentUser().user
      }

    },
    closeModal() {
      this.showModal = false;
      this.selectedProduct = null;

    },
    closeCart() {
      this.showCart = false;
    },
    closeInfo(){
      this.showInfo=false
    },
    userNameChange() {
      try {
        this.uname = authService.getCurrentUser().user.username
      } catch (err) {
        this.uname = this.unameDefault
      }

    },
    getCartCount() {
      this.cartCount =0;
      let c = CartService.getCount()
      if (c != null) {
        c.then(value => {
          if (value != null) {
            this.cartCount = value
          }
        })

      }
    },
    update() {
      this.getCartCount()
    }
  },
  mounted() {
    let isTrue = authService.iAM();
    if (isTrue) {
      this.uname = authService.getUserName()
    }
    if (this.uname === '') {
      this.uname = this.unameDefault
    }
    this.getCartCount()
    // this.$store.dispatch('setMessage', this.getCartCount())
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

a {
  color: #2c2c2c;
}
</style>
