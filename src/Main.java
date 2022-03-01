import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // example data
        int totalDiscount = 100;
        List<Product> products = Arrays.asList(
                new Product("product1", 500),
                new Product("product2", 1500)
        );

        // get discounts
        Map<Product, Integer> productDiscounts = OrderUtils.calculateDiscountPerProduct(totalDiscount, products);

        // print input and results
        System.out.println("Total discount = " + totalDiscount);
        for (Product product : products)
        {
            System.out.println(product);
        }
        System.out.println();
        for (Map.Entry<Product, Integer> productDiscount : productDiscounts.entrySet())
        {
            System.out.println(productDiscount.getKey().getName() + " discount = " + productDiscount.getValue());
        }
    }
}
