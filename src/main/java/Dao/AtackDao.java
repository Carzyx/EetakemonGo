package Dao;

import Dao.Interfaces.IAtackDao;
import Dao.Interfaces.IGenericDao;
import Model.Atack;
import Model.AtacksEetakemon;
import Model.EetakemonsUser;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Ignacio on 29/04/2017.
 */
public class AtackDao implements IAtackDao {

    private static IGenericDao<Atack> _service;
    private static IGenericDao<AtacksEetakemon> _extra;

    public AtackDao(IGenericDao<Atack> service, IGenericDao<AtacksEetakemon> extra) {
        _service = service;
        _extra = extra;
    }

    public boolean add(Atack atack) {
        return _service.add(atack);
    }

    public boolean updateById(Atack atack) {
        return _service.updateById(atack);
    }

    public boolean removeById(Atack atack) {
        return _service.removeById(atack);
    }

    public Atack getById(int id) {

        Atack eetakemon = new Atack();
        Hashtable<String, String> condition = new Hashtable<String, String>();
        condition.put("id", Integer.toString(id));

        return _service.getByParameters(eetakemon, condition);
    }

    public List<Atack> getAll() {
       return _service.getAllByParameters(new Atack(), null);
    }








}
