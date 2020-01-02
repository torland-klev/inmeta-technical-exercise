import React, { Component } from 'react';
import './Banner.css';
import { TextButton } from '../';

export default class ButtonContainer extends Component {

  constructor(props){
    super(props);
    this.buttons = this.props.buttons.map( (text, index) =>
      <TextButton text={text} onClick={() => (window.location.href = this.props.onClick[index])} key={index}/>
    );
  }

  render(){
    return(
      <div className="ButtonContainer">
        {this.buttons}
      </div>
    );
  }
}
