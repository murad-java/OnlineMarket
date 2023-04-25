
import {createApp} from 'vue'
import App from './App.vue'
import HeaderVue from './components/HeaderVue.vue'
import ContentsVue from './components/ContentsVue.vue'
import store from "@/api/store";


const app = createApp(App)
app.component('header-vue', HeaderVue)
app.component('contents-vue', ContentsVue)
app.use(store)

app.mount('#app')
