package starter.pack.restapi.test.mapper;

import org.junit.Test;
import starter.pack.restapi.mapper.DTOMapper;
import starter.pack.restapi.mapper.IDTOMapper;

public class DTOMapperTest {
    @Test
    public void validateMappingTest() {
        IDTOMapper mapper = new DTOMapper();
        mapper.validate();
    }
}
