import React, {useEffect, useState} from 'react';
import {authenticationService} from "../../services/auth.service";
import './profile.css'

export const Profile = () => {

    const [currentUser, setCurrentUser] = useState(JSON.parse(localStorage.getItem("currentUser")));

    // useEffect(() => {
    //     authenticationService.currentUser.subscribe(x => setCurrentUser(x));
    // })

    console.log(currentUser)

    return (
        <div>
            <h1>Your profile info (You are logged in)</h1>
            <h2>id: {currentUser.id}</h2>
            <h2>email: {currentUser.email}</h2>
            <h2>accessToken: {currentUser.accessToken}</h2>
        </div>
    )
}