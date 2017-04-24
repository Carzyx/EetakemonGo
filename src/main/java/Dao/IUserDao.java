package Dao;

import Model.User;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public interface IUserDao {

    boolean add(User user);
    boolean updateById(User user,String oldName);
    boolean removeById(User user);
    List<User> getAll() throws Exception;
    User getUserById(int user);
    User getUserByUsernameAndPassword(String username, String password);
    boolean isUsernameAlreadyInUse(String username);
    boolean isEmailAlreadyInUse(String email);

}
