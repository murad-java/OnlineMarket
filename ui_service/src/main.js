
import {createApp} from 'vue'
import App from './App.vue'
import HeaderVue from './components/HeaderVue.vue'
import ContentsVue from './components/ContentsVue.vue'


const app = createApp(App)
app.component('header-vue', HeaderVue)
app.component('contents-vue', ContentsVue)


app.mount('#app')
