import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button} from "@material-ui/core";
import './season.css';
import {UpdateEmployee} from "../emploee/updateEmployee";
import {CreateSeason} from "../season/create-season";
import {authenticationService} from "../../services/auth.service";
import { useHistory } from "react-router-dom";

export const Seasons = () => {

    const history = useHistory();
    const [currentUser, setCurrentUser] = useState(authenticationService.currentUserValue);

    if (currentUser.role.name !== 'Manager'){
        history.push('/');
        return null;
    }

    const [hotels, setHotels] = useState([]);
    const [isCreateSeason, setIsCreateSeason] = useState(false);

    useEffect(() => {
        axios.get("http://localhost:8080/api/hotels/all/hotels")
            .then(res => {
                console.log(res.data);
                setHotels(res.data);
            });
    }, []);

    const deleteSeason = (seasonId) => {
        axios.post("http://localhost:8080/api/hotels/delete/season", {
            seasonId: parseInt(seasonId)
        }).then(()=>{
            alert('you deleted the season!');
            history.go(0);
        })
    }

    const notifyAll = () => {
        axios.post("http://localhost:8080/api/notification", {
            userId: -1,
        }).then(()=>{
            alert('you successfully notified all guests');
            history.go(0);
        })
    }

    return <div className='season-container'>
        {
            isCreateSeason &&
            <div className='update-modal'>
                <CreateSeason hotels={hotels} cancel = {() => setIsCreateSeason(false)}/>
            </div>
        }
        <div className='search-container_manager'>
            <Button
                style={{marginRight: '10px'}}
                variant="contained"
                color="primary"
                onClick={() => setIsCreateSeason(true)}
            >
                Create season
            </Button>
            <Button
                style={{marginLeft: '10px'}}
                variant="contained"
                color="primary"
                onClick={notifyAll}
            >
                Notify quests
            </Button>
        </div>
        <div className='season-container_header'>Seasons</div>
        {
            hotels.map((hotel) => {
                return hotel.seasons.map(season => {
                    return(
                        <div className='season-element'>
                            <div className='season-element_part'>Hotel: {hotel.name}</div>
                            <div className='season-element_part'>Season name: {season.name}</div>
                            <div className='season-element_part'>Start date: {season.startDate}</div>
                            <div className='season-element_part'>End date: {season.endDate}</div>
                            <div className='season-element_part'>Coefficient: {season.coefficient}</div>
                            <div className='season-element_btn'>
                                <Button  variant="contained"
                                         color="primary"
                                         onClick={() => deleteSeason(season.seasonId)}
                                >
                                    cancel
                                </Button>
                            </div>
                        </div>
                    )
                })
            })
        }
    </div>

}