package starter.pack.core.test.mapper;

import org.junit.Test;
import starter.pack.core.mapper.EntityMapper;
import starter.pack.core.mapper.IEntityMapper;

public class EntityMapperTest {
    @Test
    public void validateMappingTest() {
        IEntityMapper mapper = new EntityMapper();
        mapper.validate();
    }
}
