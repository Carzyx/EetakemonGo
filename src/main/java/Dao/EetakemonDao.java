package Dao;

import Model.Eetakemon;
import Model.EetakemonsUser;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonDao implements IEetakemonDao {

    //Acctions for Eetakemon BBDD
    private static IGenericDao<Eetakemon> _service;
    private static IGenericDao<EetakemonsUser> _extra;

    public EetakemonDao(IGenericDao<Eetakemon> service, IGenericDao<EetakemonsUser> extra) {
        _service = service;
        _extra = extra;
    }

    public boolean add(Eetakemon eetakemon) {
        return _service.add(eetakemon);
    }

    @Override
    public boolean updateById(Eetakemon eetakemon) {
        return _service.updateById(eetakemon);
    }

    @Override
    public boolean remove(Eetakemon eetakemon) {
        return _service.removeById(eetakemon);
    }

    public boolean addEetakemon(int id, String name) {
        return true;
    }

    @Override
    public Eetakemon getById(int id) {

        Eetakemon eetakemon = new Eetakemon();
        Hashtable<String, String> condition = new Hashtable<String, String>();
        condition.put("id", Integer.toString(id));
        return _service.getByParameter(eetakemon, condition).get(0);
    }

    @Override
    public List<Eetakemon> getAll() {
        Eetakemon eetakemon = new Eetakemon();
        return _service.getAll(eetakemon);
    }

    //Actions for EetakemonsUser
    public boolean addEetakemonToUser(int id_user, int id_eetakemon) {
        EetakemonsUser eetakemonsUser = new EetakemonsUser();
        eetakemonsUser.setIduser(id_user);
        eetakemonsUser.setIdEetakemon(id_eetakemon);
        _extra.add(eetakemonsUser);
        return true;
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