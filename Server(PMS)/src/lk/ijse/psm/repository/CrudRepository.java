package lk.ijse.psm.repository;

import java.util.List;

public interface CrudRepository<T, ID> extends SuperRepsitory {

    public boolean add(T entity) throws Exception;

    public boolean upate(T entity) throws Exception;

    public boolean delete(T entity) throws Exception;

    public T search(ID id) throws Exception;

    public List<T> getAll() throws Exception;

}
