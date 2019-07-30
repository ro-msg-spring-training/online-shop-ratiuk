package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderDTO {
    private LocalDateTime localDateTime;
    private Address deliveryAddress;
    private List<ProductQuantityDTO> productQuantityDTOS;
}
