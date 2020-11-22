import React, {useEffect, useState} from 'react';
import 'date-fns';
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import { useHistory } from "react-router-dom";
import './bookingCreate.css';

import {Button, IconButton, TextField} from "@material-ui/core";
import axios from "axios";
const API_URL_GET_ROOMS = "http://localhost:8080/api/rooms/";
const API_URL_GET_BOOK = "http://localhost:8080/api/book/";

const occupancyTypes = [
    'see all',1,2,3,4,5,6,7,8,9,10
];

const destinationTypes = [
    'see all','Aqtobe', 'Kostanay', 'Astana'
]



export const BookingCreate = (props) => {
    const history = useHistory();

    const {cancel} = props;
    const [occupancy, setOccupancy] = useState('see all');
    const today = new Date();
    const todayString = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();
    console.log(todayString);

    const [startDate, setStartDate] = useState(todayString);
    const [endDate, setEndDate] = useState(todayString);
    const [destination, setDestination] = useState('see all');
    const [guestId, setGuestId] = useState('');

    const [bookings, setBookings] = useState([]);
    const [rooms, setRooms] = useState([]);

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

    const onChangeGuestId = (e) => {
        setGuestId(e.target.value);
    }

    const book = (roomId, userId, startDate, endDate) => {
        axios.post(API_URL_GET_BOOK+'room', {
            roomID: roomId,
            userID: userId,
            fromDate: Date.parse(startDate),
            toDate: Date.parse(endDate)
        }).then(()=>{
            alert('you successfully booked the room');
            history.go(0);
        })
    }
    useEffect(() => {
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
            <Button  variant="contained"
                     color="primary"
                     className='booking-list_element_btn'
                     onClick={cancel}
            >
                X
            </Button>
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
                    <label htmlFor="guestId">quest ID:</label>
                    <input id="guestId" value={guestId} onChange={onChangeGuestId}/>
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
                                             book(room.id, parseInt(guestId), startDate, endDate)
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