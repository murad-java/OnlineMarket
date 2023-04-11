<template>
  <header class="section-header">
    <section class="header-main border-bottom">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-xl-2 col-lg-3 col-md-12">
            <a href="#" class="brand-wrap">
              <img class="logo" src="../assets/mymarket.png">
            </a>
          </div>
          <div class="col-xl-6 col-lg-5 col-md-6">
          </div>
          <div class="col-xl-4 col-lg-4 col-md-6">
            <div class="widgets-wrap float-md-right">
              <div class="widget-header mr-3">
                <a href="#" class="widget-view">
                  <div class="icon-area">
                    <i class="fa fa-user"></i>
                    <span class="notify">3</span>
                  </div>
                  <small class="text"> My profile </small>
                </a>
              </div>
              <div class="widget-header mr-3">
                <a href="#" class="widget-view">
                  <div class="icon-area">
                    <i class="fa fa-comment-dots"></i>
                    <span class="notify">1</span>
                  </div>
                  <small class="text"> Message </small>
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
              <div class="widget-header">
                <a href="#" class="widget-view">
                  <div class="icon-area">
                    <i class="fa fa-shopping-cart"></i>
                  </div>
                  <small class="text"> Cart </small>
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
export default {
  name: 'HeaderVue',

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

a {
  color: #2c2c2c;
}
</style>
