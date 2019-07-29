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
public class Address extends BaseEntity<Long> {

    private static final long serialVersionUID = 1669488363484228500L;
    @Column(nullable=false)
    private String addressCountry;

    @Column(nullable=false)
    private String addressCity;

    @Column(nullable=false)
    private String addressCounty;

    @Column(nullable=false)
    private String addressStreet;
}
