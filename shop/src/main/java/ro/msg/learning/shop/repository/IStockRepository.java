package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface IStockRepository extends IBaseRepository<Stock, Long> {
    @Query(value = "SELECT s FROM Stock s WHERE s.product = :product AND s.quantity >= :quantity ORDER BY s.quantity DESC")
    List<Stock> findByProductAndQuantity(Product product, Integer quantity);

    Stock findByProductAndLocation(Product product, Location location);
}
