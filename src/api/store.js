import { createStore } from 'vuex'

const store = createStore({
    state() {
        return {
            count: 0
        }
    },
    mutations: {
        increment(state) {
            state.count++
        }
    },
    actions: {
        incrementCount({ commit }) {
            commit('increment')
        }
    }
})

export default store