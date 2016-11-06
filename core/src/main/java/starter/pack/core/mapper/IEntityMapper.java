package starter.pack.core.mapper;

import java.util.List;

public interface IEntityMapper {
    void validate();
    <T> T map(Object o, Class<T> c);
    <T> List<T> mapList(List<?> l, Class<T> c);
}
