import axios from 'axios';
import { BehaviorSubject } from 'rxjs';
import { handleResponse } from "../_helpers/handle-response";

const API_URL = "http://localhost:3000/api/auth/";


const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));


const register = (username, email, password) => {
    return axios.post(API_URL + "signup", {
        username,
        email,
        password,
    })
        .then(response => {
            if (response.data.accessToken) {
                localStorage.setItem("currentUser", JSON.stringify(response.data));
                currentUserSubject.next(response.data);
            }

            return response.data;
        });
};

const login = (username, password) => {
    return axios
        .post(API_URL + "signin", {
            username,
            password,
        })
        .then(response => {
            if (response.data.accessToken) {
                localStorage.setItem("currentUser", JSON.stringify(response.data));
                currentUserSubject.next(response.data);
            }

            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem('currentUser');
    currentUserSubject.next(null);
};



export const authenticationService = {
    login,
    logout,
    register,
    currentUser: currentUserSubject.asObservable(),
    get currentUserValue () { return currentUserSubject.value }
};