import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        userInfo: {
            id: -1,
            email: '',
            name: '',
            age: '',
            sex: '',
            declaration: '',
            notice: '',
            timeCreate: '',
            timeModify: ''
        }
    },
    mutations: {
        updateUserInfo(state, newUserInfo) {
            state.userInfo = newUserInfo;
            Vue.$cookies.set('userInfo', newUserInfo)
        },
        logout(state) {
            state.userInfo.id = -1;
            Vue.$cookies.remove("userInfo");

            Vue.axios.post('/api/logout')
                .then(function (resp) {
                    if(resp.data.isSucceed) {
                        alert('登出成功！');
                    } else {
                        alert('登出失败！');
                    }
                })
                .catch(function (error) {
                    alert(error);
                })
        }
    },
    getters: {
        getUserInfo(state) {
            if (Vue.$cookies.isKey("userInfo")) {
                return Vue.$cookies.get('userInfo');
            } else {
                return state.userInfo;
            }
        }
    }
})