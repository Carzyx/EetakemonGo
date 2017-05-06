package Dao;

import Dao.Entity.UserDto;
import Dao.Interfaces.IGenericDao;
import Dao.Interfaces.IUserDao;
import Model.User;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class UserDao implements IUserDao {

    private static IGenericDao<UserDto> _service;

    private ModelMapper modelMapper = new ModelMapper();

    public UserDao(IGenericDao<UserDto> service) {
        _service = service;
    }

    public boolean add(User user) {

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return _service.add(userDto);
    }

    public boolean updateById(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return _service.updateById(userDto);
    }

    public boolean removeById(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return _service.removeById(userDto);
    }

    public List<User> getAll() {

        List<UserDto> responseList = _service.getAllByParameters(new UserDto(), null);
        List<User> result = new ArrayList<>();
        for(UserDto response : responseList)
        {
            result.add(modelMapper.map(response, User.class));
        }
        return result;
    }


    public User getUserByUsernameAndPassword(String username, String password) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", username);
        conditions.put("password", password);
        return modelMapper.map(_service.getByParameter(userDto, conditions), User.class);
    }

    public User getUserById(int id) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("id", Integer.toString(id));
        return modelMapper.map(_service.getByParameter(userDto, conditions), User.class);
    }

    public boolean isUsernameAlreadyInUse(String username) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", username);
        List<UserDto> resultUsers = _service.getAllByParameters(userDto, conditions);
        return resultUsers.size() > 0;
    }

    public boolean isEmailAlreadyInUse(String email) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("email", email);
        List<UserDto> resultUsers = _service.getAllByParameters(userDto, conditions);
        return resultUsers.size() > 0;
    }

}
