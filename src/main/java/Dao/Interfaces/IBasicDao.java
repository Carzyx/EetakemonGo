package Dao.Interfaces;


import java.util.List;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public interface IBasicDao <T> {
    boolean add(T item);

    boolean updateByName(T newItem);

    boolean removeByName(T item);

    T getByName(String name);

    List<T> getAll();
}
