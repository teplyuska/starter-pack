package starter.pack.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import starter.pack.core.interfaces.repository.IAccountRepository;
import starter.pack.core.interfaces.service.IAccountService;
import starter.pack.core.mapper.IEntityMapper;
import starter.pack.core.model.custom.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.shared.interfaces.IDateUtil;

import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {
    private IAccountRepository accountRepository;
    private IEntityMapper mapper;
    private IDateUtil dateUtil;

    @Autowired
    public AccountService(IAccountRepository accountRepository, IEntityMapper mapper, IDateUtil dateUtil) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
        this.dateUtil = dateUtil;
    }

    @Override
    public Account getById(Long accountId) {
        return accountRepository.get(accountId);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    @Override
    public Account createAccount(CreateAccount createAccount) {
        Account newAccount = mapper.map(createAccount, Account.class);

        newAccount.setCreatedDate(dateUtil.getNow());

        return accountRepository.add(newAccount);
    }
}
