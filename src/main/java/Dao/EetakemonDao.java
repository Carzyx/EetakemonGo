package Dao;

import Dao.Entity.AtacksEetakemonDto;
import Dao.Entity.EetakemonDto;
import Dao.Entity.EetakemonsUserDto;
import Dao.Interfaces.IAtackDao;
import Dao.Interfaces.IEetakemonDao;
import Dao.Interfaces.IGenericDao;
import Model.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonDao implements IEetakemonDao {

    //Acctions for Eetakemon BBDD
    private static IGenericDao<EetakemonDto> _service;
    private static IGenericDao<AtacksEetakemonDto> _serviceAtackEetakemon;
    private static IAtackDao _serviceAtack;
    private ModelMapper modelMapper = new ModelMapper();

    public EetakemonDao() {
        _service = new GenericDaoImpl<>();
        _serviceAtackEetakemon = new GenericDaoImpl<>();
        _serviceAtack = new AtackDao();
    }

    //TODO -- Validar: si existe Eetakemon, no crearlo (repetir check de UserService/UserDao)
    public boolean add(Eetakemon eetakemon) {

        if(isValidAtackToEetakemon(eetakemon))
        {
            EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
            boolean eetakemonConfirmation = _service.add(eetakemonDto);
            if (eetakemonConfirmation)
            {
                return addAtacksToEetakemon(eetakemon);
            }
        }

        return false;
    }

    public boolean updateById(Eetakemon eetakemon) {
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        return _service.updateById(eetakemonDto);
    }

    public boolean removeById(Eetakemon eetakemon) {
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        boolean eetakemonConfirmation = _service.removeById(eetakemonDto);

        if (!(eetakemon.getEetakemonAtack().size() <= 0) || !eetakemonConfirmation) {
            return eetakemonConfirmation;
        }

        return removeAtacksToEetakemon(eetakemon);
    }

    public Eetakemon getById(int id) {

        Eetakemon eetakemon = new Eetakemon();
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);

        Hashtable<String, String> condition = new Hashtable<String, String>();
        condition.put("id", Integer.toString(id));

        return modelMapper.map(_service.getByParameters(eetakemonDto, condition), Eetakemon.class);
    }

    public List<Eetakemon> getAll() {

        List<EetakemonDto> responseList = _service.getAll(new EetakemonDto());
        List<Eetakemon> result = new ArrayList<>();
        for (EetakemonDto response : responseList) {
            result.add(modelMapper.map(response, Eetakemon.class));
        }
        return result;
    }

    public boolean addAtacksToEetakemon(Eetakemon eetakemon) {
        boolean actionResult = true;
        for (Atack atack : eetakemon.getEetakemonAtack()) {
            if(_serviceAtack.add(atack))
            {
                AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getId(), atack.getId());
                boolean result = _serviceAtackEetakemon.add(atacksEetakemonDto);
                if (result == false) {
                    actionResult = false;
                }
            }
            else {

                actionResult = false;
            }
        }
        return actionResult;
    }

    public boolean removeAtacksToEetakemon(Eetakemon eetakemon) {
        boolean actionResult = true;
        for (Atack atack : eetakemon.getEetakemonAtack()) {
            Hashtable<String, String> conditions = new Hashtable<>();
            conditions.put("idEetakemon", Integer.toString(eetakemon.getId()));
            conditions.put("idAtack", Integer.toString(atack.getId()));
            AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getId(), atack.getId());
            boolean result = _serviceAtackEetakemon.removeByConditions(atacksEetakemonDto, conditions);
            if (result == false) {
                actionResult = false;
            }
        }
        return actionResult;
    }

    public Eetakemon getCompleteEetakemonById (int id) {
        Eetakemon eetakemonResult = getById(id);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("idEetakemon", Integer.toString(eetakemonResult.getId()));
        List<AtacksEetakemonDto> atacksEetakemonDto = _serviceAtackEetakemon.getAllByParameters(new AtacksEetakemonDto(), conditions);
        List<Atack> atackList = new ArrayList<>();
        for(AtacksEetakemonDto relation : atacksEetakemonDto)
        {
            atackList.add(_serviceAtack.getById(relation.getIdAtack()));
        }
        return eetakemonResult;
    }


    public List<Eetakemon> getAllCompleteEetakemonById (List<EetakemonsUserDto> eetakemonsToUserList)
    {
        List<Eetakemon> eetakemonResult = new ArrayList<>();

        for (EetakemonsUserDto item : eetakemonsToUserList)
        {
            eetakemonResult.add(getCompleteEetakemonById(item.getIdEetakemon()));
        }
        return eetakemonResult;
    }

    private boolean isValidAtackToEetakemon(Eetakemon eetakemon)
    {
        if(eetakemon.getEetakemonAtack().size() == 4)
        {
            return true;
        }
        return false;
    }

}

    //TODO REVISAR

    /*

    public boolean updateEetakemonToUserId(Hashtable<String, Integer> conditions) {
        return true;
    }

    public boolean removeEetakemonToUserById(int i) {
        return true;
    }

    public List<EetakemonsUser> getAllEetakemonsToUser(int id) {
        EetakemonsUser eetakemonsUser = new EetakemonsUser();
        Hashtable<String, String> condition = new Hashtable<String, String>();
        condition.put("iduser", Integer.toString(id));
        return _extra.getByParameter(eetakemonsUser, condition);
    }

    public EetakemonsUser getEetakemonToUserById(int i) {
        return null;
    }

    */

