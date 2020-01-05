import React, { Component } from 'react';
import './OrdersView.css';
import { ListContainer } from '../../components';
import { findAllOrders } from '../../services/FetchHTTP';

export default class OrdersView extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "Orders",
      data: null,
    };
  }

  componentDidMount(){
    findAllOrders((resJson) => {
      this.setState({ data : resJson });
    })
  }

  render(){
    if (this.state.data){
      return(
        <div className="OrdersView">
          <ListContainer data={this.state.data} />
        </div>
      );
    } else {
      return(
        <div className="OrdersView">
          No Data
        </div>
      )
    }
  }
}
