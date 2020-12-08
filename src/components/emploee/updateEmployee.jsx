import React, {useState} from 'react';
import {Button, TextField} from "@material-ui/core";
import axios from "axios";
import './updateEmployee.css';
import {authenticationService} from "../../services/auth.service";
import {useHistory} from 'react-router-dom';

export const UpdateEmployee = (props) => {
    const { employee, cancel} = props;
    const history = useHistory();
    const [hours, setHours] = useState(employee.hours);
    const [salary, setSalary] = useState(employee.salary);
    const [position, setPosition] = useState(employee.position);

    const [currentUser, setCurrentUser] = useState(authenticationService.currentUserValue);

    // if (currentUser.email !== 'manager@gmail.com'){
    //     return null;
    // }

    const onChangeHours = (e) => {
        setHours(e.target.value);
    };

    const onChangeSalary = (e) => {
        setSalary(e.target.value);
    };

    const onChangePosition = (e) => {
        setPosition(e.target.value);
    };


    const update = () => {
        axios.post("http://localhost:8080/api/hotels/update/employee", {
            employeeId: employee.id,
            employeeHours: parseInt(hours),
            employeeSalary: parseInt(salary),
            employeePosition: position
        }).then(()=>{
            alert('you successfully updated employee info');
            // cancel();
            history.go(0);
        })
    };

    //TODO: make check types for inputs
    return <div className='update-employee-container'>
        <div className='update-employee_cancel' onClick={cancel}>X</div>
        <div className='update-employee_header'>Update employee</div>
        <div className='update-employee_input'>
            <TextField
                style={{width: '100%'}}
                id='hours'
                label='hours'
                placeholder='hours'
                variant='outlined'
                value={hours}
                onChange={onChangeHours}
            />
        </div>
        <div className='update-employee_input'>
            <TextField
                style={{width: '100%'}}
                id='Salary'
                label='Salary'
                placeholder='Salary'
                variant='outlined'
                value={salary}
                onChange={onChangeSalary}
            />
        </div>

        <div className='update-employee_input'>
            <TextField
                style={{width: '100%'}}
                id='Position'
                label='Position'
                placeholder='Position'
                variant='outlined'
                value={position}
                onChange={onChangePosition}
            />
        </div>
        <div className='update'>
            <Button
                style={{width: '100%'}}
                variant="contained"
                     color="primary"
                     onClick={update}
            >
                update
            </Button>
        </div>
    </div>
}