package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity<Long> {
    private static final long serialVersionUID = -1142328902050727961L;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "shippedFrom", referencedColumnName = "id")
    private Location shippedFrom;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;
}
