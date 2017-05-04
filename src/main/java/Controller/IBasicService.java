package Controller;
import java.util.List;
/**
 * Created by Miguel Angel on 03/05/2017.
 */
public interface IBasicService <T> {

    boolean create(T item);

    boolean removeById(int id);

    boolean updateById(int id, T item);

    T getById(int id);

    List<T> getAll();

}
