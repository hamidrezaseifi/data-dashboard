import { userService } from '../services/user.service';
import router from '../router'

const loginData = JSON.parse(sessionStorage.getItem('login-data'));

const initialState = loginData
    ? { status: { loggedIn: true }, loginData }
    : { status: {}, loginData: null };

export const authentication = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ dispatch, commit }, { username, password }) {

            commit('loginRequest', { username });

            userService.login(username, password)
                .then(
                    loginData => {
                        commit('loginSuccess', loginData);
                        router.push('/');
                    },
                    error => {
                        commit('loginFailure', error);
                        dispatch('alert/error', error, { root: true });
                    }
                );
        },
        logout({ commit }) {
            userService.logout();
            commit('logout');
            router.push('/login');
        }
    },
    mutations: {
        loginRequest(state, loginData) {
            state.status = { loggingIn: true };
            state.loginData = loginData;
            state.message = false;
        },
        loginSuccess(state, loginData) {
            state.status = { loggedIn: true };
            state.loginData = loginData;
            state.message = false;
        },
        loginFailure(state, error) {
            console.log('loginFailure', error);
            state.status = {loggedIn: false};
            state.loginData = null;
            state.message = error;
        },
        logout(state) {
            state.status = {loggedIn: false};
            state.loginData = null;
            state.message = false;
        }
    }
}