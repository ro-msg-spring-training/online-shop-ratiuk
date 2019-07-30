package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
class OrderController {
    private final OrderService ordersService;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        orderDTO.setLocalDateTime(LocalDateTime.now());
        return ordersService.createOrder(orderDTO);
    }

}
