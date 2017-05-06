package Dao.Interfaces;

import Model.EetakemonsUser;
import Model.User;

import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public interface IUserDao extends IBasicDao <User>{

    User getUserByUsernameAndPassword(String username, String password);

    boolean isUsernameAlreadyInUse(String username);

    boolean isEmailAlreadyInUse(String email);

}
