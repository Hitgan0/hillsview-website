package learn.data;

import learn.data.mappers.OrderMapper;
import learn.models.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderJdbcTemplateRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderJdbcTemplateRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Order> findAll() {
        final String sql = "select order_id, full_name, address, email, order_date, total, order_status, cash_on_delivery " +
                "from orders;";

        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order findById(int orderId) {
        final String sql = "select order_id, full_name, address, email, order_date, total, order_status, cash_on_delivery " +
                "from orders " +
                "where order_id = ?;";

        return jdbcTemplate.query(sql, new OrderMapper(), orderId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Order add(Order order) {
        final String sql = "insert into orders (full_name, address, email, order_date, total, order_status, cash_on_delivery) " +
                "values(?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getFullName());
            ps.setString(2, order.getAddress());
            ps.setString(3, order.getEmail());
            ps.setObject(4, order.getOrderDate());
            ps.setBigDecimal(5, order.getTotal());
            ps.setString(6, order.getStatus().name());
            ps.setBoolean(7, order.isCashOnDelivery());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        order.setOrderId(keyHolder.getKey().intValue());
        return order;
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
