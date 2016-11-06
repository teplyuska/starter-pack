package starter.pack.shared.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

public class BaseMapper {
    protected ModelMapper mapper;

    public BaseMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(new MatchingStrategies().STRICT);
    }

    public void validate() {
        mapper.validate();
    }

    public <T> T map(Object o, Class<T> c) {
        return mapper.map(o, c);
    }

    public <T> List<T> mapList(List<?> l, Class<T> c) {
        return l.stream().map(e -> map(e, c)).collect(Collectors.toList());
    }
}