package starter.pack.core.interfaces.repository;

import java.util.List;

public interface IRepository<T> {
    <T> T get(Long id);
    List<T> getAll();
    <T> T add(T entity);
}
