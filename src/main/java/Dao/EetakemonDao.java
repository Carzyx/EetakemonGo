package Dao;

import Model.Eetakemon;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonDao implements IEetakemonDao {

    private static IGenericDao<Eetakemon> _service;

    public EetakemonDao(IGenericDao<Eetakemon> service)
    {
        _service = service;
    }

    public boolean add(Eetakemon eetakemon){
        return _service.add(eetakemon);
    }

    @Override
    public boolean updateById(Eetakemon eetakemon){
        return _service.updateById(eetakemon);
    }

    @Override
    public boolean removeById(Eetakemon eetakemon){
        return _service.removeById(eetakemon);
    }

    @Override
    public Eetakemon getById(int id) {
        Eetakemon eetakemon=new Eetakemon();
        Hashtable<String,String>condition=new Hashtable<String, String>();
        condition.put("id",Integer.toString(id));
        return _service.getByParameter(eetakemon,condition);
    }

    @Override
    public List<Eetakemon> getAll() throws Exception {
        Eetakemon eetakemon=new Eetakemon();
        return _service.getAll(eetakemon);
    }


}
