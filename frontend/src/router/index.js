import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import HotGame from '@/components/HotGame'

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    }, {
      path: '/hotGame',
      name: 'HotGame',
      component: HotGame
    }
  ]
})
