package starter.pack.restapi.mapper;

import org.junit.Test;

public class DTOMapperTest {
    @Test
    public void validateMappingTest() {
        IDTOMapper mapper = new DTOMapper();
        mapper.validate();
    }
}
