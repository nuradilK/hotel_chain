import React, {useEffect, useState} from 'react';
import 'date-fns';
import { KeyboardDatePicker } from '@material-ui/pickers';
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import MuiPickersUtilsProvider from "@material-ui/pickers/MuiPickersUtilsProvider";
import DateFnsUtils from '@date-io/date-fns';
import {authenticationService} from "../../services/auth.service";
import { useHistory } from "react-router-dom";

import './desk-clerk-manage-bookings.css';
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import {Button, IconButton, TextField} from "@material-ui/core";

import { BookingUpdate } from "../bookings/bookingUpdate";

import axios from "axios";
import {BookingCreate} from "../bookings/bookingCreate";
const API_URL_GET_ROOMS = "http://localhost:8080/api/rooms/";
const API_URL_GET_BOOK = "http://localhost:8080/api/book/";




const destinationTypes = [
    'Aqtobe', 'Kostanay', 'Astana'
]


export const DeskClearManageBookings = () => {
    const history = useHistory();
    const [currentUser, setCurrentUser] = useState(authenticationService.currentUserValue);

    if (currentUser.role){

    }


    const today = new Date();
    const todayString = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();

    const [startDate, setStartDate] = useState(todayString);
    const [endDate, setEndDate] = useState(todayString);
    const [userId, setUserId] = useState('');
    const [roomId, setRoomId] = useState('');
    const [bookId, setBookId] = useState('');

    const [bookings, setBookings] = useState([]);
    const [rooms, setRooms] = useState([]);

    const [isCreate, setIsCreate] = useState(false);
    const [selectedBooking, setSelectedBooking] = useState(null);



    const onChangeStartDate = (e) => {
        if (e.target.value > endDate){
            setStartDate(endDate);
        } else if (e.target.value < todayString){
            setStartDate(todayString)
        } else {
            setStartDate(e.target.value);
        }

    }

    const onChangeEndDate = (e) => {
        console.log(e.target.value);
        if (e.target.value < startDate){
            setEndDate(startDate);
        } else {
            setEndDate(e.target.value);
        }
    }


    const onChangeBookId = (e) => {
        setBookId(e.target.value);
    }

    const onChangeRoomId = (e) => {
        setRoomId(e.target.value);
    }

    const onChangeUserId = (e) => {
        setUserId(e.target.value);
    }




    const book = (roomId, userId, startDate, endDate) => {
        axios.post(API_URL_GET_BOOK+'room', {
            room: roomId,
            userId: userId,
            fromDate: Date.parse(startDate),
            toDate: Date.parse(endDate)
        }).then(()=>{
            alert('you successfully booked the room');
            history.push('/profile');
        })
    }

    useEffect(() => {
        setCurrentUser(authenticationService.currentUserValue)
        axios.get(API_URL_GET_ROOMS)
            .then(res => {
                console.log(res.data);
                setRooms(res.data);
            });
        axios.get(API_URL_GET_BOOK).then((res) => {
            console.log(res.data);
            setBookings(res.data);
        })
    },[]);

    const deleteBooking = (bookingId) => {
        axios.post(API_URL_GET_BOOK+'delete', {
            bookID: bookingId,
        }).then(()=>{
            const newBookings = bookings.filter((booking) => {
                return booking.id !== parseInt(bookingId);
            })
            // history.go(0);
            setBookings(newBookings);
            alert('you successfully deleted the booking');
            // history.push('/profile');
        })
    };

    return (
        <div className='desk-clerk-manage-bookings'>
            {
                isCreate &&
                <div className='update-booking-modal'>
                    <BookingCreate cancel={() => setIsCreate(false)}/>
                </div>
            }
            {
                selectedBooking !== null &&
                <div className='update-booking-modal'>
                    <BookingUpdate booking = {selectedBooking} bookings = {bookings} rooms={rooms} cancel={()=>setSelectedBooking(null)}/>
                </div>
            }
            <Button  variant="contained"
                     color="primary"
                     className='desk-clerk-manage-bookings_create-booking'
                     onClick={() => setIsCreate(true)}
            >
                Create new booking
            </Button>
            <div className='desk-clerk-manage-bookings_search-rooms'>
                <div>
                    <label for="startDate">start date:</label>
                    <input id="startDate" type='date' value={startDate} onChange={onChangeStartDate}/>
                </div>
                <div>
                    <label for="endDate">end date:</label>
                    <input id="endDate"type='date' value={endDate} onChange={onChangeEndDate}/>
                </div>
                <div>
                    <TextField
                        id="userId"
                        label="Search by user ID"
                        type="search"  value={userId}
                        variant="outlined"
                        onChange={onChangeUserId}
                    />
                </div>
                <div>
                    <TextField
                        id="roomId"
                        label="Search by room ID"
                        type="search"  value={roomId}
                        variant="outlined"
                        onChange={onChangeRoomId}
                    />
                </div>
                <div>
                    <TextField
                        id="bookId"
                        label="Search by booking ID"
                        type="search"  value={bookId}
                        variant="outlined"
                        onChange={onChangeBookId}
                    />
                </div>

            </div>
            <div className='desk-clerk-manage-bookings_booking-list'>
                <h1 className='desk-clerk-manage-bookings_booking-list-header'>Bookings</h1>
                {bookings.filter((booking) => {
                    if (booking.user.id === parseInt(userId) || userId === ''){
                        return true;
                    }
                    return false;
                }).filter((booking) => {
                    if (booking.id === parseInt(bookId) || bookId === ''){
                        return true;
                    }
                    return false;
                }).filter((booking) => {
                    if (booking.room.id === parseInt(roomId) || roomId === ''){
                        return true;
                    }
                    return false;
                }).filter((booking) => {
                    if (booking.fromDate >= startDate && booking.toDate <= endDate){
                        return true;
                    }
                    return false;
                }).map(booking => {
                    return (
                        <div className='desk-clerk-manage-bookings_list-element'>
                            <div className='booking-list_element_info'>booking ID: {booking.id} </div>
                            <div className='booking-list_element_info'>user ID: {booking.user.id} </div>
                            <div className='booking-list_element_info'>room ID: {booking.room.id} </div>
                            <div className='booking-list_element_info'>from date: {booking.fromDate} </div>
                            <div className='booking-list_element_info'>to date: {booking.toDate} </div>
                            <div className='booking-list_element_info'>Hotel name: {booking.room.roomType.hotel.name} </div>
                            <div className='booking-list_element_info'>capacity: {booking.room.roomType.capacity} </div>
                            <div className='booking-list_element_info'>location: {booking.room.destination} </div>
                            <Button  variant="contained"
                                     color="secondary"
                                     className='booking-list_element_btn'
                                     onClick={() => deleteBooking(booking.id)}
                            >
                                Delete booking
                            </Button>
                            <Button  variant="contained"
                                     color="primary"
                                     className='booking-list_element_btn'
                                     onClick={() => setSelectedBooking(booking)}
                            >
                                Update booking
                            </Button>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}