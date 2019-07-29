package ro.msg.learning.shop.integration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exception.OutOfStockException;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@ActiveProfiles("test")
@Sql(scripts="classpath:cleanup.sql",executionPhase=Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts="classpath:cleanup.sql",executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderCreationTest extends TestBase {

    @Autowired
    private OrderService orderService;

    @Autowired
    private IProductCategoryRepository productCategoryRepository;

    @Autowired
    private ISupplierRepository supplierRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private ILocationRepository locationRepository;

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Before
    public void init() {
        productCategoryRepository.save(productCategory);
        supplierRepository.save(supplier);
        productRepository.save(product);
        productRepository.save(product2);
        addressRepository.save(address);
        locationRepository.save(location);
        locationRepository.save(location2);
        stockRepository.save(stock);
        stockRepository.save(stock2);
        stockRepository.save(stock3);
        customerRepository.save(customer);
    }

    @Test
    public void successCreatingOrder() throws JsonProcessingException {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        ProductQuantityDTO productQuantityDTO = new ProductQuantityDTO(1);
        productQuantityDTO.setId(product.getId());
        lst.add(productQuantityDTO);
        ProductQuantityDTO productQuantityDTO2 = new ProductQuantityDTO(10);
        productQuantityDTO2.setId(product2.getId());
        lst.add(productQuantityDTO2);

        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(), address, lst);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(orderDTO);

        System.out.println(jsonString);

        Order order = orderService.createOrder(orderDTO);

        Assert.assertNotNull(order);
    }

    @Test(expected = OutOfStockException.class)
    public void failCreatingOrder() {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        ProductQuantityDTO productQuantityDTO2 = new ProductQuantityDTO(100);
        productQuantityDTO2.setId(product2.getId());
        lst.add(productQuantityDTO2);
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now(), address, lst);
        orderService.createOrder(orderDTO);
    }
}
