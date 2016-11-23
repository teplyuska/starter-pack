package starter.pack.core.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import starter.pack.core.model.data.AccountAuthLog;

public interface IAccountAuthLogRepository extends JpaRepository<AccountAuthLog, Long> {
}
