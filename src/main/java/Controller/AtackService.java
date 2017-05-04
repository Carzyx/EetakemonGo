package Controller;

import Dao.IAtackDao;
import Model.Atack;

import java.util.List;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public class AtackService implements IAtackService{

    private static IAtackDao _serviceAtack;

    public AtackService(IAtackDao serviceAtackDao) {
        _serviceAtack = serviceAtackDao;
    }

    public boolean create(Atack atack) {
        return _serviceAtack.create(atack);
    }

    public boolean removeById(int id) {
        return false;
    }

    public boolean updateById(int id, Atack atack) {
        return false;
    }

    public Atack getById(int id) {
        return null;
    }

    public List<Atack> getAll() {
        return null;
    }
}
