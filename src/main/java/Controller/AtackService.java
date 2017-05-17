package Controller;

import Controller.Interfaces.IAtackService;
import Dao.AtackDao;
import Dao.Interfaces.IAtackDao;
import Model.Atack;
import Model.AtacksEetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public class AtackService implements IAtackService {

    private static IAtackDao _serviceAtack;

    public AtackService() {
        _serviceAtack = new AtackDao();
    }

    public boolean create(Atack atack) {
        return _serviceAtack.add(atack);
    }

    public boolean removeByName(Atack atack) {
        return _serviceAtack.removeByName(atack);
    }

    public boolean updateByName(Atack newAtack) {
        return _serviceAtack.updateByName(newAtack);
    }

    public boolean removeByName(int id) {
        return false;
    }

    public boolean updateByName(int id, Atack atack) {
        return false;
    }

    public Atack getByName(String name) {
        return _serviceAtack.getByName(name);
    }

    public List<Atack> getAll() {
        return _serviceAtack.getAll();
    }

}
