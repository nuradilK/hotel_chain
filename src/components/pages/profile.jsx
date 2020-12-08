import React, {useEffect, useState} from 'react';
import './profile.css'
import axios from "axios";
import {Button} from "@material-ui/core";

const API_URL2 = "http://localhost:8080/api/book/";
const API_URL_GET_BOOK = "http://localhost:8080/api/book/";


export const Profile = () => {

    const today = new Date();
    const todayString = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();

    const [currentUser, setCurrentUser] = useState(JSON.parse(localStorage.getItem("currentUser")));
    const [bookings, setBookings] = useState([]);
    // useEffect(() => {
    //     authenticationService.currentUser.subscribe(x => setCurrentUser(x));
    // })

    useEffect(() => {
        axios.get(API_URL2).then((res) => {
            console.log(res.data);
            setBookings(res.data);
        })
    }, []);

    const deleteBooking = (bookingId) => {
        axios.post(API_URL_GET_BOOK+'delete', {
            bookID: bookingId,
        }).then(()=>{
            const newBookings = bookings.filter((booking) => {
                return parseInt(booking.id) !== parseInt(bookingId)
            });
            setBookings(newBookings);
            alert('you successfully deleted the booking');
        })
    };


    return (
        <div className='profile-container'>
            <h1 className='profile-container_header'>Your profile info (You are logged in)</h1>
            {/*<h3 className='profile-container_info'>id: {currentUser.id}</h3>*/}
            <h3 className='profile-container_info'>email: {currentUser.email}</h3>
            <h3 className='profile-container_info'>role: {currentUser.role.name}</h3>
            {/*<h3 className='profile-container_info'>accessToken: {currentUser.accessToken}</h3>*/}
            {
                currentUser.role.name === 'User' &&
                <div className='booking-list'>
                    <h1 className='booking-list_header'>Your bookings</h1>
                    {bookings.map((booking) => {
                        if (booking.user.id === currentUser.id){
                            return <div className='booking-list_element'>
                                <div className='booking-list_element_info'>id: {booking.id} </div>
                                <div className='booking-list_element_info'>from: {booking.fromDate} </div>
                                <div className='booking-list_element_info'>to: {booking.toDate} </div>
                                <div className='booking-list_element_info'>Hotel name: {booking.room.roomType.hotel.name}</div>
                                <div className='booking-list_element_info'>Hotel address: {booking.room.roomType.hotel.address}</div>
                                <div className='booking-list_element_info'>Bill: {booking.bill}</div>
                                <div>
                                    Services: {
                                        booking.services && booking.services.map((service)=>{
                                            return `${service.name} `
                                        })}
                                </div>
                                {
                                    todayString <= booking.fromDate ?
                                    <Button  variant="contained"
                                             color="primary"
                                             className='booking-list_element_btn'
                                             onClick={() => deleteBooking(booking.id)}
                                    >
                                        Cancel
                                    </Button>
                                        :
                                    <Button  variant="contained"
                                             color="red"
                                             className='booking-list_element_btn'
                                    >
                                        Old booking
                                    </Button>
                                }
                            </div>
                        }
                    })}
                </div>
            }
        </div>
    )
}