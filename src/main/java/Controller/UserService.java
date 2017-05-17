package Controller;

import Controller.Interfaces.IUserService;
import Dao.Interfaces.IUserDao;
import Dao.UserDao;
import Model.User;
import com.mysql.jdbc.StringUtils;

import java.util.List;

/**
 * Created by histo on 07/03/2017.
 */
public class UserService implements IUserService {

    private static IUserDao _serviceUser;

    public UserService() {
        _serviceUser = new UserDao();
    }

    public boolean create(User user) {

        if (!isUsernameAlreadyInUse(user.getUsername()) & !isEmailAlreadyInUse(user.getEmail())) {
            return _serviceUser.add(user);
        }
        return false;
    }

    public boolean removeByName(User user) {
        return _serviceUser.removeByName(user);
    }

    public boolean removeByUsernameAndPassword(User user)
    {
        return _serviceUser.removeByUsernameAndPassword(user);
    }

    public boolean updateByName(User newUser) {
        return _serviceUser.updateByName(newUser);
    }

    public boolean updateByUsernameAndPassword(User newUser) {
        return _serviceUser.updateByUsernameAndPassword(newUser);
    }

    public User getByName(String username) {
        return _serviceUser.getByName(username);
    }

    public List<User> getAll() {
        return _serviceUser.getAll();
    }

    public User getCompleteUserByUsername(String username)
    {
       return _serviceUser.getCompleteUserByUsername(username);
    }


    public boolean deleteUser(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return false;
        }

        User user = _serviceUser.getUserByUsernameAndPassword(username, password);
        return _serviceUser.removeByUsernameAndPassword(user);
    }

    public boolean addAEetakemonsToUser(User user)
    {
        return _serviceUser.addAEetakemonsToUser(user);
    }

    public boolean removeEetakemonsToUser(User user) {
    return _serviceUser.removeEetakemonsToUser(user);
}


    public User signIn(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return null;
        }

        return _serviceUser.getUserByUsernameAndPassword(username, password);
    }

    public boolean isUsernameAlreadyInUse(String username) {

        return StringUtils.isEmptyOrWhitespaceOnly(username) || _serviceUser
            .isUsernameAlreadyInUse(username);
    }

    public boolean isEmailAlreadyInUse(String email) {

        return StringUtils.isEmptyOrWhitespaceOnly(email) || _serviceUser.isEmailAlreadyInUse(email);
    }
}
