import React from "react";
import {Form, Button, Row, Col} from 'react-bootstrap'
import {login} from "../../services/auth"

class Login extends React.Component {
  constructor() {
    super();

    this.state = { username: "", password: "" };
  }

  onInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let change = {};
    change[name] = value;
    this.setState(change);
  }

  render() {
    return (
      <Row className="justify-content-center">
        <Col md={6}>        
          <Form>
            <Form.Group>
              <Form.Label>Username</Form.Label>
              <Form.Control type="text" name="username" onChange = {(e) => this.onInputChange(e)}/>
            </Form.Group>
            <Form.Group>
              <Form.Label>Password</Form.Label>
              <Form.Control type="password" name="password" onChange = {(e) => this.onInputChange(e)}/>
            </Form.Group>
            <Button variant="success" onClick={() => {login(this.state.username, this.state.password)}}>Log in</Button>
          </Form>
        </Col>
      </Row>
    );
  }
}

export default Login;
