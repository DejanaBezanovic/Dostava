import React from 'react';
import DostavaAxios from './../../apis/DostavaAxios';
import { Table, Button, Form, ButtonGroup } from "react-bootstrap";

class Narudzbine extends React.Component {

    constructor(props) {
        super(props);

        // let Narudzbina = {
        //     brojNarudzbine: 0,
        //     datum: '',
        //     mestoIsporuke: '',
        //     cena: 0.00,
        //     opis: '',
        //     deliveryId: -1,
        // }

        this.state = {
            orders: [],
            pageNo: 0,
            totalPages: 1,
            dostavljaci: [],
            pretraga: {address: '', deliveryId: -1 }
        }
    }

    componentDidMount() {
        this.getDostavljaci()
        this.getNaruzbine(0)
    }

    getNaruzbine(page) {
        let config = { params: {
            pageNo: page
          } };

        if(this.state.pretraga.address != "") {
            config.params.address = this.state.pretraga.address;
        }
        if(this.state.pretraga.deliveryId != -1) {
          config.params.deliveryId = this.state.pretraga.deliveryId;
        } 

        DostavaAxios.get('/orders', config)
            .then(res => {
                this.setState({
                    pageNo: page,
                    orders: res.data,
                    totalPages: res.headers["total-pages"]
                })
            })
                .catch(error => {
                    // handle error
                    console.log(error);
                    alert('Error occured please try again!');
                });
    }

    doBill(id) {
        DostavaAxios.post('/racuni', id)
        .then(res => {
            // handle success
            console.log(res);
           
            alert('Bill was added successfully!');
            this.props.history.push('/movies');
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

    pretraga() {
        this.getNaruzbine(0)
    }

    vidiRacun(id) {
        this.props.history.push("/orders/racuni/" + id);
    }

    doDelete(id) {
        DostavaAxios.delete('/orders/' + id)
        .then(res => {
            // handle success
            console.log(res);
            alert('Narudzbina je uspesno obrisana!');
            window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    // valueInputChanged(e) {
    //     let input = e.target;
    
    //     let name = input.name;
    //     let value = input.value;
    
    //     let linija = this.state.linija;
    //     linija[name] = value;
    
    //     this.setState({ linija: linija });
    //   }

      pretragaInputChange(e) {
        let control = e.target;

        let name = control.name;
        let value = control.value;
    
        let pretraga = this.state.pretraga;
        pretraga[name] = value;
    
        this.setState({ pretraga: pretraga });
        this.pretraga();
      }

    //  dostavljaciSelectionChanged(e){
    //     // console.log(e);

    //     let prevoznikId = e.target.value;
    //     let prevoznik = this.state.dostavljaci.find((dostavljac) => dostavljac.id == deliveryId);

    //     let linija = this.state.linija;
    //     linija.prevoznik = prevoznik;

    //     this.setState({linija: linija});
    // }


    render() {
        return(
            <div>
                <Form style={{marginTop:35}}>
                <Form.Group>
                    <Form.Label>Mesto isporuke</Form.Label>
                    <Form.Control
                      onChange={(e) => this.pretragaInputChange(e)}
                      name="address"
                      as="input"
                    ></Form.Control>
                  </Form.Group>
                  <Form.Group>
                    <Form.Label>Dostavljac</Form.Label>
                    <Form.Control
                      onChange={(e) => this.pretragaInputChange(e)}
                      name="deliveryId"
                      value={this.state.pretraga.deliveryId}
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
                  </Form>

                  <Table bordered striped style={{ marginTop: 5 }}>
                    <thead className="thead-dark">
                        <tr>
                        <th>Broj narudzbine</th>
                        <th>Datum</th>
                        <th>Mesto isporuke</th>
                        <th>Cena</th>
                        <th>Opis</th>
                        <th>Dostavljac</th>
                        <th colSpan={2}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.orders.map((narudzbina) => {
                        return (
                            <tr key={narudzbina.id}>
                            <td>{narudzbina.number}</td>
                            <td>{narudzbina.date}</td>
                            <td>{narudzbina.address}</td>
                            <td>{narudzbina.price}</td>
                            <td>{narudzbina.description}</td>
                            <td>{narudzbina.deliveryName}</td>
                            <td>
                            <Button
                                variant="danger"
                                onClick={() => this.doDelete(narudzbina.id)}
                                style={{ marginLeft: 5 }}
                                >
                                Delete
                             </Button>
                             <Button
                                variant="info"
                                onClick={() => this.doBill(narudzbina.id)}
                                style={{ marginLeft: 5 }}
                                >
                                Kreiraj racun
                             </Button>
                             <Button
                                disabled={narudzbina.billId == null} 
                                variant="warning"
                                onClick={() => this.vidiRacun(narudzbina.billId)}
                                style={{ marginLeft: 5 }}
                                >
                                Vidi racun
                            </Button>
                            </td>
                            </tr>
                          );
                        })}
                    </tbody>
                 </Table>
                 <ButtonGroup style={{ marginTop: 25 }}>
                    <Button 
                        disabled={this.state.pageNo==0} onClick={()=>this.getNaruzbine(this.state.pageNo-1)}>
                        Previous
                    </Button>
                    <Button
                        disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getNaruzbine(this.state.pageNo+1)}>
                        Next
                    </Button>
                    </ButtonGroup>
            </div>
        )
    }
}

export default Narudzbine;