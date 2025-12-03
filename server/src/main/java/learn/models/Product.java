package learn.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productName.equals(product.productName) && price.compareTo(product.price) == 0 && quantity == product.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, price, quantity);
    }
}
