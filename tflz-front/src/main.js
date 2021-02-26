import Vue from 'vue'
import App from './App.vue'

// cookies plug
import VueCookies from 'vue-cookies';

Vue.use(VueCookies);

Vue.config.productionTip = false

// axios
import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);

// element-ui
import ElementUI from 'element-ui';
import './theme/ele_green/theme/index.css';

Vue.use(ElementUI);

//custom stylesheet
import './assets/css/global.css';

// vuex & vue-router
import store from './vuex';
import router from './router';

new Vue({
    render: h => h(App),
    store: store,
    router: router
}).$mount('#app')
