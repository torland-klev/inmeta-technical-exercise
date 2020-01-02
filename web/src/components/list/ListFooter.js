import React, { Component } from 'react';
import './List.css';
import { TextButton } from '../';

export default class ListFooter extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Footer",
    };
  }

  render(){
    return(
      <div className="ListFooter">
        <TextButton text="Create New Order" onClick={() => (window.location.href = '/orders/create')} style={{border: "2px solid black", borderRadius: 15, padding: 5, paddingLeft: 15, paddingRight: 15 }}/>
      </div>
    );
  }
}
