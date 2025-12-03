package learn.data;

import learn.models.Order;
import learn.models.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderJdbcTemplateRepositoryTest {
    int NEXT_ID = 5;

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

    @Test
    void shouldAdd() {
        Order order = makeOrder();
        Order actual = repository.add(order);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getOrderId());

        System.out.println(actual.getOrderId());
        System.out.println(actual.getFullName());
    }

    private Order makeOrder() {
        Order order = new Order();
        order.setFullName("Princess Diana");
        order.setAddress("555 5th st, Town, NY, 55555");
        order.setEmail("diana@email.com");
        order.setOrderDate(LocalDate.of(2005, 5, 5));
        order.setTotal(BigDecimal.valueOf(20));
        order.setStatus(Status.SHIPPED);
        order.setCashOnDelivery(false);

        return order;
    }
}