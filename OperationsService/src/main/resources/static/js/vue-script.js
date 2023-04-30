import Popup from "../popup/popup";
let app = new Vue({
    el: '#vueApp',
    data: {
        loading: false,
        response: null,
        selectedCategory: null,
        selectedSubCategory: null,
        categoryId: -1,
        subCategoryId: -1,
        categories: [],
        subCategories: []
    },
    component: {
        Popup
    },
    created: function () {
        this.sendGetRequest();
    },
    methods: {

        sendGetRequest: function () {


            this.updateContent();

        },
        updateSubCategories: function () {
            this.subCategoryId=-1;
            this.subCategories = []
            this.selectedSubCategory=null;
            this.categoryId = this.selectedCategory.id;
            if (this.selectedCategory) { // Проверяем, что выбрана категория
                axios.get('/subcategories/by-category/' + this.categoryId)
                    .then(response => {
                        this.subCategories = response.data;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
            this.updateContent();
        },
        updateContent: function () {
            if(this.selectedSubCategory!=null) this.subCategoryId=this.selectedSubCategory.id;
            if(this.subCategoryId!=null && this.subCategoryId!=-1)
                this.getContent("get", "/product/subcategory/"+this.subCategoryId);
            else if (this.categoryId != null && this.categoryId != -1)
                this.getContent("get", "/product/category/" + this.categoryId);
            else this.getContent("get", "/product/list");
        },
        clearSubCategory: function () {
            this.subCategories = []
            this.subCategoryId = -1;
            this.categoryId = -1;
            this.loading = true;
            this.updateContent();

            // this.getContent("get", "/product/list");

        },
        getContent: function (type, url) {
            this.loading = true;
            let xhr = new XMLHttpRequest();
            xhr.open(type.toUpperCase(), url, true);
            xhr.setRequestHeader('Content-Type', 'application/json');

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    let response = JSON.parse(xhr.responseText);
                    app.loading = false;
                    app.response = response;

                    // Iterate through each product in the response and convert the Images array to base64
                    app.response.forEach(function (product) {
                        product.img = "data:image/png;base64," + product.img;
                    });
                }
            };
            xhr.send();
        },
        getAllSub: function (){
            this.selectedSubCategory=null
            this.subCategoryId = -1;
            this.updateContent();
        },
        showProductDetails: function (){
            axios.get('page-detail-product.html').then(function (response) {
                $('#popup-container').html(response.data);

                $('#popup-container').modal('show');
            });
        }

    },
    mounted() {
        axios.get('/category/list')
            .then(response => {
                this.categories = response.data;
            })
            .catch(error => {
                console.log(error);
            });
    }
});