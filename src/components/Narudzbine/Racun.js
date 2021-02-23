import React from 'react';
import DostavaAxios from './../../apis/DostavaAxios';
import { Table} from "react-bootstrap";


class Racun extends React.Component {
    constructor(props) {
        super(props);
    
        let bill = {
          number: "",
          date: "",
          finalPrice: 0,
          orderId: -1,
        };

        this.state = {bill: bill}
}

componentDidMount() {
    this.getBillById(this.props.match.params.id)
}

getBillById(id) {
    DostavaAxios.get('/racuni/' + id)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({bill: res.data});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    render() {
        return (
        <div>
          <h1>Podaci o racunu</h1>
          <Table bordered striped style={{ marginTop: 5 }}>
          <thead className="thead-dark">
            <tr>
              <th>Broj racuna</th>
              <th>Datum kreiranja</th>
              <th>Konacna cena</th>
              <th>Id narudzbine</th>
            </tr>
          </thead>
          <tbody>
          <tr key={this.state.bill.number}>
                  <td>{this.state.bill.number}</td>
                  <td>{this.state.bill.date}</td>
                  <td>{this.state.bill.finalPrice}</td>
                  <td>{this.state.bill.orderId}</td>
          </tr>
        </tbody>
        </Table>
        </div>
        );
    }
}

export default Racun;