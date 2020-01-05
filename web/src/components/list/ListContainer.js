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
      sort: 'desc'
    };
  }

  handleSort = (sortType) => {
    this.setState({sort: sortType});
  }

  render(){
    return(
      <div className="ListContainer">
        <ListHeader onSortChange={(sortType) => this.handleSort(sortType)} />
        <ListBody data={this.props.data} sort={this.state.sort}/>
        <ListFooter />
      </div>
    );
  }
}
