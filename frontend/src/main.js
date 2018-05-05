import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"
import i18n from "./scripts/utils/i18n-support.js"

//new added
window.Vue = Vue;


Vue.use(BootstrapVue)

new Vue({
  el: '#app',
  render: h => h(App),
  //router,
  //i18n: i18n,
  i18n,
  template: '<App/>',
  components: { App }


})


/*
new Vue({
    el: '#app',
    router,
    i18n: i18n,   //实例中 引用国际化
    template: '<App/>',
    components: { App },
});
*/

/*
//1s后更改语言
setTimeout(function(){
    i18n.locale = "en"
},1000)
*/