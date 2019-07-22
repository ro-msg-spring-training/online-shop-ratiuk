package ro.msg.learning.shop.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
public class Address extends BaseEntity<Long> {

    @Column(nullable=false)
    private String addressCountry;

    @Column(nullable=false)
    private String addressCity;

    @Column(nullable=false)
    private String addressCounty;

    @Column(nullable=false)
    private String addressStreet;
}
