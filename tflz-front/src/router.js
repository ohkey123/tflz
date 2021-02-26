import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default new VueRouter({
    routes: [
        {
            name: 'home',
            path: '/',
            component: resolve => require(['@/view/Home'], resolve)
        },
        {
            name: 'register',
            path: '/register',
            component: resolve => require(['@/view/Register'], resolve)
        },
        {
            name: 'login',
            path: '/login',
            component: resolve => require(['@/view/Login'], resolve)
        },
        {
            name: 'community',
            path: '/community',
            component: resolve => require(['@/view/Community'], resolve)
        },
        {
            name: 'createPropose',
            path: '/createPropose',
            component: resolve => require(['@/view/CreatePropose'], resolve)
        },
        {
            name: 'propose',
            path: '/propose',
            component: resolve => require(['@/view/Propose'], resolve)
        },
        {
            name: 'personal',
            path: '/personal',
            component: resolve => require(['@/view/Personal'], resolve)
        },

        // vision
        {
            name: 'visionOne',
            path: '/visionOne',
            component: resolve => require(['@/view/VisionOne'], resolve)
        },
        // {
        //     name: 'visionTwo',
        //     path: '/visionTwo',
        //     component: resolve => require(['@/view/VisionTwo'], resolve)
        // },

        // game
        {
            name: 'linkup',
            path: '/linkup',
            component: resolve => require(['@/view/Linkup'], resolve)
        },
        {
            name: 'game',
            path: '/game',
            component: resolve => require(['@/view/game'], resolve)
        }
    ],
    linkActiveClass: 'router-link-active'
});