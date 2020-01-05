import React, { Component } from 'react';
import './List.css';
import ListElement from './ListElement.js';

export default class ListBody extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Body",
      listElements: [],
    };
  }

  componentDidMount(){
    const orders = this.props.data.orders;
    var listElements = [];
    orders.forEach( (item, index) => {
      const newElement = <ListElement key={item.orderId} data={item} />
      listElements.push(newElement);
    });
    this.setState({ listElements: listElements });

  }

  render(){
    return(
      <div className="ListBodyParent">
        <div className="ListBody">
          {this.state.listElements}
        </div>
      </div>
    );
  }
}
