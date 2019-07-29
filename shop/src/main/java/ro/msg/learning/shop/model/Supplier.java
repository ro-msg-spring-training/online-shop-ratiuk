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
public class Supplier extends BaseEntity<Long> {

    private static final long serialVersionUID = 3358994168900782335L;
    @Column(nullable = false)
    private String name;
}
