package Dao;

import Dao.Entity.AtacksEetakemonDto;
import Dao.Entity.EetakemonDto;
import Dao.Entity.UserDto;
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
    private ModelMapper modelMapper = new ModelMapper();

    public EetakemonDao(IGenericDao<Eetakemon> service, IGenericDao<EetakemonsUser> extra) {
        _service = service;
        _extra = extra;
    }

    public boolean add(Eetakemon eetakemon) {
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        boolean eetakemonConfirmation = _service.add(eetakemonDto);

        if(!(eetakemon.getEetakemonAtack().size() <= 0) || !eetakemonConfirmation)
        {
            return eetakemonConfirmation;
        }

       return addAtacksToEetakemon(eetakemon);
    }

    public boolean updateById(Eetakemon eetakemon) {
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        return _service.updateById(eetakemonDto);
    }

    public boolean removeById(Eetakemon eetakemon) {
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        return _service.removeById(eetakemonDto);
    }

    public Eetakemon getById(int id) {

        Eetakemon eetakemon = new Eetakemon();
        Hashtable<String, String> condition = new Hashtable<String, String>();
        condition.put("id", Integer.toString(id));
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);

        return modelMapper.map(_service.getByParameters(eetakemonDto, condition), Eetakemon.class);
    }

    public List<Eetakemon> getAll() {

        List<EetakemonDto> responseList = _service.getAllByParameters(new EetakemonDto(), null);
        List<Eetakemon> result = new ArrayList<>();
        for(EetakemonDto response : responseList)
        {
            result.add(modelMapper.map(response, Eetakemon.class));
        }
        return result;
    }




    public boolean addAtacksToEetakemon(Eetakemon eetakemon) {
        boolean actionResult = true;
        for (Atack atack : eetakemon.getEetakemonAtack())
        {
            AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getId(), atack.getId());
            boolean result = _serviceAtackEetakemon.add(atacksEetakemonDto);
            if(result == false)
            {
                actionResult = false;
            }
        }
        return actionResult;
    }



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

}