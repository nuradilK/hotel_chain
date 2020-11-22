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

import './search-available-room.css'
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import {Button, IconButton, TextField} from "@material-ui/core";
import axios from "axios";
const API_URL_GET_ROOMS = "http://localhost:8080/api/rooms/";
const API_URL_GET_BOOK = "http://localhost:8080/api/book/";



const occupancyTypes = [
    'see all',1,2,3,4,5,6,7,8,9,10
]

const destinationTypes = [
    'see all' , 'Nur-Sultan', 'Aktobe', 'Taraz'
]


export const SearchAvailableRooms = () => {
    const [occupancy, setOccupancy] = useState('see all');
    const today = new Date();
    const todayString = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();
    console.log(todayString);

    const [startDate, setStartDate] = useState(todayString);
    const [endDate, setEndDate] = useState(todayString);
    const [destination, setDestination] = useState('see all');
    const [currentUser, setCurrentUser] = useState(authenticationService.currentUserValue);
    const [bookings, setBookings] = useState([]);
    const [rooms, setRooms] = useState([]);

    const history = useHistory();
    const occupancyList = occupancyTypes.map((occup) => {
        return  <MenuItem value={occup} key={occup}>{occup}</MenuItem>
    })

    const destinationList = destinationTypes.map((dest) => {
        return  <MenuItem value={dest} key={dest}>{dest}</MenuItem>
    })

    const onChangeOccupancy = (e) => {
        setOccupancy(e.target.value);
    }

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

    const onChangeDestination = (e) => {
        setDestination(e.target.value);
    }

    const book = (roomId, userId, startDate, endDate) => {
        axios.post(API_URL_GET_BOOK+'room', {
            roomID: parseInt(roomId),
            userID: parseInt(userId),
            fromDate: Date.parse(startDate),
            toDate: Date.parse(endDate),
            bill: 0
        }).then(()=>{
            alert('you successfully booked the room');
            history.push('/profile');
        })
    }
    useEffect(() => {
        setCurrentUser(authenticationService.currentUserValue)
        axios.get(API_URL_GET_ROOMS)
            .then(res => {
                setRooms(res.data);
            });
        axios.get(API_URL_GET_BOOK).then((res) => {
            setBookings(res.data);
        })
    },[]);

    return (
        <div className='search-container'>
            <div className='search-rooms'>
                <div>
                    <label for="startDate">start date:</label>
                    <input id="startDate" type='date' value={startDate} onChange={onChangeStartDate}/>
                </div>
                <div>
                    <label for="endDate">end date:</label>
                    <input id="endDate"type='date' value={endDate} onChange={onChangeEndDate}/>
                </div>
                <div>
                    <InputLabel id="demo-simple-select-label">Occupancy</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={occupancy}
                        onChange={onChangeOccupancy}
                    >
                        {occupancyList.map((occup) => occup)}
                    </Select>
                </div>
                <div>
                    <InputLabel id="demo-simple-select-label">Destination</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={destination}
                        onChange={onChangeDestination}
                    >
                        {destinationList.map(dest => dest)}
                    </Select>
                </div>
            </div>
                <div className='booking-list'>
                    <h1 className='booking-list_element_header'>Available rooms</h1>
                    {rooms.filter((room) => {
                        let check = true;
                        bookings.forEach((booking) => {
                            if (booking.room.id === room.id){
                                if ((startDate >= booking.fromDate && startDate <= booking.toDate)
                                    || (endDate >= booking.fromDate && endDate <= booking.toDate)
                                ){
                                    check=false;
                                }
                            }
                        });
                        return check;
                    }).filter((room) => {
                        if (occupancy === 'see all'){
                            return true;
                        }
                        return room.roomType.capacity === occupancy;
                    }).filter((room) => {
                        if (destination === 'see all'){
                            return true;
                        }
                        return room.roomType.hotel.address === destination;
                    }).map(room => {
                        return (
                            <div className='booking-list_element'>
                                <div className='booking-list_element_info'>hotel name: {room.roomType.hotel.name} </div>
                                <div className='booking-list_element_info'>floor: {room.floor} </div>
                                <div className='booking-list_element_info'>number: {room.number} </div>
                                <div className='booking-list_element_info'>capacity: {room.roomType.capacity} </div>
                                <div className='booking-list_element_info'>location: {room.destination} </div>
                                <Button  variant="contained"
                                         color="primary"
                                         className='booking-list_element_btn'
                                         onClick={() => {
                                    const confirmBooking = confirm("Do you want to book this room?");
                                    if (confirmBooking){
                                        book(room.id, currentUser.id, startDate, endDate)
                                    }
                                }}>
                                    book room
                                </Button>
                            </div>
                        )
                    })}
                </div>
        </div>
    )
}