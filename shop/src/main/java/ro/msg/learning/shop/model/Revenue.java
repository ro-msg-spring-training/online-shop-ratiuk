package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Revenue extends BaseEntity<Long> {
    private static final long serialVersionUID = 4293339017252453058L;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal sum;
}
