import React, { Component } from 'react';
import './CreateOrderView.css';
import { createOrder, editOrder } from '../../services/FetchHTTP';
import { TextButton } from '../../components';



export default class CreateOrderView extends Component {
  constructor(props){
    super(props);
    this.state = {
      title: "Create Order"
    };
  }

  create(){
    const name = document.getElementById("name").value;
    const number = document.getElementById("number").value;
    const addressFrom = document.getElementById("addressFrom").value;
    const addressTo = document.getElementById("addressTo").value;
    const date = document.getElementById("date").value;
    const comment = document.getElementById("comment").value;
    const email = document.getElementById("email").value;
    const moving = document.getElementById("moving").checked;
    const packing = document.getElementById("packing").checked;
    const cleaning = document.getElementById("cleaning").checked;
    const service = (moving) ?  "moving" : ( (packing) ? "packing" : ((cleaning) ? "cleaning" : "error"));
    const data = {
      "name": name,
      "number": number,
      "addressFrom": addressFrom,
      "addressTo": addressTo,
      "date": date,
      "comment": comment,
      "service": service,
      "email": email
    };
    console.log(data);
    createOrder(data, (ret) => {
      console.log(ret);
    });
  }

  edit(){
    const name = document.getElementById("name").value;
    const number = document.getElementById("number").value;
    const addressFrom = document.getElementById("addressFrom").value;
    const addressTo = document.getElementById("addressTo").value;
    const date = document.getElementById("date").value;
    const comment = document.getElementById("comment").value;
    const email = document.getElementById("email").value;
    const moving = document.getElementById("moving").checked;
    const packing = document.getElementById("packing").checked;
    const cleaning = document.getElementById("cleaning").checked;
    const service = (moving) ?  "moving" : ( (packing) ? "packing" : ((cleaning) ? "cleaning" : "error"));
    const data = {
      "name": name,
      "number": number,
      "addressFrom": addressFrom,
      "addressTo": addressTo,
      "date": date,
      "comment": comment,
      "service": service,
      "email": email
    };
    console.log(data);
    editOrder(this.props.location.props.data.orderId, data, (ret) => {
      try{
        // If this does'nt error, the fetch was successful.
        const check = ret.order.orderId;
        window.alert("Update to order "+check+ " successful.");
      } catch{
        window.alert("Something went wrong.");
      }
    });
  }

  render(){
    var data = null;
    if(this.props.location.props){
      data = this.props.location.props.data;
    }
    if (data){
      return(
        <div className="CreateOrderContainerParent">
          <div className="CreateOrderContainer">
            <h4>Name</h4>
            <input type="text" name="name" id="name" autoComplete="name" defaultValue={data.name}/>
            <h4>Phone number</h4>
            <input type="text" name="number" id="number" autoComplete="phone" defaultValue={data.mobile}/>
            <h4>Email</h4>
            <input type="text" name="email" id="email" autoComplete="tel" defaultValue={data.email}/>
            <h4>Address from</h4>
            <input type="text" name="addressFrom" id="addressFrom" autoComplete="address-line1" defaultValue={data.addressFrom}/>
            <h4>Address to</h4>
            <input type="text" name="addressTo" id="addressTo" autoComplete="address-line2" defaultValue={data.addressTo}/>
            <div className="ServiceType">
              <input type="radio" name="serviceType" value="moving" id="moving" defaultChecked={data.service === "moving"}/> Moving
              <input type="radio" name="serviceType" value="cleaning" id="cleaning" defaultChecked={data.service === "cleaning"}/> Cleaning
              <input type="radio" name="serviceType" value="packing" id="packing" defaultChecked={data.service === "packing"}/> Packing
            </div>
            <h4>Service date</h4>
            <input type="date" name="date" id="date" defaultValue={data.date}/>
            <h4>Comment</h4>
            <input type="text" name="comment" id="comment" defaultValue={data.comment}/>
            <TextButton text="Submit Order" onClick={() => this.edit()} style={{border: "2px solid black", borderRadius: 15, padding: 5, paddingLeft: 15, paddingRight: 15, marginTop: 25 }}/>
          </div>
        </div>
      );
    }else {
      return(
        <div className="CreateOrderContainerParent">
          <div className="CreateOrderContainer">
            <h4>Name</h4>
            <input type="text" name="name" id="name" autoComplete="name"/>
            <h4>Phone number</h4>
            <input type="text" name="number" id="number" autoComplete="phone"/>
            <h4>Email</h4>
            <input type="text" name="email" id="email" autoComplete="tel"/>
            <h4>Address from</h4>
            <input type="text" name="addressFrom" id="addressFrom" autoComplete="address-line1"/>
            <h4>Address to</h4>
            <input type="text" name="addressTo" id="addressTo" autoComplete="address-line2"/>
            <div className="ServiceType">
              <input type="radio" name="serviceType" value="moving" id="moving" /> Moving
              <input type="radio" name="serviceType" value="cleaning" id="cleaning" /> Cleaning
              <input type="radio" name="serviceType" value="packing" id="packing" /> Packing
            </div>
            <h4>Service date</h4>
            <input type="date" name="date" id="date"/>
            <h4>Comment</h4>
            <input type="text" name="comment" id="comment" />
            <TextButton text="Submit Order" onClick={() => this.create()} style={{border: "2px solid black", borderRadius: 15, padding: 5, paddingLeft: 15, paddingRight: 15, marginTop: 25 }}/>
          </div>
        </div>
      )
    }
  }
}
