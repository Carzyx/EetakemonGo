package Controller.Interfaces;
import java.util.List;
/**
 * Created by Miguel Angel on 03/05/2017.
 */
public interface IBasicService <T> {

    boolean create(T item);

    boolean removeById(T item);

    boolean updateById(T newItem);

    T getById(int id);

    List<T> getAll();

}
