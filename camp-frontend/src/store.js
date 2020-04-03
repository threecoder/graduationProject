import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        idType: null,
        userName: null,
        dynamic: {
            path: null,
            curPage: 1
        }
    },
    getters: {
        idType: state => state.idType,
        userName: state => state.userName,
        dynamic: state => state.dynamic,
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
            state.dynamic = {
                path: null,
                currentPage: 1
            }
        },
        setState: (state, info) => {
            state.idType = info.type;
            state.userName = info.name;
        },
        setDynamic: (state, dynamic) => {
            state.dynamic = dynamic;
        },
        removeState: (state) => {
            state.idType = null;
            state.userName = null;
        }
    },
    actions: {

    }
})
