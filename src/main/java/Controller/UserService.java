package Controller;

import Dao.IUserDao;
import Model.User;
import com.mysql.jdbc.StringUtils;

import java.util.Hashtable;

/**
 * Created by histo on 07/03/2017.
 */
public class UserService implements IUserService {

    private static IUserDao _service;

    public UserService(IUserDao service) {
        _service = service;
    }

    //Usuario
    @Override
    public boolean createUser(User user) {

        if (!isUsernameAlreadyInUse(user.getUsername()) || !isEmailAlreadyInUse(user.getEmail())) {
            return _service.add(user);
        }
        return false;
    }

    public User getUser(int id) {
        return _service.getUserById(id);
    }

    @Override
    public User signIn(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return null;
        }

        return _service.getUserByUsernameAndPassword(username, password);

    }

    public boolean upadteUser(User user, String oldname) {
        return _service.updateById(user, oldname);
    }

    @Override
    public boolean deleteUser(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return false;
        }

        User user = _service.getUserByUsernameAndPassword(username, password);
        return _service.removeById(user);
    }


    private boolean isUsernameAlreadyInUse(String username) {

        return StringUtils.isEmptyOrWhitespaceOnly(username) || _service
            .isUsernameAlreadyInUse(username);
    }

    private boolean isEmailAlreadyInUse(String email) {

        return StringUtils.isEmptyOrWhitespaceOnly(email) || _service.isEmailAlreadyInUse(email);
    }
}
