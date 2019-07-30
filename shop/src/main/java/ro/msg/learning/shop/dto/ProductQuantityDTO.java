package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductQuantityDTO extends BaseDTO {
    private static final long serialVersionUID = -5275894465759248255L;
    // the id from the base dto refers to the product id
    private Integer quantity;
}
