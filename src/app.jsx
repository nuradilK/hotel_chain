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
import {SearchAvailableRooms} from "./components/pages/search-available-room";
import {DeskClearManageBookings} from "./components/pages/desk-clerk-manage-bookings";
import {EmployeesReview} from "./components/pages/employees-review";
import {Seasons} from "./components/pages/seasons";
import {Notification} from "./components/pages/notification";
import {Services} from "./components/pages/services";
import {CreateService} from "./components/pages/create-service";

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Notification/>
            <Navbar/>
            <Switch>
                <Route exact path='/'>
                    {/*<EmployeesReview/>*/}
                    {/*<Seasons/>*/}
                    <Home/>
                </Route>
                <Route exact path='/login'>
                    <Login/>
                </Route>
                <Route exact path='/register'>
                    <Register/>
                </Route>
                <PrivateRoute exact path='/createService' component={CreateService}/>
                <PrivateRoute exact path='/services' component={Services}/>
                <PrivateRoute exact path='/profile' component={Profile}/>
                <PrivateRoute exact path='/booking' component={SearchAvailableRooms}/>
                <PrivateRoute exact path='/deskclerk' component={DeskClearManageBookings}/>
                <PrivateRoute exact path='/employees' component={EmployeesReview}/>
                <PrivateRoute exact path='/seasons' component={Seasons}/>
            </Switch>
        </Router>
    </React.StrictMode>,
    document.getElementById('app')
);
