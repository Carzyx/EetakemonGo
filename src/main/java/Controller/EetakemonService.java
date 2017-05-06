package Controller;

import Dao.Interfaces.IEetakemonDao;
import Model.Eetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonService  {

    private static IEetakemonDao _serviceEetakemon;

    public EetakemonService(IEetakemonDao serviceEetakemonDao) {
        _serviceEetakemon = serviceEetakemonDao;
    }


    public boolean create(Eetakemon eetakemon) {

        return _serviceEetakemon.add(eetakemon);
    }

    public boolean removeById(Eetakemon eetakemon) {
        return _serviceEetakemon.removeById(eetakemon);
    }

    public boolean updateById(Eetakemon eetakemon) {
        return _serviceEetakemon.updateById(eetakemon);
    }

    public Eetakemon getById(int id) {

        return _serviceEetakemon.getById(id);
    }

    public List<Eetakemon> getAll() {
        return _serviceEetakemon.getAll();
    }

    public boolean addAtacksToEetakemon(Eetakemon eetakemon) {
        return _serviceEetakemon.addAtacksToEetakemon(eetakemon);
    }

    public boolean removeAtacksToEetakemon(Eetakemon eetakemon) {
        return _serviceEetakemon.removeAtacksToEetakemon(eetakemon);
    }







}
