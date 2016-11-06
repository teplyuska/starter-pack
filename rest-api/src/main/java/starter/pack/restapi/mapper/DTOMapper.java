package starter.pack.restapi.mapper;

import org.springframework.stereotype.Component;
import starter.pack.core.model.custom.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.restapi.model.dto.account.AccountDTO;
import starter.pack.restapi.model.dto.account.CreateAccountDTO;
import starter.pack.shared.mapper.BaseMapper;

@Component
public class DTOMapper extends BaseMapper implements IDTOMapper {
    public DTOMapper() {
        super();
        setupAccountMappings();
    }

    private void setupAccountMappings() {
        this.mapper.createTypeMap(Account.class, AccountDTO.class);
        this.mapper.createTypeMap(CreateAccountDTO.class, CreateAccount.class);
    }
}
