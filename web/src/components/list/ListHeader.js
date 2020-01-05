import React, { Component } from 'react';
import './List.css';

export default class ListHeader extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "List Header",
      sort: "desc",
    };
  }

  handleSortChange = () => {
    const sort = this.state.sort;
    const newSort = (sort === "desc") ? "asc" : "desc";
    this.setState({sort: newSort});
    this.props.onSortChange(newSort);
  }

  render(){
    return(
      <div className="ListHeader">
        <div onClick={() => this.handleSortChange() } style={{cursor: "pointer"}}>
          Sort by ID {this.state.sort}
        </div>
      </div>
    );
  }
}
