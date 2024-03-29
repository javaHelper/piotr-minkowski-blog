package pl.piomin.services.transactions.repository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.transactions.domain.OrderGroup;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface OrderGroupRepository extends CrudRepository<OrderGroup, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OrderGroup> findById(Long groupId);
}