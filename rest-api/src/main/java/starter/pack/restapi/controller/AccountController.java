package starter.pack.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import starter.pack.core.model.data.AccountAuthLog;
import starter.pack.core.service.interfaces.IAccountService;
import starter.pack.core.model.service.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.restapi.mapper.IDTOMapper;
import starter.pack.restapi.model.dto.account.AccountAuthLogDTO;
import starter.pack.restapi.model.dto.account.AccountDTO;
import starter.pack.restapi.model.dto.account.CreateAccountDTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("account")
@Transactional
public class AccountController {
    private IAccountService accountService;
    private IDTOMapper mapper;

    @Autowired
    public AccountController(IAccountService accountService, IDTOMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountDTO> create(@RequestBody CreateAccountDTO newAccount) {
        CreateAccount createAccount = mapper.map(newAccount, CreateAccount.class);
        Account account = accountService.createAccount(createAccount);
        AccountDTO mappedAccount = mapper.map(account, AccountDTO.class);
        return new ResponseEntity<>(mappedAccount, HttpStatus.OK);
    }

    @RequestMapping(value = "{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountDTO> getById(@PathVariable Long accountId) {
        Account account = accountService.getById(accountId);
        AccountDTO mappedAccount = mapper.map(account, AccountDTO.class);
        return new ResponseEntity<>(mappedAccount, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccountDTO>> getAll() {
        List<Account> accounts = accountService.getAll();
        List<AccountDTO> mappedAccounts = mapper.mapList(accounts, AccountDTO.class);
        return new ResponseEntity<>(mappedAccounts, HttpStatus.OK);
    }

    @RequestMapping(value = "{accountId}/authlog", method = RequestMethod.GET)
    public ResponseEntity<List<AccountAuthLogDTO>> getAccountAuthLog(@PathVariable Long accountId) {
        Account account = accountService.getById(accountId);
        List<AccountAuthLog> accountAuthLogs = account.getAccountAuthLogs().stream()
                .sorted(Comparator.comparing(AccountAuthLog::getCreatedDate).reversed())
                .collect(Collectors.toList());

        List<AccountAuthLogDTO> mappedAccountAuthLogs = mapper.mapList(accountAuthLogs, AccountAuthLogDTO.class);
        return new ResponseEntity<>(mappedAccountAuthLogs, HttpStatus.OK);
    }
}
