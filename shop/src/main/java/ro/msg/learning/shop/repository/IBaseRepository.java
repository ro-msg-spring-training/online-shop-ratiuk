package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.msg.learning.shop.model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface IBaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends CrudRepository<T, ID> {
}
