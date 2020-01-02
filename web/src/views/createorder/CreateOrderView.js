import React, { Component } from 'react';
import './CreateOrderView.css';
import { createOrder } from '../../services/FetchHTTP';

export default class CreateOrderView extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "Create Order"
    };
  }

  render(){
    return(
      <div className="CreateOrderContainer">
        {this.state.title}
      </div>
    )
  }
}
