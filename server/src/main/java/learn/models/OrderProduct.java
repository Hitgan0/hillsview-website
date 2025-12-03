package learn.models;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderProduct {
    private int orderProductId;
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal priceAtPurchase;

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct op = (OrderProduct) o;
        return orderProductId == op.orderProductId && orderId == op.orderId && productId == op.productId && quantity == op.quantity && priceAtPurchase.compareTo(op.priceAtPurchase) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderProductId, orderId, productId, quantity, priceAtPurchase);
    }
}
