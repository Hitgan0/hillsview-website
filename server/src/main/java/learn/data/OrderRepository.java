package learn.data;

import learn.models.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();

    Order findById(int orderId);

    Order add(Order order);

    boolean update(Order order);

    boolean deleteById(int orderId);
}
