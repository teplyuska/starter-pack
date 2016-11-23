package starter.pack.restapi.mapper;

import org.springframework.stereotype.Component;
import starter.pack.core.model.data.AccountAuthLog;
import starter.pack.core.model.service.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.restapi.model.dto.account.AccountAuthLogDTO;
import starter.pack.restapi.model.dto.account.AccountDTO;
import starter.pack.restapi.model.dto.account.CreateAccountDTO;
import starter.pack.shared.mapper.BaseMapper;

@Component
public class DTOMapper extends BaseMapper implements IDTOMapper {
    public DTOMapper() {
        super();
        this.setupAccountMappings();
        this.setupAccountAuthLogMappings();
    }

    private void setupAccountMappings() {
        this.mapper.createTypeMap(Account.class, AccountDTO.class);
        this.mapper.createTypeMap(CreateAccountDTO.class, CreateAccount.class);
    }

    private void setupAccountAuthLogMappings() {
        this.mapper.createTypeMap(AccountAuthLog.class, AccountAuthLogDTO.class);
    }
}
