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
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.strategy.SingleLocation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SingleLocationTest extends TestBase {
    @Mock
    private IProductRepository productRepository;
    @Mock
    private IStockRepository stockRepository;
    @InjectMocks
    private SingleLocation strategy;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        setIds();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
        when(stockRepository.findByProductAndQuantity(product, 1)).thenReturn(stocks);
    }

    @Test
    public void strategyTestSuccess() {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        ProductQuantityDTO productQuantityDTO = new ProductQuantityDTO(1);
        productQuantityDTO.setId(1L);
        lst.add(productQuantityDTO);

        Address address = Address.builder().addressCountry("Germanyy").addressCounty("Bayernn").addressCity("Munchenn").addressStreet("Neuburger 22").build();
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(), address, lst);
        List<StockDTO> stocks = strategy.findLocation(orderDTO);

        Assert.assertFalse(stocks.isEmpty());
    }

    @Test(expected = OutOfStockException.class)
    public void strategyTestFail() {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        ProductQuantityDTO productQuantityDTO = new ProductQuantityDTO(100);
        productQuantityDTO.setId(1L);
        lst.add(productQuantityDTO);
        ProductQuantityDTO productQuantityDTO2 = new ProductQuantityDTO(100);
        productQuantityDTO2.setId(1L);
        lst.add(productQuantityDTO2);

        Address address = Address.builder().addressCountry("Germanyy").addressCounty("Bayernn").addressCity("Munchenn").addressStreet("Neuburger 22").build();
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(), address, lst);

        strategy.findLocation(orderDTO);
    }
}
