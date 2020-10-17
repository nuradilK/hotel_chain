import React, { useState } from 'react';

import { TextField, IconButton, Button } from '@material-ui/core';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';

import './login.css'

export const Register = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState('');

    const changeEmail = (e) => {
        setEmail(e.target.value);
    }

    const changePassword = (e) => {
        setPassword(e.target.value);
    }

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    }

    const submit = () => {

    }

    return (
        <form className='form-container'>
            <div className='form-container__name'>Register</div>
            <TextField
                className='form-container__input'
                id='email'
                label='email'
                placeholder='enter your email'
                variant='outlined'
                value={email}
                onChange={changeEmail}
            />
            <TextField
                className='form-container__input'
                id='password'
                label='password'
                placeholder='enter your password'
                variant='outlined'
                value={password}
                type = {showPassword ? 'text' : 'password'}
                onChange={changePassword}
                InputProps={{
                    endAdornment:
                        <IconButton
                            aria-label='toggle password visibility'
                            onClick={handleClickShowPassword}
                        >
                            {showPassword ? <Visibility/> : <VisibilityOff/>}
                        </IconButton>
                }}
            />
            <Button className='form-container__btn' variant="contained">REGISTER</Button>
        </form>
    )
}