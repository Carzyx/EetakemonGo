package Controller;

import Dao.Interfaces.IUserDao;
import Model.User;
import com.mysql.jdbc.StringUtils;

import java.util.List;

/**
 * Created by histo on 07/03/2017.
 */
public class UserService implements IUserService {

    private static IUserDao _serviceUser;

    public UserService(IUserDao serviceUser) {
        _serviceUser = serviceUser;
    }

    //Usuario
    public boolean create(User user) {

        if (!isUsernameAlreadyInUse(user.getUsername()) || !isEmailAlreadyInUse(user.getEmail())) {
            return _serviceUser.add(user);
        }
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public boolean updateById(int id, User item) {
        return false;
    }

    public User getById(int id) {
        return _serviceUser.getUserById(id);
    }

    @Override
    public List<User> getAll() {
        return _serviceUser.getAll();
    }


    public boolean deleteUser(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return false;
        }

        User user = _serviceUser.getUserByUsernameAndPassword(username, password);
        return _serviceUser.removeById(user);
    }



    public User signIn(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return null;
        }

        return _serviceUser.getUserByUsernameAndPassword(username, password);
    }

    private boolean isUsernameAlreadyInUse(String username) {

        return StringUtils.isEmptyOrWhitespaceOnly(username) || _serviceUser
            .isUsernameAlreadyInUse(username);
    }

    private boolean isEmailAlreadyInUse(String email) {

        return StringUtils.isEmptyOrWhitespaceOnly(email) || _serviceUser.isEmailAlreadyInUse(email);
    }
}
