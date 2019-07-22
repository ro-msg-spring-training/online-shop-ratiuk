package ro.msg.learning.shop.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
public class Supplier extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;
}
