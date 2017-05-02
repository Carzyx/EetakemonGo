package Dao;

import Model.Atack;
import Model.AtacksEetakemon;
import Model.EetakemonsUser;
import Model.User;

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

    //Actions for Atacks
    public boolean addAtack(Atack atack) {
        return _service.add(atack);
    }

    public boolean updateAtack(Atack atack) {
        return _service.updateById(atack);
    }

    public boolean removeAtackById(Atack atack) {
        return _service.removeById(atack);
    }

    public List<Atack> getAll() {
        return null;
    }

    public Atack getAtackTById(int id) {
        Atack atack = new Atack();
        atack.setId(id);
        Hashtable<String, String> conditions = new Hashtable<String, String>();
        conditions.put("id", Integer.toString(id));
        return _service.getByParameter(atack, conditions).get(0);
    }

    //Actions for Atacks Eetakemon
    public boolean addAtackToEetakemon(int id_eetakemon, int id_atack) {
        EetakemonsUser eetakemonsUser = new EetakemonsUser();
        AtacksEetakemon atacksEetakemon = new AtacksEetakemon();
        atacksEetakemon.setIdEetakemon(id_eetakemon);
        atacksEetakemon.setIdAtack(id_atack);
        return _extra.add(atacksEetakemon);
    }

    public boolean updateAtackToEetakemon(int i, int j) {
        return true;
    }

    public boolean removeAtackToEetakemonById(int i) {
        return true;
    }

    public List<AtacksEetakemon> getAllAtacksToEetakemon(int id) {
        AtacksEetakemon atacksEetakemon = new AtacksEetakemon();
        Hashtable<String, String> conditions = new Hashtable<String, String>();
        conditions.put("ideetakemon", Integer.toString(id));
        return _extra.getByParameter(atacksEetakemon, conditions);
    }

    public AtacksEetakemon getAtackToEetakekonById(int i) {
        return null;
    }

}
