package servises.interfaces;

public interface IPersonServices<T> {
    T getById(long id);

    T getByUsername(String username);

    T findByLogin(T person);

    void add(T person);

    void delete(long id);

    void update(T entity);
}
