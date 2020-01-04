package app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import app.model.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

  // Find first
  public Orders findTopByOrderByOrderIdAsc();

  // Find last
  public Orders findTopByOrderByOrderIdDesc();

}
