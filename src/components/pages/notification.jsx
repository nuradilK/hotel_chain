import React, {useEffect, useState} from 'react';
import {Button} from "@material-ui/core";
import axios from "axios";
import {authenticationService} from "../../services/auth.service";
import { useHistory } from 'react-router-dom';
import './notification.css';

export const Notification = () => {
    const history = useHistory();
    const [currentUser, setCurrentUser] = useState(authenticationService.currentUserValue);
    if ((currentUser && !currentUser.notification) || currentUser === null){
        return null;
    }
    const [hotels, setHotels] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:8080/api/hotels/all/hotels")
            .then(res => {
                console.log(res.data);
                setHotels(res.data);
            });
    }, []);


    const close = () => {
        axios.post("http://localhost:8080/api/notification", {
            userId: currentUser.id,
        }).then(()=>{
            const updatedUser = {
                ...currentUser
            }
            updatedUser.notification = false;
            localStorage.setItem("currentUser", JSON.stringify(updatedUser));
            history.go(0);
            alert('Notification closed!');
        })
        history.push('/profile')
    }
    return (
        <div className='notification'>
            <Button  variant="contained"
                     color="primary"
                     className='employees-list-element_btn'
                     onClick={close}
            >
                Ok, close
            </Button>
            <div className='employees-list_header'>Check the seasons' course</div>
            {
                hotels.map((hotel) => {
                    return hotel.seasons.map(season => {
                        return(
                            <div className='employees-list-element'>
                                <div className='employees-list-element_part'>Hotel: {hotel.name}</div>
                                <div className='employees-list-element_part'>Season name: {season.name}</div>
                                <div className='employees-list-element_part'>Start date: {season.startDate}</div>
                                <div className='employees-list-element_part'>End date: {season.endDate}</div>
                                <div className='employees-list-element_part'>Coefficient: {season.coefficient}</div>
                            </div>
                        )
                    })
                })
            }
        </div>
    )
}