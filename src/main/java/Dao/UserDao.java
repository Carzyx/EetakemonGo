package Dao;

import Dao.Entity.EetakemonsUserDto;
import Dao.Entity.UserDto;
import Dao.Interfaces.IGenericDao;
import Dao.Interfaces.IUserDao;
import Model.Eetakemon;
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
    private static IGenericDao<EetakemonsUserDto> _serviceEetakemonsUser;


    private ModelMapper modelMapper = new ModelMapper();

    public UserDao(IGenericDao<UserDto> service) {
        _service = service;
    }

    public boolean add(User user) {

        UserDto userDto = modelMapper.map(user, UserDto.class);
        boolean userConfirmation = _service.add(userDto);

        if(!(user.getEetakemons().size() <= 0) || !userConfirmation)
        {
            return userConfirmation;
        }

        return addAEetakemonsToUser(user);
    }

    public boolean updateById(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return _service.updateById(userDto);
    }

    public boolean removeById(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return _service.removeById(userDto);
    }

    public User getById(int id) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("id", Integer.toString(id));
        return modelMapper.map(_service.getByParameters(userDto, conditions), User.class);
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





    public boolean addAEetakemonsToUser(User user) {
        boolean actionResult = true;
        for (Eetakemon eetakemon : user.getEetakemons())
        {
            EetakemonsUserDto eetakemonsUserDto = new EetakemonsUserDto(user.getId(), eetakemon.getId());
            boolean result = _serviceEetakemonsUser.add(eetakemonsUserDto);
            if(result == false)
            {
                actionResult = false;
            }
        }
        return actionResult;
    }






    public User getUserByUsernameAndPassword(String username, String password) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", username);
        conditions.put("password", password);
        return modelMapper.map(_service.getByParameters(userDto, conditions), User.class);
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
