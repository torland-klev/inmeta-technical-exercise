import React, { Component } from 'react';
import './List.css';

export default class ListHeader extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Header",
    };
  }

  render(){
    return(
      <div className="ListHeader">{this.state.title}</div>
    );
  }
}
