import React, { Component } from 'react';
import './ViewOrderView.css';
import { TextButton, ItemPair } from '../../components';
import { deleteOrder } from '../../services/FetchHTTP';
import {Link} from 'react-router-dom';

export default class ViewOrderView extends Component {
  constructor(props){
    super(props);
    this.state = {
      data: null,
    }
  }

  componentDidMount(){
    const props = this.props.location.props;
    if (props){
      this.setState({data: props.data});
    }
  }

  delete(){
    if(window.confirm("Are you sure? Press 'OK' to delete, 'Cancel' to cancel.")){
      deleteOrder(this.state.data.orderId, (res) => {
        window.alert(res.message);
        window.location.href = '/orders';
      });
    } else {
      window.alert("Delete cancelled");
    }
  }

  render(){
    if (this.state.data){
      return(
        <div className="ViewOrderContainerParent">
          <div className="ViewOrderContainer">
            <ItemPair first={<h4>Order ID</h4>} second={<h4>{this.state.data.orderId}</h4>} />
            <ItemPair first={<h4>Name</h4>} second={<h4>{this.state.data.name}</h4>} />
            <ItemPair first={<h4>Mobile</h4>} second={<h4>{this.state.data.mobile}</h4>} />
            <ItemPair first={<h4>Address From</h4>} second={<h4>{this.state.data.addressFrom}</h4>} />
            <ItemPair first={<h4>Address To</h4>} second={<h4>{this.state.data.addressTo}</h4>} />
            <ItemPair first={<h4>Service</h4>} second={<h4>{this.state.data.service}</h4>} />
            <ItemPair first={<h4>Date</h4>} second={<h4>{this.state.data.date}</h4>} />
            <ItemPair first={<h4>Comment</h4>} second={<h4>{this.state.data.comment}</h4>} />
            <ItemPair first={<h4>Last Updated</h4>} second={<h4>{this.state.data.lastUpdated}</h4>} />
            <ItemPair first={<h4>Created</h4>} second={<h4>{this.state.data.created}</h4>} />

            <ItemPair
              style={{maxWidth: "40%", minWidth: 260}}
              first={<TextButton text="Delete Order"
                onClick={() => this.delete()}
                style={{border: "2px solid black", borderRadius: 15, padding: 5, paddingLeft: 15, paddingRight: 15, marginTop: 25, minWidth: 100 }}/>}
              second={
                <Link to={{pathname: "/orders/create", props:{data: this.state.data}}} style={{textDecoration: "none", color: "inherit"}}>
                  <TextButton text="Edit Order"
                    style={{border: "2px solid black", borderRadius: 15, padding: 5, paddingLeft: 15, paddingRight: 15, marginTop: 25, minWidth: 100 }}/>
                </Link>}
              />
          </div>
        </div>
      )
    }
    return(
      <div className="ViewOrderView">
        Something went wrong.
      </div>
    )
  }
}
