package Dao;

import Model.Eetakemon;

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
    public boolean removeById(Eetakemon eetakemon) {
        return _service.removeById(eetakemon);
    }

    @Override
    public Eetakemon getById(Eetakemon eetakemon) {
        //return _service.getByParameter(eetakemon);
        return null;
    }

    @Override
    public List<Eetakemon> getAll() {
        return _service.getAll(Eetakemon.class);
    }


}
