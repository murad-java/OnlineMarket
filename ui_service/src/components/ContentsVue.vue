<template>
  <section class="section-content padding-y">
    <div class="container">

      <div class="card mb-3 radius">
        <div class="card-body">

          <div class="row">
            <div class="col-md-2">Filter by</div> <!-- col.// -->
            <div class="col-md-10">
              <ul class="list-inline">
                <li class="list-inline-item mr-3 dropdown"><a href="#" class="dropdown-toggle"
                                                              data-toggle="dropdown"> Categories </a>
                  <div class="dropdown-menu p-3" style="max-width:400px;">
                    <label class="form-check">
                      <input type="radio" name="myFilter" class="form-check-input" @change="clearSubCategory"> All
                      categories
                    </label>
                    <label v-for="category in categories" class="form-check" @change="updateSubCategories"
                           :key="category">
                      <input :id=category.id type="radio" v-model="selectedCategory" name="myFilter"
                             class="form-check-input" :value="category">
                      {{ category.name }}
                    </label>

                  </div> <!-- dropdown-menu.// -->
                </li>
                <li v-if="subCategories!=null && subCategories.length>0" class="list-inline-item mr-3 dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Sub Categories </a>
                  <div class="dropdown-menu p-3" style="max-width:600px;width: 380px">
                    <label class="form-check">
                      <input type="radio" name="subFilter" class="form-check-input" @change="getAllSub" checked> All sub
                      categories
                    </label>
                    <label v-for="subCategory in subCategories" class="form-check" @change="updateContent"
                           :key="subCategory">
                      <input :id="subCategory.id" name="subFilter" type="radio" class="form-check-input"
                             v-model="selectedSubCategory" :value="subCategory">
                      {{ subCategory.name }}
                    </label>
                  </div>
                </li>

              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="spinner-border text-primary" style="position: fixed;top: 9%;left: 50%;" role="status" v-if="loading">
        <span class="sr-only">Loading...</span>
      </div>
      <div v-if="response" class="row">

        <div v-for='product in response' class="col-md-3 "  :key="product" @click="openModal(product)">
          <figure class="card card-product-grid radius"  >
            <div class="img-wrap "  >
              <span style="display: none" class="badge badge-danger"> NEW </span>
              <img style="width: 100%; height: auto;"  :src="product.img">
            </div> <!-- img-wrap.// -->
            <figcaption class="info-wrap" >
              <span href="#" class="title mb-2">{{ product.name }}</span>
              <div class="price-wrap">
                <i class="fa-solid fa-manat-sign fa-flip" style="color: #fc891d"></i>
                <span class="price">{{ product.price }}</span>
              </div>
              <p class="text-muted ">{{ product.description }}</p>
              <hr >
              <div @click.stop>
              <a href="#" class="btn btn-outline-primary"  @click=" addToCart(product)"> <i class="fa fa-cart-arrow-down" ></i> Add to
                cart
              </a>
              </div>
            </figcaption>
          </figure>
        </div>
      </div>
      <product-modal :show-modal="showModal" :selected-product="selectedProduct" :imgs="imgs" :images-obj="imagesObj" @close="closeModal" />
      <nav v-if="response!=null && response.length>20" class="mb-4" aria-label="Page navigation sample" >
        <ul class="pagination">
          <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
          <li class="page-item active"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item"><a class="page-link" href="#">4</a></li>
          <li class="page-item"><a class="page-link" href="#">5</a></li>
          <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
      </nav>
    </div>
  </section>
  <footer class=" bg-secondary footer mt-auto py-3">
    <div class="container">


      <section class="footer-bottom text-center">

        <p class="text-white">-------------------------------------------------------</p>
        <p class="text-muted"> &copy; {{year}} My Market, All rights reserved </p>
        <br>
      </section>
    </div><!-- //container -->
  </footer>
</template>

<script>
import instance from '../api/instance'
import productModal from "@/components/product-modal";
import ImageService from "@/api/ImageService";
import Toasty from "@/api/Toasty";
import CartService from "@/api/CartService";
import { mapActions } from 'vuex'

export default {
  name: 'ContentsVue',
  data() {
    return {

      loading: false,
      year:2020,
      response: null,
      selectedCategory: null,
      selectedSubCategory: null,
      categoryId: -1,
      subCategoryId: -1,
      categories: [],
      subCategories: [],
      showModal: false, // флаг, указывающий, нужно ли показывать модальное окно
      selectedProduct: null, // выбранный продукт, который будет отображаться в модальном окне
      imagesObj:[],
      imgs:null,
      cartCount:0

    }
  },
  components: {
    "product-modal": productModal
  },
  methods: {
    openModal(product) {
      this.showModal = true;
      this.selectedProduct = product;
      this.imgs =product.img;
      ImageService.getImagesByProductId(product.id).then(value =>{
        this.imagesObj = value.data
        this.imagesObj.forEach(v => {
          v.img = "data:image/png;base64,"+v.img
        })
      } ).catch(err=>{
        Toasty.showError(err.message)
      })

    },
    ...mapActions(['incrementCount']),
    addToCart(product){
      CartService.addToCart(product.id).then(() => {
        this.incrementCount();
      })

    },
    closeModal() {
      this.showModal = false;
      this.selectedProduct = null;
      this.imagesObj =null;
    },
    sendGetRequest: function () {
      this.updateContent();
    },
    updateSubCategories: function () {
      this.subCategoryId = -1;
      this.subCategories = []
      this.selectedSubCategory = null;
      this.categoryId = this.selectedCategory.id;
      if (this.selectedCategory) { // Проверяем, что выбрана категория
        instance.get('/subcategories/by-category/' + this.categoryId)
            .then(response => {
              this.subCategories = response.data;
            })
            .catch(error => {
              Toasty.showError(error.message)
            });
      }
      this.updateContent();
    },
    updateContent: function () {
      if (this.selectedSubCategory != null) this.subCategoryId = this.selectedSubCategory.id;
      if (this.subCategoryId != null && this.subCategoryId != -1)
        this.getContent("GET", "/product/subcategory/" + this.subCategoryId);
      else if (this.categoryId != null && this.categoryId != -1)
        this.getContent("GET", "/product/category/" + this.categoryId);
      else this.getContent("GET", "/product/list");
    },
    clearSubCategory: function () {
      this.subCategories = []
      this.subCategoryId = -1;
      this.categoryId = -1;
      this.updateContent();
    },
    getContent: function (type, url) {
      this.loading = true;
      instance.get(url)
          .then(response => {

            let res = response.data;
            this.loading = false;
            this.response = res;

            // Iterate through each product in the response and convert the Images array to base64
            this.response.forEach(function (product) {
              product.img = "data:image/png;base64," + product.img;
            });
          })
          .catch(error => {
            this.loading =false;
            Toasty.showError(error.message)
          });
    },
    getAllSub: function () {
      this.selectedSubCategory = null
      this.subCategoryId = -1;
      this.updateContent();
    }

  },
  mounted() {
    this.year=new Date().getFullYear()
    this.sendGetRequest();
    instance.get('/category/list')
        .then(response => {
          this.categories = response.data;
        })
        .catch(error => {
          Toasty.showError(error.message)
        });
  }
};
</script>
<style>
.custom-toastify-text {
  color: #212121;
}
.radius {
  border-radius: 10px;
}
img {
  object-fit: cover;
}

</style>