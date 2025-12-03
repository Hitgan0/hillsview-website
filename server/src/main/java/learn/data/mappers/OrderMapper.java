package learn.data.mappers;

import learn.models.Order;
import learn.models.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setFullName(rs.getString("full_name"));
        order.setAddress(rs.getString("address"));
        order.setEmail(rs.getString("email"));
        order.setOrderDate(rs.getDate("order_date").toLocalDate());
        order.setTotal(rs.getBigDecimal("total"));

        Status status = Status.valueOf(rs.getString("order_status").toUpperCase());
        order.setStatus(status);

        order.setCashOnDelivery(rs.getBoolean("cash_on_delivery"));

        return order;
    }
}
