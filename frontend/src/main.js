import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"

//new added
window.Vue = Vue;


Vue.use(BootstrapVue)

new Vue({
  el: '#app',
  render: h => h(App)
})
