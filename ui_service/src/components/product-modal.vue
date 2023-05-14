<template>
  <section class="section-content bg-white padding-y modal  " id="popup-modal" v-if="showModal" @click="closeModal">
    <div class="container modal-content">
      <div class="modal-header">
        <button class="close" type="button" @click="closeModal">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="form-row " @click.stop>
        <aside class="form-group col-md-8">
          <div class="card product-details">
            <article class="gallery-wrap central">
              <div class="img-big-wrap">
                <div>
                  <a href="#">
                    <img  v-if="!img" class="item-thumb img-fluid" style="height: auto; width: auto" :src='imgs'>
                    <img  v-else class="item-thumb img-fluid" style="height: auto; width: auto" :src='img'>
                  </a>
                </div>
              </div>
              <div class="thumbs-wrap">
                <a v-for="image in imagesObj" href="#" class="item-thumb " @click="loadImage(image)" :key="image"> <img
                    style="height: auto; width: auto" :src='image.img'></a>
              </div>
            </article>
          </div>
        </aside>
        <main class="form-group col-md-4">
          <article class="product-info-aside">
            <h2 class="title mt-3">{{ selectedProduct.name }}</h2>
            <div class="mb-3">
              <var class="price h4">Price: {{ selectedProduct.price }}</var>
            </div>
            <p>{{ selectedProduct.description }}</p>
          </article>
        </main>
      </div>
      <div class="modal-footer" @click.stop>
        <button type="button" class="btn btn-info" @click="closeModal">
          Close
        </button>
        <a href="#" class="btn  btn-primary" @click="addToCart()">
          <i class="fas fa-shopping-cart"></i> <span class="text">Add to cart</span>
        </a>
      </div>
    </div>
  </section>
</template>
<script>
import CartService from "@/api/CartService";
import { mapActions } from 'vuex'
export default {
  data() {
    return {
      img: null
    }
  },
  props: {
    showModal: Boolean,
    selectedProduct: Object,
    imagesObj: Object,
    imgs: String
  },
  methods: {
    closeModal() {
      this.img = null
      this.$emit('close');
    },
    ...mapActions(['incrementCount']),
    loadImage(event) {
      this.img = event.img
    },
    addToCart(){
      CartService.addToCart(this.selectedProduct.id).then(()=>
      {
        this.incrementCount();
      })
    }
  }
}
</script>
<style>
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
img {
  object-fit: contain;
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