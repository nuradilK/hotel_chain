import axios from 'axios';
import { BehaviorSubject } from 'rxjs';

const API_URL = "http://localhost:8080/api/auth/";


const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

const getUsers = (email, password) => {
    return axios.get(API_URL + "users")
        .then(response => {
            console.log(response);
        });
};


const register = (email, password) => {
    return axios.post(API_URL + "signup", {
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

const login = (email, password) => {
    return axios
        .post(API_URL + "signin", {
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

const logout = () => {
    localStorage.removeItem('currentUser');
    currentUserSubject.next(null);
};



export const authenticationService = {
    getUsers,
    login,
    logout,
    register,
    currentUser: currentUserSubject.asObservable(),
    get currentUserValue () { return currentUserSubject.value }
};