package Business;

import Business.Interfaces.IEetakemonService;
import DAL.Dao.EetakemonDao;
import DAL.Dao.Interfaces.IEetakemonDao;
import Model.Eetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonService implements IEetakemonService {

    private static IEetakemonDao _serviceEetakemon;

    public EetakemonService() {
        _serviceEetakemon = new EetakemonDao();
    }


    public boolean create(Eetakemon eetakemon) {

        return _serviceEetakemon.add(eetakemon);
    }

    public boolean removeByName(Eetakemon eetakemon) {
        return _serviceEetakemon.removeByName(eetakemon);
    }

    public boolean updateByName(Eetakemon eetakemon) {
        return _serviceEetakemon.updateByName(eetakemon);
    }

    public Eetakemon getByName(String name) {

        return _serviceEetakemon.getByName(name);
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
