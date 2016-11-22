package starter.pack.core.service.interfaces;

import starter.pack.core.model.custom.account.CreateAccount;
import starter.pack.core.model.data.Account;

import java.util.List;

public interface IAccountService {
    Account getById(Long accountId);

    List<Account> getAll();

    Account createAccount(CreateAccount createAccount);
}
