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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
public class MostAbundantLocation implements ILocationStrategy {
    private IStockRepository stockRepository;
    private IProductRepository productRepository;

    @Override
    public List<StockDTO> findLocation(OrderDTO orderDTO) {
        List<StockDTO> stockLocation = new ArrayList<>();
        orderDTO.getProductQuantityDTOS().forEach(product -> {
            try {
                List<Stock> stocks = stockRepository.findByProductAndQuantity(productRepository.findById(product.getId()).get(), product.getQuantity());
                if (stocks.isEmpty()) {
                    throw new OutOfStockException(product.getId());
                }
                Stock stock = stocks.get(0);
                stockLocation.add(new StockDTO(stock.getLocation(), stock.getProduct(), stock.getQuantity()));
            } catch (NoSuchElementException e) {
                throw new NoSuchProductException(product.getId());
            }
        });

        return stockLocation;
    }
}

