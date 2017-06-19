package DAL.Dao;

import DAL.EntityDataBase.AtacksEetakemonDto;
import DAL.EntityDataBase.EetakemonDto;
import DAL.EntityDataBase.EetakemonsUserDto;
import DAL.Dao.Interfaces.IAtackDao;
import DAL.Dao.Interfaces.IEetakemonDao;
import DAL.Dao.Interfaces.IGenericDao;
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
    private static IGenericDao<EetakemonsUserDto>_serviceEetackemonUser;
    private static IGenericDao<AtacksEetakemonDto> _serviceAtackEetakemon;
    private static IAtackDao _serviceAtack;
    private ModelMapper modelMapper = new ModelMapper();

    public EetakemonDao() {
        _service = new GenericDaoImpl<>();
        _serviceEetackemonUser=new GenericDaoImpl<>();
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

        if (!(eetakemon.getAtacks().size() <= 0) || !eetakemonConfirmation) {
            return eetakemonConfirmation;
        }

        return removeAtacksToEetakemon(eetakemon);
    }

    public Eetakemon getByName(String name) {

        Eetakemon eetakemon = new Eetakemon();
        EetakemonDto eetakemonDto = modelMapper.map(eetakemon, EetakemonDto.class);
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", name);
        modelMapper.map(_service.getByParameters(eetakemonDto, conditions), eetakemon);
        return eetakemon;
    }

    public List<Eetakemon> getAll() {

        List<EetakemonDto> responseList = _service.getAll(new EetakemonDto());
        List<Eetakemon> result = new ArrayList<>();
        for (EetakemonDto response : responseList) {
            result.add(modelMapper.map(response, Eetakemon.class));
        }
        return result;
    }
    public List<Eetakemon> getAllEetackemonsComplete(){
        List<EetakemonDto> responseList = _service.getAll(new EetakemonDto());
        List<Eetakemon> result = new ArrayList<>();
        for (EetakemonDto response : responseList) {
            result.add(getCompleteEetakemonByName(response.getName()));
        }
        return result;
    }

    public boolean addAtacksToEetakemon(Eetakemon eetakemon) {
        boolean actionResult = true;
        List<Atack>atacks=_serviceAtack.getAll();
        for (Atack atack : eetakemon.getAtacks()) {
                for (int i=0;i<atacks.size();i++){
                    if (atack.getName().equals(atacks.get(i)))
                    {
                        AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getName(), atack.getName());
                        boolean atackAdded = _serviceAtackEetakemon.add(atacksEetakemonDto);
                        if (!atackAdded) {
                            actionResult = false;
                        }
                    }
                    else {
                        actionResult = false;
                }
            }
        }
        return actionResult;
    }

    public boolean removeAtacksToEetakemon(Eetakemon eetakemon) {
        boolean actionResult = true;
        for (Atack atack : eetakemon.getAtacks()) {
            Hashtable<String, String> conditions = new Hashtable<>();
            conditions.put("eetakemonName", eetakemon.getName());
            conditions.put("atackName", atack.getName());
            AtacksEetakemonDto atacksEetakemonDto = new AtacksEetakemonDto(eetakemon.getName(), atack.getName());
            boolean atackRemoved = _serviceAtackEetakemon.removeByConditions(atacksEetakemonDto, conditions);
            if (!atackRemoved) {
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
        eetakemonResult.setAtacks(atackList);
        return eetakemonResult;
    }


    public List<Eetakemon> getAllCompleteEetakemonByName (List<EetakemonsUserDto> eetakemonsToUserList)
    {
        List<Eetakemon> eetakemonResult = new ArrayList<>();

        for (EetakemonsUserDto item : eetakemonsToUserList)
        {
            Eetakemon eetakemon=new Eetakemon();
                eetakemon=getCompleteEetakemonByName(item.getEetakemonName());
            Hashtable<String, String> conditions = new Hashtable<>();
            conditions.put("eetakemonName", item.getEetakemonName());
            EetakemonsUserDto eetakemonsUser=_serviceEetackemonUser.getByParameters(new EetakemonsUserDto(),conditions);
            modelMapper.map(eetakemonsUser,eetakemon);
            eetakemonResult.add(eetakemon);
        }
        return eetakemonResult;
    }

    private boolean isValidAtackToEetakemon(Eetakemon eetakemon)
    {
        return eetakemon.getAtacks().size() == 4;
    }

}


