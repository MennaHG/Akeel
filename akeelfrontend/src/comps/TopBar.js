import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { red } from '@mui/material/colors';
 


export default function AkeelBar() {
  return (
    <Box sx={{ flexGrow: 1 }} >
    <div  class="topbar"  style={{color:"black"}}>
        <AppBar position="static"style={{backgroundColor:"#03254c"}}  > 
        <Toolbar >
        <Typography variant="h6" component="div" >
            Akeel
          </Typography>
         
        </Toolbar>
      </AppBar>
      </div>
    </Box>
  );
}
