import VueRouter from 'vue-router'
import Vue from 'vue'
import MainPage from './components/ComonComponents/Main/MainPage.vue'
import Item from './components/ComonComponents/ItemCompent/Item.vue'
import LoginPage1 from './components/ComonComponents/Headers/Login/LoginPage1.vue'
import LoginCard1 from './components/ComonComponents/Headers/Login/LoginCards/LoginAndRegist1.vue'
import LoginCard2 from './components/ComonComponents/Headers/Login/LoginCards/LoginAndRegist2.vue'
import Table from './components/ComonComponents/Table/Table.vue'


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