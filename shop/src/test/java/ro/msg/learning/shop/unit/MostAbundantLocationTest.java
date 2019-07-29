package ro.msg.learning.shop.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exception.OutOfStockException;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.strategy.MostAbundantLocation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MostAbundantLocationTest extends TestBase {
        @Mock
        private IProductRepository productRepository;
        @Mock
        private IStockRepository stockRepository;
        @InjectMocks
        private MostAbundantLocation strategy;

        @Before
        public void init() {
            MockitoAnnotations.initMocks(this);
            setIds();
            when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
            when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
            when(stockRepository.findByProductAndQuantity(product, 1)).thenReturn(stocksProd);
            when(stockRepository.findByProductAndQuantity(product, 101)).thenThrow(OutOfStockException.class);
            when(stockRepository.findByProductAndQuantity(product, 99)).thenReturn(stocksProdBigStock);
            when(stockRepository.findByProductAndQuantity(product2, 1)).thenReturn(stocksProd2);
            when(stockRepository.findAll()).thenReturn(stocks);
        }

        @Test
        public void strategyTestSuccess() {
            List<ProductQuantityDTO> productQuantityDTOS = new ArrayList<>();
            ProductQuantityDTO productQuantityDTO = new ProductQuantityDTO(1);
            productQuantityDTO.setId(1L);
            productQuantityDTOS.add(productQuantityDTO);

            OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(), address, productQuantityDTOS);
            List<StockDTO> stocks = strategy.findLocation(orderDTO);


            Assert.assertFalse(stocks.isEmpty());
            Assert.assertEquals(1, stocks.size());
            Assert.assertEquals("MUC2", stocks.get(0).getLocation().getName());

            productQuantityDTO.setQuantity(99);
            stocks = strategy.findLocation(orderDTO);

            Assert.assertFalse(stocks.isEmpty());
            Assert.assertEquals(1, stocks.size());
            Assert.assertEquals("MUC2", stocks.get(0).getLocation().getName());

            productQuantityDTO.setId(2L);
            productQuantityDTO.setQuantity(1);
            stocks = strategy.findLocation(orderDTO);

            Assert.assertFalse(stocks.isEmpty());
            Assert.assertEquals(1, stocks.size());
            Assert.assertEquals("MUC", stocks.get(0).getLocation().getName());
        }


        @Test(expected = OutOfStockException.class)
        public void strategyTestFail() {
            List<ProductQuantityDTO> productQuantityDTOS = new ArrayList<>();
            ProductQuantityDTO productQuantityDTO = new ProductQuantityDTO(101);
            productQuantityDTO.setId(1L);
            productQuantityDTOS.add(productQuantityDTO);

            OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(), address, productQuantityDTOS);

            strategy.findLocation(orderDTO);
        }
}
