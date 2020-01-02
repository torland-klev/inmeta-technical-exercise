import React, { Component } from 'react';
import './Button.css';

export default class TextButton extends Component{
  constructor() {
    super();
    this.state = {
      title: "Button",
    }
  };

  render(){

    const fromProps = (this.props.text) ? this.props.text : this.state.time;
    const text = (this.props.bold) ? <b>{fromProps}</b> : fromProps;

    return(
      <div onClick={this.props.onClick} className="TextButton" style={this.props.style}>
        {text}
      </div>
    );
  }
}
