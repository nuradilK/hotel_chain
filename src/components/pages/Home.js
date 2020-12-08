import React, {useEffect} from 'react'
import {authenticationService} from "../../services/auth.service";
import './home.css';

export const Home = () => {

    return (
        <div className='home'>
            <h1>Welcome to home hotel chain "Umbrella academy"</h1>
            <div>Please, go to the booking section to book the room</div>
        </div>
    )
}