package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location extends BaseEntity<Long> {
    private static final long serialVersionUID = -7359926675202645885L;
    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;
}
