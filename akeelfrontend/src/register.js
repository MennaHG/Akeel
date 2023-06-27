import React, { useState } from 'react';
import './comps/register.css'
import {Routes, Route, useNavigate, Link} from 'react-router-dom';
import Login  from './login.js';
/*
const signup = () => {
  const[name,setName]=useState('')
  const[password,setPassword]=useState('')

  // Simple POST request with a JSON body using fetch
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({  "name":"Nada",
    "password":"12345" })
};
fetch('http://localhost:8080/akeel/api/customer/signup', requestOptions)
    .then(response => response.json())
    .then(data => this.setState({ password: data.password , name:data.name}));
};*/

let rolename=()=>{
  let roleElem = document.getElementById("role");
  var roletext = roleElem.options[roleElem.selectedIndex].text;
  
  if(roleElem.value == 2)    //Restaurant Owner
    return "Owner"
  return roletext;
}

function Register() {
  const[name,setName]=useState('')
  const[password,setPassword]=useState('')

  const handleClick=(e)=>{
    let url = "http://localhost:8080/akeel/api/"+rolename()+"/signup"
    e.preventDefault()
    const user={name,password}
    console.log(user)
    fetch(url,{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(user)

  }).then(()=>{
    console.log("New User added")
  })
}
  return (
    <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row justify-content-center align-items-center h-100">
        <div class="col-12 col-lg-9 col-xl-7">
          <div class="card shadow-2-strong card-registration" >
            <div class="card-body p-4 p-md-5">
              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Sign Up</h3>
              <form>
  
                <div class="row">
                  <div class="col-md-6 mb-4">
  
                    <div class="form-outline">
                    <label class="form-label" for="UserName">User Name

                      <input value={name} type="text" id="UserName" class="form-control form-control-lg" autocomplete="off" onChange={(e)=>setName(e.target.value)} />
                      </label>
                    </div>
  
                  </div>
               
                </div>
  
                <div class="row">
                  <div class="col-md-6 mb-4 d-flex align-items-center">
  
                    <div class="form-outline datepicker w-100">
                    <label for="Password" class="form-label">Password

                      <input value={password} type="password" class="form-control form-control-lg" id="Password" autocomplete="off"  onChange={(e)=>setPassword(e.target.value)}/>
                      </label>
                    </div>
  
                  </div>
                  <div class="col-md-6 mb-4">
  
                    
                  </div>
                </div>
  
              
                <div class="row">
                  <div class="col-12">
                  <label for="role" class="form-label select-label"> Role</label>
                    <select id="role" class="select form-control-lg" >
                      <option value="1"  >Customer</option>
                      <option value="2" >Restaurant Owner</option>
                      <option value="3" >Runner</option>
                    </select>
                 
  
                  </div>
                </div>
  
                <div class="mt-4 pt-2">
                  <input class="btn btn-primary btn-lg" type="submit" value="Submit" onClick={handleClick} />
                </div>
  
              </form>
            </div>
            <div class="signin"><text>Already Have an account? </text> 
            
            <Link to="/login" >Sign in</Link>
            <Routes>
            <Route exact path="/login" index element={<Login/>} />

            </Routes>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  )}

export default Register;

