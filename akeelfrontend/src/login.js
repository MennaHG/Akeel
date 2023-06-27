import React from 'react';
import './comps/register.css'
import {Routes, Route, Link} from 'react-router-dom';


import Register from './register';

function Login() {
  return (
    <section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row justify-content-center align-items-center h-100">
        <div class="col-12 col-lg-9 col-xl-7">
          <div class="card shadow-2-strong card-registration" >
            <div class="card-body p-4 p-md-5">
              <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Log In</h3>
              <form>
  
                <div class="row">
                  <div class="col-md-6 mb-4">
  
                    <div class="form-outline">
                    <label class="form-label" for="UserName">User Name</label>

                      <input type="text" id="UserName" class="form-control form-control-lg" autocomplete="off" />
                    </div>
  
                  </div>
               
                </div>
  
                <div class="row">
                  <div class="col-md-6 mb-4 d-flex align-items-center">
  
                    <div class="form-outline datepicker w-100">
                    <label for="Password" class="form-label">Password</label>

                      <input type="password" class="form-control form-control-lg" id="Password" autocomplete="off" />
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
                  <input class="btn btn-primary btn-lg" type="submit" value="Log In" />
                </div>
  
              </form>
            </div>
            <div class="signin"><label>Create an account </label>  
            <Link to="/register">Sign Up</Link>
              <Routes>
            <Route exact path="/register" index element={<Register/>} />

            </Routes>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  )}
export default Login;