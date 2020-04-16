import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: { name: null, expire: 0 }
    },
    getters: {
        user: state => state.user
    },
    mutations: {
        init: (state) => {
            let user = window.localStorage.getItem("user");
            if (user) {
                user = JSON.parse(user);
                state.user = user;
            }
        },
        setUser: (state, obj) => {
            window.localStorage.setItem("user", JSON.stringify(obj));
            state.user = obj;
        },
        removeUser: (state) => {
            state.user = { name: null, expire: 0 };
            window.localStorage.removeItem("user");
        }
    },
    actions: {

    }
})
