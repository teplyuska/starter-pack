package starter.pack.core.mapper;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import starter.pack.core.model.data.AccountAuthLog;
import starter.pack.core.model.service.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.core.model.service.accountauthlog.CreateAccountAuthLog;
import starter.pack.shared.mapper.BaseMapper;

@Component
public class EntityMapper extends BaseMapper implements IEntityMapper {
    public EntityMapper() {
        super();
        this.setupAccountMappings();
        this.setupAccountAuthLogMappings();
    }

    private void setupAccountMappings() {
        this.mapper.addMappings(new PropertyMap<CreateAccount, Account>() {
            @Override
            protected void configure() {
                skip().setId(null);
                skip().setCreatedDate(null);
                skip().setAccountAuthLogs(null);
                skip().setPasswordHash(null);
            }
        });
    }

    private void setupAccountAuthLogMappings() {
        this.mapper.addMappings(new PropertyMap<CreateAccountAuthLog, AccountAuthLog>() {
            @Override
            protected void configure() {
                skip().setId(null);
                skip().setCreatedDate(null);
                skip().setAccount(null);
            }
        });
    }
}
