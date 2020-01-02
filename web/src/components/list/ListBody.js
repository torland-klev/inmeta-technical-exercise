import React, { Component } from 'react';
import './List.css';

export default class ListBody extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Body",
    };
  }

  render(){
    return(
      <div className="ListBody">{this.state.title}</div>
    );
  }
}
