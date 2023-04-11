<template>

  <section class="section-content bg-white padding-y modal  " id="popup-modal" v-if="showModal" @click="closeModal">
    <div class="container">

      <!-- ============================ ITEM DETAIL ======================== -->
      <div class="row modal-content" @click.stop>
        <span class="close-mod" @click="closeModal">&times;</span>
        <aside class="col-md-6">

          <div class="card product-details">

            <article class="gallery-wrap central">
              <div class="img-big-wrap">
                <div>
                  <a href="#">
                    <img v-if="!img" :src='imgs'>
                    <img v-else :src='img'>
                  </a>
                </div>
              </div> <!-- slider-product.// -->
              <div class="thumbs-wrap">
                <a v-for="image in imagesObj" href="#" class="item-thumb" @click="loadImage(image)" :key="image"> <img
                    :src='image.img'></a>

              </div> <!-- slider-nav.// -->
            </article> <!-- gallery-wrap .end// -->
          </div> <!-- card.// -->
        </aside>
        <main class="col-md-6">
          <article class="product-info-aside">

            <h2 class="title mt-3">{{ selectedProduct.name }}</h2>


            <div class="mb-3">
              <var class="price h4">Price: {{ selectedProduct.price }}</var>
            </div> <!-- price-detail-wrap .// -->

            <p>{{ selectedProduct.description }}</p>


            <!-- col.// -->
            <div class="form-group col-md">
              <a href="#" class="btn  btn-primary">
                <i class="fas fa-shopping-cart"></i> <span class="text">Add to cart</span>
              </a>

            </div> <!-- col.// -->
            <!-- row.// -->

          </article> <!-- product-info-aside .// -->
        </main> <!-- col.// -->
      </div> <!-- row.// -->

    </div> <!-- container .//  -->
  </section>


  <!--  <div class="modal" v-if="showModal">-->
  <!--    <div class="modal-content">-->
  <!--      <span class="close" @click="closeModal">&times;</span>-->
  <!--      <div class="product-details">-->
  <!--        <h2>{{ selectedProduct.name }}</h2>-->
  <!--        <p>{{ selectedProduct.description }}</p>-->
  <!--        <p>Price: {{ selectedProduct.price }}</p>-->
  <!--        &lt;!&ndash; Другие свойства продукта &ndash;&gt;-->
  <!--      </div>-->
  <!--    </div>-->
  <!--  </div>-->
</template>

<script>
// import ImageService from "@/api/ImageService";

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
    imgs: Object
  },
  methods: {
    closeModal() {
      this.img=null
      this.$emit('close');
    },
    loadImage(event) {
      this.img = event.img
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