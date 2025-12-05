package learn.data;

import learn.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(int productId);

    Product add(Product product);

    boolean update(Product product);

    boolean deleteById(int productId);
}
