package starter.pack.core.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starter.pack.core.model.data.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
}
