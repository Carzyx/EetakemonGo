package DAL.Dao.Interfaces;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 05/04/2017.
 */
public interface IGenericDao<T> {

    boolean add(T t);

    boolean updateByConditions(T t, Hashtable<String, String> conditions);

    boolean removeByConditions(T t, Hashtable<String, String> conditions);

    T get(T t);

    List<T> getAll(T t);

    T getByParameters(T t, Hashtable<String, String> conditions);

    List<T> getAllByParameters(T t, Hashtable<String, String> conditions);


}
