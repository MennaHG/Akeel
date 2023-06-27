import AkeelBar from './comps/TopBar.js'
import Register from './register.js';
import Login  from './login.js';
import {Routes, Route, useNavigate, Link} from 'react-router-dom';
import './index.css'

function App() {
  
  return (
    <div className="App">
      <AkeelBar/>
      <Routes>
            <Route exact path="/login" index element={<Login/>} />
            <Route exact path="/register" index element={<Register/>} />

            </Routes>   

      </div>
  );
}

export default App;
