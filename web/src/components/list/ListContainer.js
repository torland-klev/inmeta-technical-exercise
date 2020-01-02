import React, { Component } from 'react';
import ListHeader from './ListHeader.js';
import ListBody from './ListBody.js';
import ListFooter from './ListFooter.js';
import './List.css';

export default class ListContainer extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Container",
    };
  }

  render(){
    return(
      <div className="ListContainer">
        <ListHeader />
        <ListBody />
        <ListFooter />
      </div>
    );
  }
}
