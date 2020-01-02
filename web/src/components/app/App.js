import React, { Component } from 'react';
import {BrowserRouter, Route } from 'react-router-dom';
import './App.css'
import { APP_NAME } from '../../config';
import { Banner, Footer } from '../';
import SiteImage from '../siteimage/SiteImage';
import { HomeView, OrdersView, CreateOrderView } from '../../views';

export default class App extends Component {
  render(){
    return (
      <div className="Container">
        <SiteImage />
        <Banner text={APP_NAME} buttons={["Orders"]} onClick={["/orders"]}/>
        <BrowserRouter>
          <div className="Body">
            <Route exact path='/' component={HomeView} />
            <Route exact path='/orders' component={OrdersView} />
            <Route exact path='/orders/create' component={CreateOrderView} />
          </div>
        </BrowserRouter>
        <Footer />
      </div>
    );
  }
}
