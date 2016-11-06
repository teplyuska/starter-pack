package starter.pack.restapi.mapper;

import java.util.List;

public interface IDTOMapper {
    void validate();
    <T> T map(Object o, Class<T> c);
    <T> List<T> mapList(List<?> l, Class<T> c);
}
