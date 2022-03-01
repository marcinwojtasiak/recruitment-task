import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderUtils
{
    /**
     * Calculates a discount for each product proportionally to their price.
     * If a portion of the discount cannot be split perfectly, it will be given to the last product.
     * @param totalDiscount Total discount for the products (cannot be larger than total price of products or less than 0)
     * @param products A list of products (0 < size < 6)
     * @return A map of products and their discounts
     * @throws IllegalArgumentException if size of products is invalid
     * @throws IllegalArgumentException if total discount is larger than the total price of products or less than 0
     * @throws NullPointerException if products is null
     */
    public static Map<Product, Integer> calculateDiscountPerProduct(int totalDiscount, List<Product> products)
    {
        // validate products
        if (products == null)
            throw new NullPointerException();
        if (products.size() > 5 || products.size() < 1)
            throw new IllegalArgumentException();

        int totalPrice = products.stream().mapToInt(Product::getPrice).sum();

        // validate totalDiscount
        if (totalDiscount > totalPrice || totalDiscount < 0)
            throw new IllegalArgumentException();

        HashMap<Product, Integer> result = new HashMap<>();
        int totalDistributed = 0; // discount that have been already distributed

        for (int i=0; i<products.size(); i++)
        {
            Product product = products.get(i);
            // calculate discount for the product
            int discount = (int)(totalDiscount * ((float)product.getPrice() / totalPrice));
            totalDistributed += discount;
            // apply all non-distributed discount to the last product
            if (i == products.size()-1)
            {
                discount += totalDiscount - totalDistributed;
            }
            result.put(product, discount);
        }

        return result;
    }
}
