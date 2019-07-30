package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exception.NoLocationForStocksException;
import ro.msg.learning.shop.exception.OrderCreationException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.ILocationStrategy;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IStockRepository stockRepository;
    private final ILocationStrategy strategy;
    private final IAddressRepository addressRepository;
    private final IOrderDetailRepository orderDetailRepository;

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Address address = addressRepository.findByAddressCountryAndAddressCityAndAddressCountyAndAddressStreet(orderDTO.getDeliveryAddress().getAddressCountry(),
                orderDTO.getDeliveryAddress().getAddressCity(), orderDTO.getDeliveryAddress().getAddressCounty(), orderDTO.getDeliveryAddress().getAddressStreet());

        if (address == null) {
            address = addressRepository.save(orderDTO.getDeliveryAddress());
        }

        List<StockDTO> stockDTOS = strategy.findLocation(orderDTO);

        if (stockDTOS.isEmpty()) {
            throw new NoLocationForStocksException();
        }

        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        Order order = Order.builder().shippedFrom(stockDTOS.get(0).getLocation()).customer(customers.get(0))
                .createdAt(orderDTO.getLocalDateTime()).address(address).build();

        orderRepository.save(order);

        List<ProductQuantityDTO> products = orderDTO.getProductQuantityDTOS();

        stockDTOS.forEach(stock -> {
            for(ProductQuantityDTO orderProduct : products) {
                if (orderProduct.getId().equals(stock.getProduct().getId())) {
                    Integer quantity = stock.getQuantity() - orderProduct.getQuantity();

                    Stock stockToUpdate = stockRepository.findByProductAndLocation(stock.getProduct(), stock.getLocation());
                    stockToUpdate.setQuantity(quantity);
                    //stockRepository.save(stockToUpdate);

                    orderDetailRepository.save(OrderDetail.builder().order(order).product(stock.getProduct()).quantity(orderProduct.getQuantity()).build());
                    break;
                }
            }
        });

        if (!orderRepository.findById(order.getId()).isPresent()) {
            throw new OrderCreationException();
        }

        return order;
    }
}
