import React, {useEffect, useState} from 'react';

import { Link } from 'react-router-dom';

import './navbar.css'
import {authenticationService} from "../../services/auth.service";
import { useHistory } from "react-router-dom";

export const Navbar = () => {

    let history = useHistory();
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        authenticationService.currentUser.subscribe(x => setCurrentUser(x));
    });

    const logout = () => {
        authenticationService.logout();
        history.push('/login');
    }

    return (
        <div className='navbar'>
            <Link to='/' className='navbar__home'>Home</Link>

            { currentUser === null ?
                <div>
                    <Link to='/login' className='navbar__auth'>Login</Link>
                    <Link to='/register' className='navbar__auth'>Register</Link>
                </div> :
                <div>
                    <div className='navbar__auth' onClick={logout}>Logout</div>
                    <Link to='/profile' className='navbar__auth'>Profile</Link>
                </div>
            }
        </div>
    )
}