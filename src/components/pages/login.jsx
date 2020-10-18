import React, { useState, useEffect } from 'react';

import { TextField, IconButton, Button } from '@material-ui/core';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import { authenticationService } from '../../services/auth.service'

import './login.css'

const initialErrors = {
    emailError: '',
    passwordError: ''
}

import { useHistory } from "react-router-dom";

export const Login = () => {

    let history = useHistory();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState('');
    const [validateErrors, setValidateErrors] = useState(initialErrors);
    const [message, setMessage] = useState('');

    useEffect(() => {
        if (authenticationService.currentUserValue) {
            history.push('/');
        }
    });

    const changeEmail = (e) => {
        setEmail(e.target.value);
    }

    const changePassword = (e) => {
        setPassword(e.target.value);
    }

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    }

    const validate = () => {
        let newErrors = {...validateErrors}
        newErrors.emailError = (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/).test(email) ? '' : 'Invalid email'
        newErrors.passwordError = password.length >= 6 ? '' : 'Minimum 6 symbols required'

        setValidateErrors(newErrors);

        return Object.values(newErrors).every(error => error === '')
    }

    const submit = (e) => {
        e.preventDefault();

        if (validate()){
            authenticationService.login(email, password).then(
                () => {
                    history.push('/profile');
                },
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();
                    setMessage(resMessage);
                    alert(resMessage);
                }
            )
        }

    }

    return (
        <form className='form-container' onSubmit={submit}>
            <div className='form-container__name'>Login</div>
            <TextField
                className='form-container__input'
                id='email'
                label='email'
                placeholder='enter your email'
                variant='outlined'
                value={email}
                onChange={changeEmail}
                {...(validateErrors.emailError && {error: true, helperText: validateErrors.emailError})}
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
                {...(validateErrors.passwordError && {error: true, helperText: validateErrors.passwordError})}
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
            <Button className='form-container__btn' variant="contained" type='submit'>LOGIN</Button>
        </form>
    )
}