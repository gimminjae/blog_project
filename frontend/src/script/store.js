import {createStore} from 'vuex'

// Create a new store instance.
const store = createStore({
    state() {
        return {
            user: {
                id: 0,
                //회원 정보는 필요한 최소한의 정보만 가지고 있는 것이 좋다
            }
        }
    }
    ,
    mutations: {
        setUser(state, payload) {
            state.user.id = payload.memberIdx;
        }
        ,
        removeUser(state) {
            state.user.id = 0;
        }
    }
})

export default store;
