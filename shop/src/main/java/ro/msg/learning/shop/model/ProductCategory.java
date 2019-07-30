package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategory extends BaseEntity<Long> {
    private static final long serialVersionUID = 6217105557263612973L;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
}