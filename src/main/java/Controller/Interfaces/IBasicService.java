package Controller.Interfaces;
import java.util.List;
/**
 * Created by Miguel Angel on 03/05/2017.
 */
public interface IBasicService <T> {

    boolean create(T item);

    boolean removeByName(T item);

    boolean updateByName(T newItem);

    T getByName(String name);

    List<T> getAll();

}
