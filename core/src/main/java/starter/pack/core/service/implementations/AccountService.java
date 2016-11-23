package starter.pack.core.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import starter.pack.core.enums.AuthStatusEnum;
import starter.pack.core.model.data.AccountAuthLog;
import starter.pack.core.model.service.accountauthlog.CreateAccountAuthLog;
import starter.pack.core.model.service.auth.AuthenticationResult;
import starter.pack.core.repository.interfaces.IAccountAuthLogRepository;
import starter.pack.core.repository.interfaces.IAccountRepository;
import starter.pack.core.service.interfaces.IAccountService;
import starter.pack.core.mapper.IEntityMapper;
import starter.pack.core.model.service.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.shared.interfaces.IDateUtil;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    private IAccountRepository accountRepository;
    private IAccountAuthLogRepository accountAuthLogRepository;
    private IEntityMapper mapper;
    private IDateUtil dateUtil;

    private static final int LOG_ROUNDS = 12;

    @Autowired
    public AccountService(IAccountRepository accountRepository, IAccountAuthLogRepository accountAuthLogRepository, IEntityMapper mapper, IDateUtil dateUtil) {
        this.accountRepository = accountRepository;
        this.accountAuthLogRepository = accountAuthLogRepository;
        this.mapper = mapper;
        this.dateUtil = dateUtil;
    }

    @Override
    public Account getById(Long accountId) {
        return accountRepository.findOne(accountId);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(CreateAccount createAccount) {
        Account newAccount = mapper.map(createAccount, Account.class);

        String passwordHash = BCrypt.hashpw(createAccount.getPassword(), BCrypt.gensalt(LOG_ROUNDS));
        newAccount.setPasswordHash(passwordHash);
        newAccount.setCreatedDate(dateUtil.getNow());

        return accountRepository.save(newAccount);
    }

    @Override
    public void createAccountAuthLog(CreateAccountAuthLog createAccountAuthLog) {
        AccountAuthLog accountAuthLog = mapper.map(createAccountAuthLog, AccountAuthLog.class);

        accountAuthLog.setCreatedDate(dateUtil.getNow());

        accountAuthLogRepository.save(accountAuthLog);
    }

    @Override
    public AuthenticationResult authenticateAccount(String email, String password, String ipAddress) {
        Account account = accountRepository.findByEmail(email);

        AuthenticationResult authenticationResult = new AuthenticationResult();
        if (account != null) {
            CreateAccountAuthLog createAccountAuthLog = new CreateAccountAuthLog();
            createAccountAuthLog.setIpAddress(ipAddress);
            createAccountAuthLog.setAccountId(account.getId());

            if (BCrypt.checkpw(password, account.getPasswordHash())) {
                createAccountAuthLog.setStatus(AuthStatusEnum.SUCCESS);
            } else {
                createAccountAuthLog.setStatus(AuthStatusEnum.WRONG_PASSWORD);
            }

            this.createAccountAuthLog(createAccountAuthLog);

            authenticationResult.setAccount(account);
            authenticationResult.setAuthStatus(createAccountAuthLog.getStatus());
        } else {
            authenticationResult.setAuthStatus(AuthStatusEnum.ACCOUNT_NOT_FOUND);
        }

        return authenticationResult;
    }
}
