package Controller;

import Dao.IUserDao;
import Model.User;
import com.mysql.jdbc.StringUtils;

import java.util.Hashtable;

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
            return _service.add(user);
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


    public boolean upadteUser(User user, String oldname) {
        return _serviceUser.updateById(user, oldname);
    }

    public boolean deleteUser(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return false;
        }

        User user = _service.getUserByUsernameAndPassword(username, password);
        return _service.removeById(user);
    }



    public User signIn(String username, String password) {
        if (StringUtils.isEmptyOrWhitespaceOnly(username) || StringUtils
            .isEmptyOrWhitespaceOnly(password)) {
            return null;
        }

        return _service.getUserByUsernameAndPassword(username, password);
    }


    private boolean isUsernameAlreadyInUse(String username) {

        return StringUtils.isEmptyOrWhitespaceOnly(username) || _service
            .isUsernameAlreadyInUse(username);
    }

    private boolean isEmailAlreadyInUse(String email) {

        return StringUtils.isEmptyOrWhitespaceOnly(email) || _service.isEmailAlreadyInUse(email);
    }
}
