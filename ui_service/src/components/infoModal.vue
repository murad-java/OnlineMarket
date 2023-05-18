<template>
  <section class="section-content bg-white padding-y modal  " id="popup-modal" v-if="showInfo" @click="closeModal">
    <div class="container modal-contents" @click.stop>
     <div class="modal-header align-items-center">
       <h5 class="modal-title">Info</h5>
       <div class="row align-items-center justify-content-center w-75  ml-5" >
         <div class="col-2"><button class="btn btn-block " :class="{ 'b': isContactActive }" @click="contactClick" style="font-size: 1.3ch">Contact</button> </div>
         <div class="col-2"><button class="btn btn-block " :class="{ 'b': isAboutActive }" @click="aboutClick" style="font-size: 1.3ch">About_us</button> </div>
         <div class="col-4"><button class="btn btn-block " :class="{ 'b': isTermsActive }" @click="termsClick" style="font-size: 1.3ch">Terms_of_use</button> </div>
         <div class="col-4"><button class="btn btn-block " :class="{ 'b': isPrivacyActive }" @click="privacyClick" style="font-size: 1.3ch">Privacy_policy</button> </div>
       </div>
        <button class="close" type="button" @click="closeModal">
         <span aria-hidden="true">&times;</span>
       </button>
      </div>
      <div class="form-row " >
        <div v-if="isContactActive" class="p-5  w-100 align-content-center justify-content-center" style="height: 400px">
          <div class="row align-content-start justify-content-start">
            <div class="col-1"><h6>Tel.:</h6></div>
            <div class="col-2">+994705116781</div>
          </div>
          <div class="row align-content-start justify-content-start">
            <div class="col-1"><h6>Email:</h6></div>
            <div class="col-2"><h7>safikhanov95@mail.ru</h7></div>
          </div>
        </div>
        <div v-if="isAboutActive" class="textarea-wrapper w-100 align-content-center">
          <textarea id="my-textarea" readonly rows="4" placeholder='gamit.az müxtəlif proqramlaşdırma dillərində proqramlaşdırma kurslarına
çıxışı təmin edən saytdır. Saytımızda proqramçılar tərəfindən hazırlanmış
kursların geniş seçimini tapa bilərsiniz.

Biz istifadəçilərimizə yüksək keyfiyyətli məzmun və xidmətlər təqdim
etməyə çalışırıq. Kurslarımızı veb saytımızda almaq olar, burada özünüz
üçün ən uyğun kursu seçib, münasib vaxt və məkanda təhsilə başlaya bilərsiniz.

Biz istifadəçilərimizin məxfiliyinə hörmət edirik və şəxsi məlumatlarınızla
işləyərkən yüksək təhlükəsizlik standartlarına riayət etməyimizi təmin edirik.
Hər hansı bir sualınız, istək və ya təklifiniz olarsa, zəhmət olmasa veb
saytımızdakı əlaqə forması vasitəsilə bizimlə əlaqə saxlayın.'></textarea>
        </div>
        <div v-if="isTermsActive" class="textarea-wrapper w-100 ">
          <textarea id="my-textarea" readonly  rows="4" placeholder='İstifadə şərtləri

Gamit.az saytına xoş gəlmisiniz.
Aşağıda xidmətlərimizə və məzmunumuza girişinizi tənzimləyən saytımızdan istifadə
şərtləri verilmişdir. Zəhmət olmasa bu istifadə şərtlərini diqqətlə oxuyun, çünki
onlar sizinlə bizim aramızda olan hüquqi müqavilədir.

Xidmətlərimizin təsviri
Gamit.az saytımızda əldə oluna bilən müxtəlif proqramlaşdırma dillərində proqramlaşdırma
kurslarına çıxış təmin edir. Biz istifadəçilərimizə keyfiyyətli məzmun və xidmətlər təqdim
edir, həmçinin texniki dəstək göstəririk.

Qeydiyyat və məlumat məxfiliyi
Kurslarımızı almaq üçün Gamit.az saytında qeydiyyatdan keçməlisiniz. Qeydiyyat
zamanı verilən məlumatların düzgünlüyünə və tamlığına görə məsuliyyət daşıyırsınız. Biz sizin
şəxsi məlumatlarınızın məxfiliyinə zəmanət veririk və qanunun tələb etdiyi hallar istisna
olmaqla, sizin açıq razılığınız olmadan onları üçüncü tərəflərlə paylaşmayacağıq.

İmtina
Saytımızdakı məzmunun dəqiq və müasir olmasını təmin etmək üçün hər cür səy göstərsək də,
onun tamlığına və etibarlılığına zəmanət vermirik. Saytımızın istifadəsi nəticəsində yaranan
hər hansı zərər və ya itkiyə, o cümlədən, lakin bununla məhdudlaşmayaraq, itirilmiş mənfəət,
məlumatla bağlı zərər və ya digər maddi itkilərə görə məsuliyyət daşımırıq.

İstifadə Şərtləri Dəyişiklikləri
Biz istənilən vaxt bu istifadə şərtlərini dəyişdirmək hüququnu özümüzdə saxlayırıq.
Dəyişikliklər vebsaytımızda yerləşdiriləcək və biz sizi vaxtaşırı nəzərdən keçirmək üçün bu
səhifəni yoxlamağı tövsiyə edirik.'></textarea>
        </div>
        <div v-if="isPrivacyActive" class="textarea-wrapper w-100 ">
          <textarea id="my-textarea" readonly  rows="4" placeholder='  Məxfilik Siyasəti

Sayta daxil olan hər bir istifadəçi aşağıda göstərilən qaydaları qəbul edir.
Bu qaydalar istifadəçilərə xəbərdarlıq edilmədən əlavə edilə, dəyişdirilə və ya silinə bilər.


Biz, müxtəlif proqramlaşdırma dillərinin proqramlaşdırılması üzrə dərslər (hissə-hissə kurslar)
satan "gamit.az" saytının sahibləri istifadəçilərimizin şəxsi məlumatlarının qorunmasına böyük
əhəmiyyət veririk. Biz istifadəçilərimizin məlumatlarının məxfiliyini və təhlükəsizliyini qorumağa
sadiqik və bunu təmin etmək üçün bütün ağlabatan addımları atırıq.


  Toplanmış Məlumat

Saytın administrasiyası İnternet-mağaza saytının İstifadəçisi tərəfindən verilən şəxsi
məlumatların düzgünlüyünü yoxlamır.
Biz yalnız xidmətlərimizi göstərmək və istifadəçilərimizə ən yüksək səviyyədə xidmət göstərmək
üçün lazım olan məlumatları toplayırıq. Biz istifadəçilərin açıq razılığı olmadan şəxsi məlumatları
toplamırıq və təqdim olunan hər hansı məlumat yalnız istifadəçilərin tələb etdiyi xidmətlərin
göstərilməsinin bir hissəsi kimi istifadə olunur.


Saytımızdan qeydiyyatdan keçmək və istifadə etmək prosesində biz aşağıdakı məlumatları toplaya bilərik:

Qeydiyyatdan keçmək və kurslarımıza girişi təmin etmək üçün istifadəçilərin adı, soyadı, e-poçt ünvanı
və əlaqə telefon nömrəsi;
Seçilmiş kurslar və satınalmalar haqqında məlumat;
Dəstək komandamızla əlaqə qurarkən istifadəçinin bizə verdiyi məlumat.


  Məlumatdan İstifadə

Biz toplanmış məlumatlardan yalnız proqramlaşdırma kurslarımızla bağlı xidmətlər göstərmək və saytımızı
və göstərilən xidmətlərin keyfiyyətini yaxşılaşdırmaq üçün istifadə edirik. Qanunla tələb olunan hallar
istisna olmaqla, istifadəçinin açıq razılığı olmadan şəxsi məlumatları üçüncü şəxslərə ötürmürük.


  Məlumat məxfiliyi

İstifadəçilərimizin şəxsi məlumatlarını qorumaq üçün bütün lazımi tədbirləri görürük. Biz şəxsi məlumatların
icazəsiz daxil olmasının, itirilməsinin, istifadəsinin və ya açıqlanmasının qarşısını almaq üçün müasir
texniki və təşkilati təhlükəsizlik tədbirlərindən istifadə edirik.
Sizin şəxsi məlumatlarınız təhlükəsiz serverlərdə saxlanılır və biz şəxsi məlumatların emalı ilə bağlı bütün
qüvvədə olan qanun və qaydalara əməl edirik.


  Bizimlə əlaqə saxlayın

Məxfilik siyasətimiz və ya şəxsi məlumatların emalı ilə bağlı hər hansı sualınız, təklifiniz və ya
şərhiniz varsa, vebsaytımızda göstərilən əlaqə məlumatları vasitəsilə bizimlə əlaqə saxlaya bilərsiniz.
Biz istifadəçilərimizin etibarını qiymətləndiririk və şəxsi məlumatlarınızla işləyərkən yüksək məxfilik
və təhlükəsizlik standartlarını qorumağa davam etməyimizi təmin edirik.'></textarea>
        </div>
<!--        <aside class="form-group col-md-8">-->
<!--          <div class="card product-details">-->
<!--            <article class="gallery-wrap central">-->
<!--              <div class="img-big-wrap">-->
<!--                <div>-->
<!--                  <a href="#">-->
<!--                    <img  v-if="!img" class="item-thumb img-fluid" style="height: auto; width: auto" :src='imgs'>-->
<!--                    <img  v-else class="item-thumb img-fluid" style="height: auto; width: auto" :src='img'>-->
<!--                  </a>-->
<!--                </div>-->
<!--              </div>-->
<!--              <div class="thumbs-wrap">-->
<!--                <a v-for="image in imagesObj" href="#" class="item-thumb " @click="loadImage(image)" :key="image"> <img-->
<!--                    style="height: auto; width: auto" :src='image.img'></a>-->
<!--              </div>-->
<!--            </article>-->
<!--          </div>-->
<!--        </aside>-->
<!--        <main class="form-group col-md-4">-->
<!--          <article class="product-info-aside">-->
<!--            <h2 class="title mt-3">{{ selectedProduct.name }}</h2>-->
<!--            <div class="mb-3">-->
<!--              <var class="price h4">Price: {{ selectedProduct.price }}</var>-->
<!--            </div>-->
<!--            <p>{{ selectedProduct.description }}</p>-->
<!--          </article>-->
<!--        </main>-->
<!--      </div>-->
<!--      <div class="modal-footer" @click.stop>-->
<!--        <button type="button" class="btn btn-info" @click="closeModal">-->
<!--          Close-->
<!--        </button>-->
<!--        <a href="#" class="btn  btn-primary" @click="addToCart()">-->
<!--          <i class="fas fa-shopping-cart"></i> <span class="text">Add to cart</span>-->
<!--        </a>-->
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
      isContactActive:true,
      isAboutActive:false,
      isTermsActive:false,
      isPrivacyActive:false,
    }
  },
  props: {
    showInfo: Boolean,
    selectedProduct: Object,
    imagesObj: Object,
    imgs: String
  },
  methods: {
    aboutClick(){
      this.deactivateAll()
      this.isAboutActive=true;
    },
    contactClick(){
      this.deactivateAll()
      this.isContactActive=true;
    },
    termsClick(){
      this.deactivateAll()
      this.isTermsActive = true;
    },
    privacyClick(){
      this.deactivateAll()
      this.isPrivacyActive = true
    },
    closeModal() {
      this.img = null
      this.$emit('close');
    },
    ...mapActions(['incrementCount']),
    loadImage(event) {
      this.img = event.img
    },
    deactivateAll(){
      this.isContactActive = false
      this.isAboutActive = false
      this.isTermsActive = false
      this.isPrivacyActive = false
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
  overflow: hidden;
  background-color: rgba(0, 0, 0, 0.4) !important;
}
.b {
  color: #ff6a00;
  border: none;
  box-shadow: none;
}
.b:hover {
  color: #ff6a00;
}
img {
  object-fit: contain;
}
.modal-contents {
  background-color: #ffffff;
  margin: 2% auto;
  padding: 20px;
  border: 1px solid #888;
  border-radius: 12px;
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


.textarea-wrapper {
  max-height: 400px;
  height: 400px;
  overflow-y: auto;
}

textarea {
  width: 100%;
  height: 95%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  resize: none;
  text-align: left;
  font-size: 1.6ch;
}
</style>