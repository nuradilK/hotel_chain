import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button} from "@material-ui/core";
import './employees-review.css';
import {UpdateEmployee} from "../emploee/updateEmployee";
import {CreateSeason} from "../season/create-season";


export const Seasons = () => {

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

    }

    const notifyAll = () => {
        axios.post("http://localhost:8080/api/notification", {
            userId: -1,
        }).then(()=>{
            alert('you successfully notified all guests');
            history.go(0);
        })
    }

    return <div>
        {
            isCreateSeason &&
            <div className='update-modal'>
                <CreateSeason hotels={hotels} cancel = {() => setIsCreateSeason(false)}/>
            </div>
        }
        <div>
            <Button  variant="contained"
                     color="primary"
                     className='employees-list-element_btn'
                     onClick={() => setIsCreateSeason(true)}
            >
                Create season
            </Button>
            <Button  variant="contained"
                     color="primary"
                     className='employees-list-element_btn'
                     onClick={notifyAll}
            >
                Notify quests
            </Button>
        </div>
        <div className='employees-list'>
            <div className='employees-list_header'>Seasons</div>
            {
                hotels.map((hotel) => {
                    return hotel.seasons.map(season => {
                        return(
                            <div className='employees-list-element'>
                                <div className='employees-list-element_part'>Hotel: {hotel.name}</div>
                                <div className='employees-list-element_part'>Season name: {season.name}</div>
                                <div className='employees-list-element_part'>Start date: {season.startDate}</div>
                                <div className='employees-list-element_part'>End date: {season.endDate}</div>
                                <div className='employees-list-element_part'>Coefficient: {season.coefficient}</div>
                                <Button  variant="contained"
                                         color="primary"
                                         className='employees-list-element_btn'
                                         onClick={deleteSeason}
                                >
                                    cancel
                                </Button>
                            </div>
                        )
                    })
                })
            }
        </div>
    </div>

}