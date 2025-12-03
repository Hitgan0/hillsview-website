package learn.data;

import learn.data.mappers.OrderMapper;
import learn.models.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderJdbcTemplateRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderJdbcTemplateRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Order> findAll() {
        final String sql = "select order_id, full_name, address, order_date, total, order_status, cash_on_delivery " +
                "from orders;";

        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order findById(int orderId) {
        return null;
    }

    @Override
    public Order add(Order order) {
        return null;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean deleteById(int orderId) {
        return false;
    }
}
