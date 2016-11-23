package starter.pack.core.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import starter.pack.core.model.data.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}