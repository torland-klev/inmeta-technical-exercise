import React, { Component } from 'react';
import './HomeView.css';

export default class HomeView extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "Home",
    };
  }

  render(){
    return(
      <div className="HomeView">{this.state.title}</div>
    );
  }
}
