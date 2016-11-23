package starter.pack.core.service.interfaces;

import starter.pack.core.model.service.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.core.model.service.accountauthlog.CreateAccountAuthLog;
import starter.pack.core.model.service.auth.AuthenticationResult;

import java.util.List;

public interface IAccountService {
    Account getById(Long accountId);

    List<Account> getAll();

    Account createAccount(CreateAccount createAccount);

    void createAccountAuthLog(CreateAccountAuthLog createAccountAuthLog);

    AuthenticationResult authenticateAccount(String email, String password, String ipAddress);
}
