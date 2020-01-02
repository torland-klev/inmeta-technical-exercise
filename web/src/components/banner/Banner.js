import React, { Component } from 'react';
import './Banner.css';
import { TextButton } from '../';
import { ButtonContainer } from './';

export default class Banner extends Component{

  render(){
    return(
      <div className="Banner">
        <TextButton bold text={this.props.text} onClick={() => (window.location.href = '/')} style={{fontSize: '30px'}}/>
        {this.props.title}
        <ButtonContainer buttons={this.props.buttons} onClick={this.props.onClick}/>
      </div>
    );
  }
}
