import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrderUtilsTest
{
    @Test
    void calculateDiscountPerProduct_Ideal()
    {
        int totalDiscount = 100;
        List<Product> products = Arrays.asList(
                new Product("product1", 500),
                new Product("product2", 1500)
        );

        Map<Product, Integer> discounts = OrderUtils.calculateDiscountPerProduct(totalDiscount, products);
        assertEquals(25, discounts.get(products.get(0)));
        assertEquals(75, discounts.get(products.get(1)));
    }

    @Test
    void calculateDiscountPerProduct_NonIdeal()
    {
        int totalDiscount = 102;
        List<Product> products = Arrays.asList(
                new Product("product1", 500),
                new Product("product2", 1500)
        );

        Map<Product, Integer> discounts = OrderUtils.calculateDiscountPerProduct(totalDiscount, products);
        assertEquals(25, discounts.get(products.get(0)));
        assertEquals(77, discounts.get(products.get(1)));
    }

    @Test
    void calculateDiscountPerProduct_DiscountLessThenNumOfProducts()
    {
        int totalDiscount = 2;
        List<Product> products = Arrays.asList(
                new Product("product1", 500),
                new Product("product2", 500),
                new Product("product3", 500)
        );

        Map<Product, Integer> discounts = OrderUtils.calculateDiscountPerProduct(totalDiscount, products);
        assertEquals(0, discounts.get(products.get(0)));
        assertEquals(0, discounts.get(products.get(1)));
        assertEquals(2, discounts.get(products.get(2)));
    }

    @Test
    void calculateDiscountPerProduct_InvalidDiscount()
    {
        int totalDiscount = 21;
        List<Product> products = Arrays.asList(
                new Product("product1", 10),
                new Product("product2", 10)
        );

        assertThrows(IllegalArgumentException.class, () -> OrderUtils.calculateDiscountPerProduct(totalDiscount, products));
        assertThrows(IllegalArgumentException.class, () -> OrderUtils.calculateDiscountPerProduct(-1, products));
    }

    @Test
    void calculateDiscountPerProduct_InvalidProducts()
    {
        int totalDiscount = 100;
        List<Product> products = Arrays.asList(
                new Product("product1", 10),
                new Product("product2", 10),
                new Product("product3", 10),
                new Product("product4", 10),
                new Product("product5", 10),
                new Product("product6", 10)
        );

        assertThrows(IllegalArgumentException.class, () -> OrderUtils.calculateDiscountPerProduct(totalDiscount, products));
        assertThrows(IllegalArgumentException.class, () -> OrderUtils.calculateDiscountPerProduct(totalDiscount, new ArrayList<>()));
        assertThrows(NullPointerException.class, () -> OrderUtils.calculateDiscountPerProduct(totalDiscount, null));
    }
}