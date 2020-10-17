import React, {useEffect, useState} from 'react';
import {authenticationService} from "../../services/auth.service";
import './profile.css'

export const Profile = () => {

    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        authenticationService.currentUser.subscribe(x => setCurrentUser(x));
    })

    return (
        <div>
            {currentUser}
        </div>
    )
}