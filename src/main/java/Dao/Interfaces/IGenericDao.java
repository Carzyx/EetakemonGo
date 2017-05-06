package Dao.Interfaces;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 05/04/2017.
 */
public interface IGenericDao<T>  {

    boolean add(T t);

    boolean updateById(T t);

    boolean removeById(T t);

    boolean removeByConditions(T t, Hashtable<String, String> conditions);

    T getByParameters(T t, Hashtable<String, String> conditions);

    List<T> getAllByParameters(T t, Hashtable<String, String> conditions);
}
