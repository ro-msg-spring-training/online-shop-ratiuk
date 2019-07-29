package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity<T> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "seq_mytable_sid")
    @Column(updatable = false, nullable = false)
    protected T id;

}
