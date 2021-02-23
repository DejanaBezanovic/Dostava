import React from 'react';
import DostavaAxios from './../../apis/DostavaAxios';
import { Button, Form } from "react-bootstrap";

class EditNarudzbine extends React.Component {

    constructor(props) {
        super(props);

         let narudzbina = {
             number: 0,
             date: '',
             address: '',
             price: 0.00,
             description: '',
             deliveryId: -1,
         };
    
       this.state = { 
         narudzbina: narudzbina,
         dostavljaci: []}
   }

   componentDidMount() {
       this.getNarudzbinaById(this.props.match.params.id);
       this.getDostavljaci();
   }

   getNarudzbinaById(id) {
    DostavaAxios.get('/orders/' + id)
    .then(res => {
        this.setState({narudzbina: res.data})
    })
    .catch(error => {
        // handle error
        console.log(error);
        alert('Error occured please try again!');
     });
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

    valueInputChange(event) {
        let control = event.target;
    
        let name = control.name;
        let value = control.value;
    
        let narudzbina = this.state.narudzbina;
        narudzbina[name] = value;
    
        this.setState({ narudzbina: narudzbina });
    }

    doEdit() {
        DostavaAxios.put('/orders/' + this.props.match.params.id, this.state.narudzbina)
        .then(res => {
            console.log(res);
            alert('Narudzbina je uspesno izmenjena!');
            this.props.history.push('/orders');
        })
        .catch(error => {
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    
    render() {
        return(
            <div>
                <h1>Narudzbina</h1>
                    <Form>
                    <Form.Group>
                        <Form.Label>Broj narudzbine</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="number"
                        value={this.state.narudzbina.number}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Datum</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="date"
                        value={this.state.narudzbina.date}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Mesto isporuke</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="address"
                        value={this.state.narudzbina.address}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Cena</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="price"
                        value={this.state.narudzbina.price}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Opis</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="description"
                        value={this.state.narudzbina.description}
                        as="input"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Dostavljac</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="deliveryId"
                        value={this.state.narudzbina.deliveryId}
                        as="select"
                        >
                        <option>{this.state.narudzbina.deliveryName}</option>
                        {this.state.dostavljaci.map((dostavljac) => {
                            return (
                            <option value={dostavljac.id} key={dostavljac.id}>
                                {dostavljac.imeIPrezime}
                            </option>
                            );
                        })}
                        </Form.Control>
                    </Form.Group>
                    <Button variant="primary" onClick={() => this.doEdit()}>
                        Edit
                    </Button>
                    </Form>
            </div>
        )
    }
}

export default EditNarudzbine;
