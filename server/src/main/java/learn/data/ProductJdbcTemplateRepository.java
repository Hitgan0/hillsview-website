package learn.data;

import learn.data.mappers.ProductMapper;
import learn.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ProductJdbcTemplateRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Product> findAll() {
        final String sql = "select product_id, product_name, price, quantity " +
                "from products;";

        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product findById(int productId) {
        final String sql = "select product_id, product_name, price, quantity " +
                "from products " +
                "where product_id = ?;";

        return jdbcTemplate.query(sql, new ProductMapper(), productId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Product add(Product product) {
        final String sql = "insert into products (product_name, price, quantity) " +
                "values(?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        product.setProductId(keyHolder.getKey().intValue());
        return product;
    }

    @Override
    public boolean update(Product product) {
        final String sql = "update products set "
                + "product_name = ?, "
                + "price = ?, "
                + "quantity = ? "
                + "where product_id = ?;";

        return jdbcTemplate.update(sql,
                product.getProductName(),
                product.getPrice(),
                product.getQuantity(),
                product.getProductId()) > 0;
    }

    @Override
    public boolean deleteById(int productId) {
        return jdbcTemplate.update("delete from products where product_id = ?;", productId) > 0;
    }
}
