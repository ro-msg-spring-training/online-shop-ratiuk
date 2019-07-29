package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail extends BaseEntity<Long> {
    private static final long serialVersionUID = -4313671845001698037L;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    @Column(nullable=false)
    private Integer quantity;

}

