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

    public boolean updateByName(Eetakemon eetakemon) {
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", eetakemon.getName());
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);

        return _service.updateByConditions(eetakemonDto, conditions);
    }

    public boolean removeByName(Eetakemon eetakemon) {
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", eetakemon.getName());
        boolean eetakemonConfirmation = _service.removeByConditions(eetakemonDto, conditions);

        if (!(eetakemon.getEetakemonAtack().size() <= 0) || !eetakemonConfirmation) {
            return eetakemonConfirmation;
        }

        return removeAtacksToEetakemon(eetakemon);
    }

    public Eetakemon getByName(String name) {

        Eetakemon eetakemon = new Eetakemon();
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", name);

        return modelMapper.map(_service.getByParameters(eetakemonDto, conditions), Eetakemon.class);
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
                AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getName(), atack.getName());
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
            conditions.put("eetakemonName", eetakemon.getName());
            conditions.put("atackName", atack.getName());
            AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getName(), atack.getName());
            boolean result = _serviceAtackEetakemon.removeByConditions(atacksEetakemonDto, conditions);
            if (result == false) {
                actionResult = false;
            }
        }
        return actionResult;
    }

    public Eetakemon getCompleteEetakemonByName (String name) {
        Eetakemon eetakemonResult = getByName(name);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("eetakemonName", eetakemonResult.getName());
        List<AtacksEetakemonDto> atacksEetakemonDto = _serviceAtackEetakemon.getAllByParameters(new AtacksEetakemonDto(), conditions);
        List<Atack> atackList = new ArrayList<>();
        for(AtacksEetakemonDto relation : atacksEetakemonDto)
        {
            atackList.add(_serviceAtack.getByName(relation.getAtackName()));
        }
        return eetakemonResult;
    }


    public List<Eetakemon> getAllCompleteEetakemonByName (List<EetakemonsUserDto> eetakemonsToUserList)
    {
        List<Eetakemon> eetakemonResult = new ArrayList<>();

        for (EetakemonsUserDto item : eetakemonsToUserList)
        {
            eetakemonResult.add(getCompleteEetakemonByName(item.getEetakemonName()));
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


