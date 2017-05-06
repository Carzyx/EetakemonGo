package Dao;

import Dao.Entity.EetakemonsUserDto;
import Dao.Entity.UserDto;
import Dao.Interfaces.IEetakemonDao;
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
    private static IEetakemonDao _serviceEetakemon;


    private ModelMapper modelMapper = new ModelMapper();

    public UserDao() {
        _service = new GenericDaoImpl<>();
        _serviceEetakemonsUser = new GenericDaoImpl<>();
        _serviceEetakemon = new EetakemonDao();
    }

    public boolean add(User user) {

        UserDto userDto = modelMapper.map(user, UserDto.class);
        boolean userConfirmation = _service.add(userDto);

        if(user.getEetakemons() == null || !(user.getEetakemons().size() <= 0) || !userConfirmation)
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
        boolean userConfirmation = _service.removeById(userDto);
        if(!(user.getEetakemons().size() <= 0) || !userConfirmation)
        {
            return userConfirmation;
        }

        return removeEetakemonsToUser(user);
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

    public User getCompleteUserById(int id) {
        User user = getById(id);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("idUser", Integer.toString(user.getId()));
        List<EetakemonsUserDto> result = _serviceEetakemonsUser.getAllByParameters(new EetakemonsUserDto(), conditions);

        user.setEetakemons(_serviceEetakemon.getAllCompleteEetakemonById(result));

        return user;
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

    public boolean removeEetakemonsToUser(User user) {
        boolean actionResult = true;
        for (Eetakemon eetakemon : user.getEetakemons())
        {
            Hashtable<String, String> conditions = new Hashtable<>();
            conditions.put("idUser", Integer.toString(user.getId()));
            conditions.put("idEetakemon", Integer.toString(eetakemon.getId()));
            EetakemonsUserDto eetakemonsUserDto = new EetakemonsUserDto(user.getId(), eetakemon.getId());
            boolean result = _serviceEetakemonsUser.removeByConditions(eetakemonsUserDto, conditions);
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
