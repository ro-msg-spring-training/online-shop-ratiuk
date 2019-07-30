package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.strategy.ILocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundantLocation;
import ro.msg.learning.shop.strategy.SingleLocation;

@Configuration
@AllArgsConstructor
public class LocationConfiguration {
    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;

    private enum Strategy {
        SINGLE, ABUNDANT
    }

    @Bean
    public ILocationStrategy locationStrategy(@Value("${location.strategy}") Strategy strategy) {
        switch (strategy) {
            case SINGLE:
                return new SingleLocation(stockRepository, productRepository);
            case ABUNDANT:
                return new MostAbundantLocation(stockRepository, productRepository);
            default:
                throw new IllegalStateException();
        }
    }
}
