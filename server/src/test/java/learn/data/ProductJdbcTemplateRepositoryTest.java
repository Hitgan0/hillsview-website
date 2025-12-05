package learn.data;

import learn.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductJdbcTemplateRepositoryTest {
    int NEXT_ID = 4;

    @Autowired
    ProductJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindAll() {
        List<Product> products = repository.findAll();
        assertNotNull(products);
        assertEquals(3, products.size());
    }

    @Test
    void shouldFindById() {
        Product product = repository.findById(1);
        assertEquals(1, product.getProductId());
        assertEquals("Apple", product.getProductName());
        assertEquals(BigDecimal.valueOf(5.50).setScale(2, RoundingMode.HALF_UP), product.getPrice());
    }

    @Test
    void shouldAdd() {
        Product product = makeProduct();
        Product actual = repository.add(product);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getProductId());
    }

    @Test
    void shouldUpdate() {
        Product product = makeProduct();
        product.setProductId(2);
        assertTrue(repository.update(product));
        product.setProductId(999);
        assertFalse(repository.update(product));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }

    private Product makeProduct() {
        Product product = new Product();
        product.setProductName("Grape");
        product.setPrice(BigDecimal.valueOf(8.8));
        product.setQuantity(80);

        return product;
    }
}