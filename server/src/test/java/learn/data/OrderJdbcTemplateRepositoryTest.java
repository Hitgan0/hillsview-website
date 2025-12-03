package learn.data;

import learn.models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderJdbcTemplateRepositoryTest {
    @Autowired
    OrderJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindAll() {
        List<Order> orders = repository.findAll();
        assertNotNull(orders);
        assertEquals(4, orders.size());
    }

    @Test
    void shouldFindById() {
        Order order = repository.findById(1);
        assertEquals(1, order.getOrderId());
        assertEquals("John Doe", order.getFullName());
        assertEquals("111 1st st, Town, NY, 11111", order.getAddress());
    }

}