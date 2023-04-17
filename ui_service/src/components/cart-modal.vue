<!--<template>-->
<!--  <div id="app" class=" modal" v-if="showCart">-->
<!--    <section class="section-content padding-y">-->
<!--      <div class="card mx-auto"-->
<!--           style="max-width:800px;border-radius: 10px; background-color: #ffffff;  margin-top:80px;">-->
<!--        <article class="card-body" @click.stop><span class="close-mod" @click="closeModal">&times;</span>-->
<!--          <transition name="fade">-->
<!--            <modal><h3 class=" mb-4">Список продуктов</h3>-->
<!--              <div class="modal-body border bg-light">-->
<!--                <ul class="">-->
<!--                  <div class="form-row w-auto align-items-center" v-for="product in cart" :key="product.id">-->
<!--                    <div class="form-group col-md-7">-->
<!--                      <li class="list-group-item d-flex justify-content-between align-items-center">{{-->
<!--                          product.name-->
<!--                        }}-->
<!--                      </li>-->
<!--                    </div>-->
<!--                    <div class="form-group col-md-3">-->
<!--                      <li class="list-group-item d-flex justify-content-between align-items-center">-->
<!--                        {{ product.price }} azn.-->
<!--                      </li>-->
<!--                    </div>-->
<!--                    <div class="form-group col-md-2">-->
<!--                      <button @click="deleteProduct(product.id)" class="btn btn-warning "><i-->
<!--                          class="fa-solid fa-trash-can"></i></button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </ul>-->

<!--              </div>-->
<!--              <div v-if="cart.length > 0" class="modal-footer mt-6 d-flex justify-content-between">-->
<!--                <div class="total">Итого: {{ totalPrice }} azn.</div>-->
<!--                <button @click="showBuyButton = true" class="btn btn-primary btn-buy">Купить</button>-->
<!--              </div>-->
<!--              <div class="modal-footer">-->
<!--                <button @click="showModal = false" class="btn btn-info btn-close">Закрыть</button>-->
<!--              </div>-->
<!--            </modal>-->
<!--          </transition>-->
<!--        </article>-->
<!--      </div>-->
<!--    </section>-->
<!--  </div>-->
<!--</template>-->
<!--<script>-->
<!--export default {-->
<!--  props: {-->
<!--    showCart: Boolean-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      showModal: false,-->
<!--      totalPrice: 0,-->
<!--      showBuyButton: false,-->
<!--      cart: [-->
<!--        {id: 1, name: "Молоко", price: 50},-->
<!--        {id: 2, name: "Хлеб", price: 30},-->
<!--        {id: 3, name: "Сыр", price: 200},-->
<!--        {id: 4, name: "Яйца", price: 40},-->
<!--        {id: 5, name: "Колбаса", price: 150},-->
<!--        {id: 6, name: "Сок", price: 60},-->
<!--      ],-->
<!--    };-->
<!--  },-->
<!--  watch: {-->
<!--    cart: {-->
<!--      handler() {-->
<!--        this.totalPrice = this.computedTotalPrice;-->
<!--      },-->
<!--      deep: true-->
<!--    }-->
<!--  },-->
<!--  computed: {-->
<!--    computedTotalPrice() {-->
<!--      return this.cart.reduce((sum, product) => sum + product.price, 0);-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    deleteProduct(id) {-->
<!--      this.cart = this.cart.filter((product) => product.id !== id);-->
<!--    },-->
<!--  },-->
<!--  mounted() {-->
<!--    this.totalPrice = this.computedTotalPrice;-->
<!--  }-->
<!--};-->
<!--</script>-->
<!--<style>-->
<!--.fade-enter-active,-->
<!--.fade-leave-active {-->
<!--  transition: opacity 0.5s;-->
<!--}-->

<!--.fade-enter,-->
<!--.fade-leave-to {-->
<!--  opacity: 0;-->
<!--}-->
<!--.btn-buy, .btn-close {-->
<!--  width: 100px;-->
<!--  height: 40px;-->
<!--  display: inline-flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  margin-right: 10px;-->
<!--}-->
<!--.btn-primary {-->
<!--  background-color: #4CAF50;-->
<!--  border-color: #4CAF50;-->
<!--}-->

<!--.btn-primary:hover {-->
<!--  background-color: #3e8e41;-->
<!--  border-color: #3e8e41;-->
<!--}-->
<!--.total {-->
<!--  font-size: 20px;-->
<!--  font-weight: bold;-->
<!--  text-align: right;-->
<!--  margin-top: 20px;-->
<!--}-->
<!--.modal {-->
<!--  display: block;-->
<!--  position: fixed;-->
<!--  z-index: 1;-->
<!--  left: 0%;-->
<!--  top: 0%;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  overflow: auto;-->
<!--  background-color: rgba(0, 0, 0, 0.4) !important;-->
<!--}-->

<!--.modal-body {-->
<!--  overflow-y: auto;-->
<!--  max-height: 200px;-->
<!--}-->
<!--</style>-->

<!--<template>-->
<!--  <div id="app" class="modal" v-if="showCart">-->
<!--    <section class="section-content padding-y">-->
<!--      <div class="card mx-auto" style="max-width:900px;border-radius: 10px; background-color: #fdf7ef;  margin-top:80px;">-->
<!--        <article class="card-body" @click.stop>-->
<!--          <span class="close-mod" @click="closeModal">&times;</span>-->
<!--          <transition name="fade">-->
<!--            <modal>-->
<!--              <h3 class="mb-4">Список продуктов</h3>-->
<!--              <div class="modal-body border bg-light w-auto">-->
<!--                <ul class="list-group " style="max-height:320px; overflow-y: auto; overflow-x: hidden">-->
<!--                  <div class="form-row w-auto align-items-center" v-for="(product) in cart" :key="product.id">-->
<!--                    <div class="form-group col-md-7">-->
<!--                      <li class="list-group-item d-flex justify-content-between align-items-center">{{ product.name }}</li>-->
<!--                    </div>-->
<!--                    <div class="form-group col-md-3">-->
<!--                      <li class="list-group-item d-flex justify-content-between align-items-center">{{ product.price }} azn.</li>-->
<!--                    </div>-->
<!--                    <div class="form-group col-md-2">-->
<!--                      <button @click="deleteProduct(product.id)" class="btn btn-warning"><i class="fa-solid fa-trash-can"></i></button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </ul>-->
<!--                <div class="text-right mt-3"><strong>Итого: {{ getTotalPrice }} azn.</strong></div>-->
<!--              </div>-->
<!--              <div class="modal-footer">-->
<!--                <button class="btn btn-secondary" @click="closeModal">Закрыть</button>-->
<!--                <button class="btn btn-primary" :disabled="cart.length === 0">Купить</button>-->
<!--              </div>-->
<!--            </modal>-->
<!--          </transition>-->
<!--        </article>-->
<!--      </div>-->
<!--    </section>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  props: {-->
<!--    showCart: Boolean-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      showModal: true,-->
<!--      cart: [-->
<!--        {id: 1, name: "Молоко", price: 50},-->
<!--        {id: 2, name: "Хлеб", price: 30},-->
<!--        {id: 3, name: "Сыр", price: 200},-->
<!--        {id: 4, name: "Яйца", price: 40},-->
<!--        {id: 5, name: "Колбаса", price: 150},-->
<!--        {id: 6, name: "Сок", price: 60},-->
<!--      ],-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    deleteProduct(id) {-->
<!--      this.cart = this.cart.filter((product) => product.id !== id);-->
<!--    },-->
<!--    closeModal() {-->
<!--      this.$emit('update:showCart', false);-->
<!--    }-->
<!--  },-->
<!--  computed: {-->
<!--    getTotalPrice() {-->
<!--      return this.cart.reduce((total, product) => total + product.price, 0);-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--.fade-enter-active,-->
<!--.fade-leave-active {-->
<!--  transition: opacity 0.5s;-->
<!--}-->

<!--.fade-enter,-->
<!--.fade-leave-to {-->
<!--  opacity: 0;-->
<!--}-->

<!--.modal {-->
<!--  display: block;-->
<!--  position: fixed;-->
<!--  z-index: 1;-->
<!--  left: 0%;-->
<!--  top: 0%-->
<!--}-->
<!--</style>-->

<template>

  <div class="modal" tabindex="-1" role="dialog" v-if="showCart">
    <div class="modal-dialog " style="max-width: 700px" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Cart</h5>
          <button type="button" class="close" @click="closeModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" style="max-height: 420px;overflow-x: hidden;overflow-y:auto">
          <table class="table">
            <thead>
            <tr>
              <th>Name</th>
              <th>Price</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(product, index) in cart" :key="product.id">
              <td>
                <li class="list-group-item d-flex justify-content-between align-items-center">{{ product.name }}</li>
              </td>
              <td>
                <li class="list-group-item d-flex justify-content-between align-items-center">{{ product.price }} azn.
                </li>
              </td>
              <td class="align-content-center">
                <button type="button" class="btn btn-danger" @click="deleteProduct(index)">
                  <i class="fa-solid fa-trash-can"></i>
                </button>
              </td>
            </tr>
            </tbody>
          </table>
          <hr>
        </div>
        <div class="text-right mt-3 total">
          <strong>Итого: {{ getTotalPrice() }} azn.</strong>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-info" @click="closeModal">
            Close
          </button>
          <button type="button" class="btn btn-primary" @click="buyProducts()">
            Buy
          </button>
<!--            <div class="modal-body">-->
<!--              <div class="row">-->
<!--                <div class="col-md-6"></div>-->
<!--                <div class="col-md-3">-->
<!--                  <button type="submit" class="btn btn-info btn-block " @click="closeModal"> Close</button>-->
<!--                </div>-->
<!--                <div class="col-md-3">-->
<!--                  <button type="submit" class="btn btn-primary btn-block " @click="logout">Buy</button>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      showModal: false,
      cart: [
        {id: 1, name: 'Молоко', price: 50},
        {id: 2, name: 'Хлеб', price: 30},
        {id: 3, name: 'Сыр', price: 200},
        {id: 4, name: 'Яйца', price: 40},
        {id: 5, name: 'Колбаса', price: 150},
        {id: 6, name: 'Сок', price: 60},
      ],
    };
  },
  props: {
    showCart: Boolean
  },
  methods: {
    deleteProduct(index) {
      this.cart.splice(index, 1);
    },
    getTotalPrice() {
      return this.cart.reduce((total, product) => total + product.price, 0);
    },
    closeModal() {
      this.$emit('close');
    },
    buyProducts() {
      // Действия по совершению покупки
      this.cart = [];
      this.showModal = false;
    },
  },
};
</script>
<style>
.total {
  font-size: 20px;
  font-weight: bold;
  text-align: right;
  margin-top: 20px;
}
</style>