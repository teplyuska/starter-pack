package starter.pack.core.repository;

import org.springframework.stereotype.Repository;
import starter.pack.core.interfaces.repository.IAccountRepository;
import starter.pack.core.model.data.Account;

@Repository
public class AccountRepository extends BaseRepository<Account> implements IAccountRepository {
    public AccountRepository() {
        super(Account.class);
    }
}
