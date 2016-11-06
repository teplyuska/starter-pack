package starter.pack.core.repository;

import org.springframework.stereotype.Repository;
import starter.pack.core.interfaces.repository.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked"})
@Repository
public abstract class BaseRepository<T> implements IRepository<T> {
    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;

    public BaseRepository(Class<T> clazz){
        this.clazz = clazz;
    }

    public <T> T get(Long id){
        return em.find((Class<T>) clazz, id);
    }

    public List<T> getAll(){
        return em.createQuery("from " + clazz.getSimpleName()).getResultList();
    }

    public <T> T add(T entity){
        em.persist(entity);
        return entity;
    }

    protected <T> T firstOrDefault(String predicate, Object... parameters){
        TypedQuery<T> query = em.createQuery("from "+ clazz.getSimpleName() + " t where t." + predicate, (Class<T>) clazz);

        for(int i = 1; i <= parameters.length; i++){
            query.setParameter(i, parameters[i-1]);
        }

        List<T> result = query.setMaxResults(1).getResultList();

        return result.size() > 0 ? result.get(0) : null;
    }

    protected List<T> where(String wherePredicate, Object... parameters){
        TypedQuery<T> query = em.createQuery("from "+ clazz.getSimpleName() + " t where t." + wherePredicate, clazz);

        for(int i = 1; i <= parameters.length; i++){
            query.setParameter(i, parameters[i-1]);
        }

        List<T> list = query.getResultList();
        return list == null ? new ArrayList<>() : null;
    }
}