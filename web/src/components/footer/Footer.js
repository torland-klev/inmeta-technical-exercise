import React, { Component } from 'react';
import './Footer.css';

export default class Footer extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "Footer",
    };
  }

  render(){
    return(
      <div className="Footer">{this.state.title}</div>
    );
  }
}
