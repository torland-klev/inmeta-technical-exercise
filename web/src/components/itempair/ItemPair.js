import React, { Component } from 'react';
import './ItemPair.css';

export default class ItemPair extends Component {

  render(){
    return(
      <div className="Item" style={this.props.style}>
        <div className="SubItem1">
          {this.props.first}
        </div>
        <div className="SubItem2">
          {this.props.second}
        </div>
      </div>
    );
  }
}
