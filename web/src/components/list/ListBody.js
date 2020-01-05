import React, { Component } from 'react';
import './List.css';
import ListElement from './ListElement.js';

export default class ListBody extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Body",
      listElements: [],
      sort: 'desc'
    };
  }

  componentDidMount(){
    this.updateOrders();
  }

  componentDidUpdate(){
    if (this.props.sort !== this.state.sort){
      this.setState({sort: this.props.sort});
      this.updateOrders();
    }
  }

  updateOrders(){
    const orders = this.props.data.orders;
    var listElements = [];
    orders.forEach( (item, index) => {
      const newElement = <ListElement key={item.orderId} data={item} />
      listElements.push(newElement);
    });
    listElements.sort((item1, item2) => {
      if (this.state.sort === "desc"){
        return item1.key - item2.key;
      }
      return item2.key - item1.key;
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
