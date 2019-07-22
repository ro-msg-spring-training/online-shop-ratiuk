package ro.msg.learning.shop.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class Revenue extends BaseEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal sum;
}
