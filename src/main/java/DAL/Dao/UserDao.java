package DAL.Dao;

import DAL.EntityDataBase.EetakemonsUserDto;
import DAL.EntityDataBase.UserDto;
import DAL.Dao.Interfaces.IEetakemonDao;
import DAL.Dao.Interfaces.IGenericDao;
import DAL.Dao.Interfaces.IUserDao;
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

        if(!userConfirmation || user.getEetakemons() == null || !(user.getEetakemons().size() > 0) )
        {
            return userConfirmation;
        }

        return addAEetakemonsToUser(user);
    }

    public boolean updateByName(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username",user.getUsername());
        return _service.updateByConditions(userDto, conditions);
    }

    public boolean updateByUsernameAndPassword(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", user.getUsername());
        conditions.put("password", user.getPassword());
        return _service.updateByConditions(userDto, conditions);
    }

    public boolean updateByNameAndPassword(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", user.getUsername());
        conditions.put("password", user.getPassword());
        return _service.updateByConditions(userDto, conditions);
    }


    public boolean removeByName(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", user.getUsername());
        boolean userConfirmation = _service.removeByConditions(userDto, conditions);
        if( !(user.getEetakemons() == null || user.getEetakemons().size() <= 0) || !userConfirmation)
        {
            return userConfirmation;
        }

        return removeEetakemonsToUser(user);
    }

    public boolean removeByUsernameAndPassword(User user) {
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", user.getUsername());
        conditions.put("password", user.getPassword());

        UserDto userDto = modelMapper.map(user, UserDto.class);
        boolean userConfirmation = _service.removeByConditions(userDto, conditions);
        if((user.getEetakemons() == null || user.getEetakemons().size() <= 0) || !userConfirmation)
        {
            return userConfirmation;
        }

        return removeEetakemonsToUser(user);
    }

    public User getByName(String namer) {
        UserDto userDto = new UserDto();
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", namer);
        return modelMapper.map(_service.getByParameters(userDto, conditions), User.class);
    }

    public List<User> getAll() {

        List<UserDto> responseList = _service.getAll(new UserDto());
        List<User> result = new ArrayList<>();
        for(UserDto response : responseList)
        {
            result.add(modelMapper.map(response, User.class));
        }
        return result;
    }

    public User getCompleteUserByUsername(String username) {
        User user = getByName(username);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("username", user.getUsername());
        List<EetakemonsUserDto> result = _serviceEetakemonsUser.getAllByParameters(new EetakemonsUserDto(), conditions);
        if(result != null)
        {
            user.setEetakemons(_serviceEetakemon.getAllCompleteEetakemonByName(result));
        }

        return user;
    }

    public boolean addAEetakemonsToUser(User user) {
        boolean actionResult = true;
        boolean eetakemonAdded=false;
        if(isValidEetakemonToUser(user)) {
            Hashtable<String, String> conditions = new Hashtable<>();
            conditions.put("username", user.getUsername());
            List<EetakemonsUserDto> result = _serviceEetakemonsUser.getAllByParameters(new EetakemonsUserDto(), conditions);
            for (Eetakemon eetakemon : user.getEetakemons()) {
                for (EetakemonsUserDto myeetakemon:result) {
                    if(eetakemon.getName().equals(myeetakemon.getEetakemonName())){
                        myeetakemon.setLevel(myeetakemon.getLevel()+1);
                        conditions.put("username",myeetakemon.getUsername());
                        conditions.put("eetakemonName",myeetakemon.getEetakemonName());
                        eetakemonAdded = _serviceEetakemonsUser.updateByConditions(myeetakemon,conditions);
                    if (!eetakemonAdded) {
                        actionResult = false;
                        }
                    }
                }
                if(!eetakemonAdded){
                    if(eetakemon.getLevel()==0)
                        eetakemon.setLevel(1);
                    EetakemonsUserDto eetakemonsUserDto=new EetakemonsUserDto(user.getUsername(),eetakemon.getName(),eetakemon.getLevel());
                    _serviceEetakemonsUser.add(eetakemonsUserDto);
                }
                if (!eetakemonAdded)
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
            conditions.put("username", user.getUsername());
            conditions.put("eetakemonName", eetakemon.getName());
            EetakemonsUserDto eetakemonsUserDto = new EetakemonsUserDto(user.getUsername(), eetakemon.getName());
            boolean relationRemoved = _serviceEetakemonsUser.removeByConditions(eetakemonsUserDto, conditions);
            if(!relationRemoved)
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
        User user = modelMapper.map(_service.getByParameters(userDto, conditions), User.class);

        conditions.put("username", user.getUsername());
        List<EetakemonsUserDto> result = _serviceEetakemonsUser.getAllByParameters(new EetakemonsUserDto(), conditions);

        if(result != null)
        {
            user.setEetakemons(_serviceEetakemon.getAllCompleteEetakemonByName(result));
        }
        //TODO Object response that contains Token and succes.
        return user;
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

    private boolean isValidEetakemonToUser(User user) {
        if(user.getEetakemons().size() > 0)
        {
            for (Eetakemon eetakemon : user.getEetakemons())
            {
                if(eetakemon.getAtacks().size() != 4)
                {
                    return false;
                }
            }
        }
        return true;
    }

}
