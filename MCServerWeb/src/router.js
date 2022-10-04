import VueRouter from 'vue-router'
import Vue from 'vue'
import MainPage from './components/ComonComponents/Main/MainPage.vue'
import Home from "./components/ComonComponents/Content/Home.vue";
import Atlas from "./components/ComonComponents/Content/Atlas.vue";
import Donate from "./components/ComonComponents/Content/Donate.vue";
import Forum from "./components/ComonComponents/Content/Forum.vue";
import Rules from "./components/ComonComponents/Content/Rules.vue";
import Server from "./components/ComonComponents/Content/Server.vue";
import LoginPage1 from "./components/ComonComponents/Headers/Login/LoginPage1.vue"


Vue.use(VueRouter);


export default new VueRouter({
    // mode: 'history',
    routes: [{
            path: '/',
            component: MainPage,
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