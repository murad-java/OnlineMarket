

<template>

  <div class="modal" tabindex="-1" @click="closeModal" role="dialog" v-if="showCart">
    <div class="modal-dialog " @click.stop style="max-width: 700px" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Cart</h5>
          <button type="button" class="close" @click="closeModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" style="max-height: 420px;overflow-x: hidden;overflow-y:auto">
          <table class="table">
            <thead >
            <tr >
              <th style="text-align: center">Name</th>
              <th style="text-align: center">Price</th>
              <th style="text-align: center">Delete</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(product) in cart" :key="product.id">
              <td>
                <li class="list-group-item d-flex justify-content-between align-items-center">{{ product.name }}</li>
              </td>
              <td>
                <li class="list-group-item d-flex justify-content-between align-items-center">{{ product.price }} azn.
                </li>
              </td>
<!--              <td >-->
<!--                <div class="row">-->
<!--                <button type="button" class="btn col-auto" @click="downCount(product)"><i class="fa-solid fa-angle-left" style="color: #ff6a00;"></i></button>-->
<!--                <li class="list-group-item d-flex justify-content-between align-items-center rounded-3">{{ product.count }}</li>-->
<!--                  <button type="button" class="btn  col-1"  @click="upCount(product)"><i class="fa-solid fa-angle-right" style="color: #ff6a00;"></i></button>-->
<!--                </div>-->
<!--              </td>-->
              <td class="text-center">
                <button type="button" class="btn btn-danger" @click="deleteProduct(product)">
                  <i class="fa-solid fa-trash-can"></i>
                </button>
              </td>
            </tr>
            </tbody>
          </table>
          <hr>
        </div>
        <div class="text-right mt-3 total">
          <strong>Итого: {{ total }} azn.</strong>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-info" @click="closeModal">
            Close
          </button>
          <button type="button" class="btn btn-primary" @click="buyProducts()">
            Buy
          </button>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import CartService from "@/api/CartService";
import { mapActions } from 'vuex'
import BuyService from "@/api/BuyService";
import Toasty from "@/api/Toasty";
export default {
  data() {
    return {
      showModal: false,
      total:0.0,
      cart: [],
    };
  },
  props: {
    showCart: Boolean
  },
  watch:{
    showCart: function() {
      if (this.showCart) {
        CartService.getCart().then(value => {
          this.updateCart(value)
        })
      }
    }
  },
  methods: {
    deleteProduct(product) {
      CartService.delete(product.id).then(value => {
        this.updateCart(value)
        this.incrementCount()
      })
    },
    ...mapActions(['incrementCount']),
    upCount(product){
      CartService.upCount(product.id).then(value => {
        this.updateCart(value)
      })
    },
    downCount(product){
      CartService.downCount(product.id).then(value => {
        this.updateCart(value)
      })
    },
    updateCart(value){
      if(value!=null) {
        this.total = value.totalPrice
        this.cart = value.products
      }
    },
    closeModal() {
      this.$emit('close');
    },
    buyProducts() {
      // Действия по совершению покупки
      BuyService.buyFromCart().then(response => {
        if(response!=null)
        {
          if(response.data.error){
            Toasty.showError(response.data.message)
          }else {
            window.open(response.data.url, '_blank');
            this.$emit('close');
          }
        }
      })
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
