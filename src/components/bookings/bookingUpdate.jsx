import React, {useEffect, useState} from 'react';
import {Button} from "@material-ui/core";
import {authenticationService} from "../../services/auth.service";
import MenuItem from "@material-ui/core/MenuItem";
import axios from "axios";
import InputLabel from "@material-ui/core/InputLabel/InputLabel";
import Select from "@material-ui/core/Select/Select";
import './bookingCreate.css'
const API_URL_GET_ROOMS = "http://localhost:8080/api/rooms/";
const API_URL_GET_BOOK = "http://localhost:8080/api/book/";
import {useHistory} from 'react-router-dom';


const occupancyTypes = [
    'see all',1,2,3,4,5,6,7,8,9,10
]

const destinationTypes = [
    'see all','Aqtobe', 'Kostanay', 'Astana'
]

export const BookingUpdate = (props) => {
    const history = useHistory();
    const {booking, bookings, rooms, cancel} = props;
    const [occupancy, setOccupancy] = useState('see all');
    const today = new Date();
    const todayString = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();

    const [startDate, setStartDate] = useState(booking.fromDate);
    const [endDate, setEndDate] = useState(booking.toDate);
    const [destination, setDestination] = useState('see all');

    const occupancyList = occupancyTypes.map((occup) => {
        return  <MenuItem value={occup} key={occup}>{occup}</MenuItem>
    });

    const destinationList = destinationTypes.map((dest) => {
        return  <MenuItem value={dest} key={dest}>{dest}</MenuItem>
    });

    const onChangeOccupancy = (e) => {
        setOccupancy(e.target.value);
    };

    const onChangeStartDate = (e) => {
        if (e.target.value > endDate){
            setStartDate(endDate);
        } else if (e.target.value < todayString){
            setStartDate(todayString)
        } else {
            setStartDate(e.target.value);
        }

    };

    const onChangeEndDate = (e) => {
        if (e.target.value < startDate){
            setEndDate(startDate);
        } else {
            setEndDate(e.target.value);
        }
    };

    const onChangeDestination = (e) => {
        setDestination(e.target.value);
    };


    const updateBook = (bookId, roomId, fromDate, toDate) => {
        axios.post(API_URL_GET_BOOK+'update', {
            bookID: parseInt(bookId),
            roomID: parseInt(roomId),
            fromDate: Date.parse(startDate),
            toDate: Date.parse(endDate)
        }).then(()=>{
            alert('you successfully updated the booking');
            history.go(0);
        })
    };

    return (
        <div className='search-container'>
            <Button
                style={{marginTop: '20px'}}
                variant="contained"
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
                        <div className={booking.room.id === room.id ? 'booking-list_element' : 'booking-list_element current-room'}>
                            <div className='booking-list_element_info'>hotel name: {room.roomType.hotel.name} </div>
                            <div className='booking-list_element_info'>floor: {room.floor} </div>
                            <div className='booking-list_element_info'>number: {room.number} </div>
                            <div className='booking-list_element_info'>capacity: {room.roomType.capacity} </div>
                            <div className='booking-list_element_info'>location: {room.destination} </div>
                            <div className='booking-list_element_btn'>
                                <Button  variant="contained"
                                         color="primary"
                                         onClick={() => {
                                             updateBook(booking.id, room.id, startDate, endDate);
                                         }}>
                                    update book with this room
                                </Button>
                            </div>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}