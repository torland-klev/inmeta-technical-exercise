import React, { Component } from 'react';
import './List.css';
import {Link} from 'react-router-dom';

export default class ListElement extends Component {

  render(){

    return(
      <Link className="ListElement" to={{pathname: '/orders/view', props: {data: this.props.data} }} >
          <div>{this.props.data.orderId}</div>
          <div>{this.props.data.name}</div>
      </Link>
    );
  }
}
