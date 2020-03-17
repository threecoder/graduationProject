import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        idType: null,
        userName: null
    },
    getters: {
        idType: state => state.idType,
        userName: state => state.userName,
        isLogin: state => {
            if (state.idType === null || state.userName === null) {
                return false;
            }
            return true;
        }
    },
    mutations: {
        initState: (state) => {
            let user = window.localStorage.getItem("user");
            if (user) {
                user = JSON.parse(user);
                state.idType = user.type;
                state.userName = user.name;
            }
        },
        setState: (state, info) => {
            state.idType = info.type;
            state.userName = info.name;
        },
        removeState: (state) => {
            state.idType = null;
            state.userName = null;
        }
    },
    actions: {

    }
})
