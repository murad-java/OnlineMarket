<template>

  <div class="modal" tabindex="-1" @click="closeModal" role="dialog" v-if="showOrders">
    <div class="modal-dialog " @click.stop style="max-width: 700px" role="document">
      <div class="modal-content min-vw-600">
        <div class="modal-header">
          <h5 class="modal-title">Orders</h5>
          <button type="button" class="close" @click="closeModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" style="max-height: 420px;overflow-x: hidden;overflow-y:auto">
          <div v-for="(product) in orders" :key="product.id">
            <div class=" p-2 mb-3 shadow-m rounded "
                 style="display: flex;background-color: rgb(251,255,255); border: 1px solid #000">
              <div><img class="rounded border" style="width:15em ;height: auto"
                        :src="concat('data:image/png;base64,',product.image)">
              </div>
              <div class="w-100 mw-300">
                <h6 class="text-center mt-1">{{ product.name }} </h6>
                <hr>
                <div class="row" style="justify-content: center">

                </div>
                <div class="justify-content-end align-self-end" style="max-height: 15px;height: 56%">
                  <div class="row justify-content-end bottom-0" style="height: auto; margin: -10px ">

                    <span class="price-wrap mr-1 align-self-end">prise:</span>
                    <span class="price mr-1 align-self-end">{{ product.price }}</span>
                    <i class="fa-solid fa-manat-sign fa-flip mr-3 align-self-end"
                       style="color: rgb(252, 137, 29); font-size: 2.5ex;"></i>
                    <span class="price-old mr-3 align-self-end">{{product.buyDateTime}}</span>
                  </div>
                </div>
                <hr>
                  <div class="ml-2 block" v-for="(file) in product.productFileEntity" :key="file.url" >
                    <div><a :href="file.fileUrl" target="_blank" class="col-3 ml-2 mr-2 price-wrap download" ><i class="fa-solid fa-download"></i> DOWNLOAD </a>
                      <span class="price-wrap mr-1 mr-1">size:</span>
                      <span class="price mr-1 mr-3">{{ file.size }} MByte</span>
                    </div>
                  </div>

              </div>
            </div>
          </div>
          <hr>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-info" @click="closeModal">
            Close
          </button>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import BuyService from "@/api/BuyService";

export default {
  data() {
    return {
      showModal: false,
      orders: [],
    };
  },
  props: {
    showOrders: Boolean
  },
  watch: {
    showOrders: function () {
      if (this.showOrders) {
        BuyService.getOrders().then(value => {
          this.updateOrders(value)
        })
      }
    }
  },
  methods: {


    updateOrders(value) {
      if (value != null) {
        this.orders = value.data
      }
    },
    closeModal() {
      this.$emit('close');
    },
    concat(var1, var2) {
      return var1 + var2;
    }

  },

};
</script>
<style>
.download{
  color: #436cbb
}

.total {
  font-size: 20px;
  font-weight: bold;
  text-align: right;
  margin-top: 20px;
}
</style>
