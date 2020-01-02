
const TARGET = "http://localhost:9090";

const createOrder = (formData) => {
  fetch(TARGET + '/orders', {
     method: 'post',
     body: formData
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    if(resJson.id){
      alert("Order (id=" + resJson.id +") created successfully.");
    } else {
      alert("Error occured.");
    }
    return resJson;
   });
};

const findOrder = (id) => {
  fetch(TARGET + '/orders?id=' + id, {
    method: 'get'
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    return resJson;
  });
}

const editOrder = (id, formData) => {
  fetch(TARGET + '/orders?id=' + id, {
    method: 'put',
    body: formData
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    return resJson;
  });
}

const deleteOrder = (id) => {
  fetch(TARGET + '/orders?id=' + id, {
    method: 'delete'
  })
  .then(res => res.json())
  .then(resJson => {
    console.log(resJson);
    return resJson;
  });
}

export {
  createOrder,
  findOrder,
  editOrder,
  deleteOrder
};
