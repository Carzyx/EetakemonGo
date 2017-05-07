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

    @Override
    public boolean removeById(Atack atack) {
        return _serviceAtack.removeById(atack);
    }

    @Override
    public boolean updateById(Atack newAtack) {
        return _serviceAtack.updateById(newAtack);
    }

    public boolean removeById(int id) {
        return false;
    }

    public boolean updateById(int id, Atack atack) {
        return false;
    }

    public Atack getById(int id) {
        return _serviceAtack.getById(id);
    }

    public List<Atack> getAll() {
        return _serviceAtack.getAll();
    }

}
