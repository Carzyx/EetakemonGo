package DAL.Dao.Interfaces;

import Model.User;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public interface IUserDao extends IBasicDao<User> {

    boolean removeByUsernameAndPassword(User user);

    boolean updateByUsernameAndPassword(User user);

    boolean addAEetakemonsToUser(User user);

    boolean removeEetakemonsToUser(User user);

    User getUserByUsernameAndPassword(String username, String password);

    User getCompleteUserByUsername(String name);

    boolean isUsernameAlreadyInUse(String username);

    boolean isEmailAlreadyInUse(String email);

}
