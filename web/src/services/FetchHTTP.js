
const TARGET = "http://localhost:9090";

const createOrder = (data, callback) => {
  fetch(TARGET + '/orders', {
     method: 'post',
     headers: {
      'Content-Type': 'application/json'
     },
     body: JSON.stringify(data)
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    if(resJson.orderId){
      alert("Order (id=" + resJson.orderId +") created successfully.");
    } else {
      alert("Error occured.");
    }
    callback(resJson);
   });
};

const findOrder = (id, callback) => {
  fetch(TARGET + '/orders?id=' + id, {
    method: 'get'
  })
  .then(res => res.json())
  .then(resJson => {
    callback(resJson);
  });
}

const editOrder = (id, data, callback) => {
  fetch(TARGET + '/orders?id=' + id, {
    method: 'put',
    headers: {
     'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    callback(resJson);
  });
}

const deleteOrder = (id, callback) => {
  fetch(TARGET + '/orders?id=' + id, {
    method: 'delete'
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    callback(resJson);
  });
}

export {
  createOrder,
  findOrder,
  editOrder,
  deleteOrder
};
