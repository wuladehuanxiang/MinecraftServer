import VueRouter from 'vue-router'
import Vue from 'vue'
import MainPage from './components/Main/MainPage.vue'
import Item from './components/ItemCompent/Item.vue'
import LoginPage1 from './components/Login/LoginPage1.vue'
import LoginCard1 from './components/Login/LoginCards/LoginAndRegist1.vue'
import LoginCard2 from './components/Login/LoginCards/LoginAndRegist2.vue'


Vue.use(VueRouter);


export default new VueRouter({
    // mode: 'history',
    routes: [{
            path: '/',
            component: LoginPage1,
            children: [],
        },
        {
            path: '/MainPage',
            component: MainPage,
            children: [

            ],
        },
    ]
})