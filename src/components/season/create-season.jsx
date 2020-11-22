import React, {useState} from 'react';
import {Button, TextField} from "@material-ui/core";
import axios from "axios";
import './updateEmployee.css';
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel/InputLabel";
import Select from "@material-ui/core/Select/Select";

export const CreateSeason = (props) => {
    const { hotels, cancel} = props;

    const today = new Date();
    const todayString = today.getFullYear() + "-" + today.getMonth() + "-" + today.getDate();
    console.log(todayString);

    const [startDate, setStartDate] = useState(todayString);
    const [endDate, setEndDate] = useState(todayString);
    const [selectedHotel, setSelectedHotel] = useState(1);
    const [hotelName, setHotelName] = useState('');
    const [coefficient, setCoefficient] = useState(1);

    const hotelsList = hotels.map((hotel) => {
        return  <MenuItem value={hotel.hotelID} key={hotel.hotelID}>{hotel.name}</MenuItem>
    })

    const onChangeSelectedHotel = (e) => {
        setSelectedHotel(e.target.value);
    }

    const onChangeHotelName = (e) => {
        setHotelName(e.target.value);
    }

    const onChangeCoefficient = (e) => {
        setCoefficient(e.target.value);
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

    const createSeason = () => {
        axios.post("http://localhost:8080/api/season/create", {
            hotelId: parseInt(selectedHotel),
            seasonName: hotelName,
            startDate: Date.parse(startDate),
            endDate: Date.parse(endDate),
            coefficient: parseFloat(coefficient)
        }).then(()=>{
            alert('you successfully created new season');
            // history.push('/seasons');
            //TODO: Add to season function.
        })
    }

    return <div className='update-employee'>
        <div className='cancel' onClick={cancel}>X</div>
        <div className='update-employee_header'>Create season route</div>
        <div className='update-employee_input'>
            <InputLabel id="demo-simple-select-label">Hotel IDs</InputLabel>
            <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={selectedHotel}
                onChange={onChangeSelectedHotel}
            >
                {hotelsList.map(hotel => hotel)}
            </Select>
        </div>
        <div className='update-employee_input'>
            <TextField
                style={{width: '100%'}}
                id='hotelName'
                label='Hotel name'
                placeholder='Hotel name'
                variant='outlined'
                value={hotelName}
                onChange={onChangeHotelName}
            />
        </div>
        <div className='update-employee_input'>
            <TextField
                style={{width: '100%'}}
                id='Coefficient'
                label='Coefficient'
                placeholder='Coefficient'
                variant='outlined'
                value={coefficient}
                onChange={onChangeCoefficient}
            />
        </div>
        <div className='update-employee_input'>
            <label htmlFor="startDate">start date:</label>
            <input id="startDate" type='date' value={startDate} onChange={onChangeStartDate}/>
        </div>
        <div className='update-employee_input'>
            <label htmlFor="endDate">end date:</label>
            <input id="endDate" type='date' value={endDate} onChange={onChangeEndDate}/>
        </div>
        <Button  variant="contained"
                 color="primary"
                 className='update'
                 onClick={createSeason}
        >
            Create season
        </Button>
    </div>
}