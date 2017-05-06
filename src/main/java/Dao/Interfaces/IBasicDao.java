package Dao.Interfaces;


import java.util.List;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public interface IBasicDao <T> {
    boolean add(T item);

    boolean updateById(T newItem);

    boolean removeById(T item);

    T getUserById(int itemId);

    List<T> getAll();
}
