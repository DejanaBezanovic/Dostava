import React from 'react';
import DostavaAxios from './../../apis/DostavaAxios';
import {Button, Form} from "react-bootstrap";


class AddNarudzbina extends React.Component {

    constructor(props) {
        super(props);

        let narudzbina = {
                broj: 0,
                datum: '',
                mestoIsporuke: '',
                cena: 0.00,
                opis: '',
                deliveryId: -1,
            }


        this.state = {
            dostavljaci: [],
            narudzbina: narudzbina
        }
    }

    componentDidMount() {
        this.getDostavljaci()
    }

    getDostavljaci() {
        DostavaAxios.get('/dostavljaci')
        .then(res => {
            console.log(res);
            this.setState({dostavljaci: res.data});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
        });
    }

    valueInputChanged(e) {
        let input = e.target;
    
        let name = input.name;
        let value = input.value;
    
        let narudzbina = this.state.narudzbina;
        narudzbina[name] = value;
    
        this.setState({ narudzbina: narudzbina });
      }

    create() {
        let newDto = {
           number: this.state.narudzbina.broj,
            date: this.state.narudzbina.datum,
            address: this.state.narudzbina.mestoIsporuke,
            price: this.state.narudzbina.cena,
            description: this.state.narudzbina.opis,
            deliveryId: this.state.narudzbina.deliveryId,
        }

        DostavaAxios.post('/orders', newDto)
        .then(res => {
            // handle success
            console.log(res);
           
            alert('Order was added successfully!');
            this.props.history.push('/orders');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    render() {
        return(
            <div>
        <h1>Dodaj narudzbinu</h1>
        <Form>
          <Form.Group>
            <Form.Label>Broj narudzbine</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChanged(event)}
              name="broj"
            //   value={this.state.narudzbina.brojNarudzbine}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Datum</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChanged(event)}
              name="datum"
              value={this.state.narudzbina.datum}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Mesto isporuke</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChanged(event)}
              name="mestoIsporuke"
              value={this.state.narudzbina.mestoIsporuke}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Cena</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChanged(event)}
              name="cena"
              value={this.state.narudzbina.cena}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Opis</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChanged(event)}
              name="opis"
              value={this.state.narudzbina.opis}
              as="input"
            ></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Dostavljac</Form.Label>
            <Form.Control
              onChange={(event) => this.valueInputChanged(event)}
              name="deliveryId"
            //   value={this.state.narudzbina.deliveryId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.dostavljaci.map((dostavljac) => {
                return (
                  <option value={dostavljac.id} key={dostavljac.id}>
                    {dostavljac.imeIPrezime}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.create()}>
            Add
          </Button>
        </Form>
        </div>
        )
    }
}

export default AddNarudzbina;