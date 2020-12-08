import React, {useEffect, useState} from 'react';

import './services.css';
import axios from "axios";
import InputLabel from "@material-ui/core/InputLabel/InputLabel";
import Select from "@material-ui/core/Select/Select";
import MenuItem from "@material-ui/core/MenuItem";
import {Button} from "@material-ui/core";
import {authenticationService} from "../../services/auth.service";

export const Services = () => {

    const [services, setServices] = useState([]);
    const [bookings, setBookings] = useState([]);
    const [booking, setBooking] = useState();

    const [currentUser, setCurrentUser] = useState(authenticationService.currentUserValue);

    useEffect(() => {
        axios.get("http://localhost:8080/api/service/get").then(res => {
                // console.log(res.data);
                setServices(res.data);
            });

        axios.get("http://localhost:8080/api/book").then((res) => {
            console.log(res.data);
            let books = res.data;
            books = books.filter((book)=>book.user.id === currentUser.id);
            // console.log(books);
            setBooking(books[0].id);
            setBookings(books);
        })

    }, []);

    const onChangeBooking = (e) => {
        setBooking(e.target.value);
    }

    const registerService = (serviceID) => {
        axios.post("http://localhost:8080/api/service/use", {
            bookId: booking,
            serviceId: serviceID
        }).then(()=>{
            alert(`you successfully registered the service for booking id${booking}`);
            history.push('/profile');
        })
    };

    return <div className='services-container'>
        <div className='services-container_header'>
            Register services
        </div>
        <div className='services-list'>
            {
                services.map((service) => {
                    // console.log(bookings)
                    return <div className='service'>
                        {bookings.length > 1 &&
                        <div className='service__item'>
                            <InputLabel id="demo-simple-select-label">Select booking</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value={booking}
                                onChange={onChangeBooking}
                            >
                                {bookings.map((booking) => <MenuItem value={booking.id} key={booking.id}>{booking.id}</MenuItem>)}
                            </Select>
                        </div>
                        }

                        <div className='service__item'>ID: {service.serviceID}</div>
                        <div className='service__item'>Name: {service.name}</div>
                        <div className='service__item'>Price: {service.price}</div>
                        <div className='service__btn'>
                            <Button  variant="contained"
                                     color="primary"
                                     className='booking-list_element_btn'
                                     onClick={() => {
                                         registerService(service.serviceID)
                                     }}>
                                register
                            </Button>
                        </div>

                    </div>
                })
            }
        </div>
    </div>

}