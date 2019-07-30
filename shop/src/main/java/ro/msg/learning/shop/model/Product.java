package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity<Long> {

    private static final long serialVersionUID = -4016633629061013846L;
    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ProductCategory.class)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Supplier.class)
    @JoinColumn(name = "supplier", referencedColumnName = "id")
    private Supplier supplier;

    @Column
    private String imageURL;
}
