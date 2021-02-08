import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, HashRouter as Router, Switch } from 'react-router-dom';
import {Container, Button, Navbar, Nav} from "react-bootstrap"
import Login from "./components/login/Login"
import Home from './components/Home';
import {logout} from './services/auth'
import Narudzbine from './components/Narudzbine/Narudzbine'
import AddNarudzbina from './components/Narudzbine/AddNarudzbina'
import Racun from './components/Narudzbine/Racun'

class App extends React.Component {

    render() {
        return (
            <div>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/orders">Orders</Nav.Link>
                            <Nav.Link as={Link} to="/addOrder">Add order</Nav.Link>

   
                            {window.localStorage['jwt'] ? 
                            <Button onClick = {()=>logout()}>Logout</Button> :
                            <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                            }
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"25px"}}>
                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/orders" component={Narudzbine} />
                        <Route exact path="/addOrder" component={AddNarudzbina} /> 
                        {/* <Route exact path="/orders/edit/:id" component={EditNarudzbina} /> */}
                        <Route exact path="/orders/racuni/:id" component={Racun} /> 
                    </Switch>
                    </Container>
                </Router>
            </div>
        );
    }
};


ReactDOM.render(
    <App/>,
    document.querySelector('#root')
);