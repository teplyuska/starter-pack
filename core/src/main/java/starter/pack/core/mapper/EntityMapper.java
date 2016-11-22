package starter.pack.core.mapper;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import starter.pack.core.model.custom.account.CreateAccount;
import starter.pack.core.model.data.Account;
import starter.pack.shared.mapper.BaseMapper;

@Component
public class EntityMapper extends BaseMapper implements IEntityMapper {
    public EntityMapper() {
        super();
        setupAccountMappings();
    }

    private void setupAccountMappings() {
        this.mapper.addMappings(new PropertyMap<CreateAccount, Account>() {
            @Override
            protected void configure() {
                skip().setId(null);
                skip().setCreatedDate(null);
            }
        });
    }
}
