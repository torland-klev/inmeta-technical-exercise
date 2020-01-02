import React, { Component } from 'react';
import './OrdersView.css';
import { ListContainer } from '../../components';

export default class OrdersView extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "Orders"
    };
  }

  render(){
    return(
      <div className="OrdersView">
        <ListContainer />
      </div>
    );
  }
}
