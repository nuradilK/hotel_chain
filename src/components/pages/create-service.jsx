import React, {useState} from 'react';
import axios from "axios";
import {Button, TextField} from "@material-ui/core";
import './create-service.css';
export const CreateService = () => {

    const [name, setName] = useState('');
    const [price, setPrice] = useState();

    const onChangeName = (e) => {
        setName(e.target.value);
    };

    const onChangePrice = (e) => {
        setPrice(e.target.value);
    };


    const createService = () => {
        axios.post("http://localhost:8080/api/service/create", {
            name: name,
            price: parseInt(price)
        }).then(()=>{
            alert('you successfully created service!');
            // cancel();
            // history.go(0);
        })
    };

    return <div className='create-service-container'>
        <div className='create-service-item'>
            <TextField
                style={{width: '100%'}}
                id='name'
                label='name'
                placeholder='name'
                variant='outlined'
                value={name}
                onChange={onChangeName}
            />
        </div>
        <div className='create-service-item'>
            <TextField
                style={{width: '100%'}}
                id='price'
                label='price'
                placeholder='price'
                variant='outlined'
                value={price}
                onChange={onChangePrice}
            />
        </div>
        <div className='create-service-item_btn'
                 onClick={() => {
                     createService()
                 }}>
            create service
        </div>
    </div>
}