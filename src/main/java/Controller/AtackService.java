package Controller;

import Controller.Interfaces.IAtackService;
import Dao.Interfaces.IAtackDao;
import Model.Atack;
import Model.AtacksEetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public class AtackService implements IAtackService {

    private static IAtackDao _serviceAtack;

    public AtackService(IAtackDao serviceAtackDao) {
        _serviceAtack = serviceAtackDao;
    }

    public boolean create(Atack atack) {
        return _serviceAtack.add(atack);
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

    @Override
    public boolean addAtackToEetakemon(int i, int j) {
        return false;
    }

    @Override
    public boolean updateAtackToEetakemon(int i, int j) {
        return false;
    }

    @Override
    public boolean removeAtackToEetakemonById(int i) {
        return false;
    }

    @Override
    public List<AtacksEetakemon> getAllAtacksToEetakemon(int i) {
        return null;
    }

    @Override
    public AtacksEetakemon getAtackToEetakekonById(int i) {
        return null;
    }
}
