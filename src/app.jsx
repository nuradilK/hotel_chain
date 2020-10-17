import React from 'react';
import ReactDOM from 'react-dom';
import { Home } from './components/pages/home';
import { Login } from "./components/pages/login";

import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import { Navbar } from './components/navbar/navbar';
import { Register } from "./components/pages/register";

import './app.css';
import {PrivateRoute} from "./components/private-route/private-route";
import {Profile} from "./components/pages/profile";

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Navbar/>
            <Switch>
                <Route exact path='/'>
                    <Home/>
                </Route>
                <Route exact path='/login'>
                    <Login/>
                </Route>
                <Route exact path='/register'>
                    <Register/>
                </Route>
                <PrivateRoute exact path='/profile' component={Profile}/>
            </Switch>
        </Router>
    </React.StrictMode>,
    document.getElementById('app')
);
