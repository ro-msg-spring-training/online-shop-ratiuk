package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exception.NoSuchProductException;
import ro.msg.learning.shop.exception.OutOfStockException;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocation implements ILocationStrategy {
    private IStockRepository stockRepository;
    private IProductRepository productRepository;

    @Override
    public List<StockDTO> findLocation(OrderDTO orderDTO) {
        Map<Long, List<StockDTO>> locations = new HashMap<>();

        orderDTO.getProductQuantityDTOS().forEach(product -> {
            try{
                List<Stock> stocks = stockRepository.findByProductAndQuantity(productRepository.findById(product.getId()).get(), product.getQuantity());
                if (stocks.isEmpty()) {
                    throw new OutOfStockException(product.getId());
                }

                stocks.forEach(stock -> {
                    List<StockDTO> locationList = locations.get(stock.getLocation().getId());
                    if (locationList == null) {
                        locationList = new ArrayList<>();
                    }

                    locationList.add(new StockDTO(stock.getLocation(), stock.getProduct(), stock.getQuantity()));
                    locations.put(stock.getLocation().getId(), locationList);
                });

            } catch (NoSuchElementException e) {
                throw new NoSuchProductException(product.getId());
            }
        });

        for (Map.Entry<Long, List<StockDTO>> entry : locations.entrySet()) {
            if (entry.getValue().size() == orderDTO.getProductQuantityDTOS().size()) {
                return entry.getValue();
            }
        }

        return new ArrayList<>();
    }
}

