import axios from 'axios';
import { BehaviorSubject } from 'rxjs';

const API_URL = "http://localhost:8080/api/rooms/";


const rooms = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

const getUsers = (email, password) => {
    return axios.get(API_URL + "users")
        .then(response => {
            console.log(response);
        });
};




export const authenticationService = {
    getUsers,

    currentUser: currentUserSubject.asObservable(),
    get currentUserValue () { return currentUserSubject.value }
};