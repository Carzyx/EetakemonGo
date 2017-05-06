package Controller.Interfaces;

import Model.User;

/**
 * Created by histo on 07/03/2017.
 */
public interface IUserService extends IBasicService <User> {

    User signIn(String username, String password);


}
