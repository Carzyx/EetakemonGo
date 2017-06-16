package DAL.Dao;

import DAL.Dao.Interfaces.IAtackDao;
import DAL.Dao.Interfaces.IGenericDao;
import Model.Atack;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Ignacio on 29/04/2017.
 */
public class AtackDao implements IAtackDao {

    private static IGenericDao<Atack> _service;

    public AtackDao() {

        _service = new GenericDaoImpl<>();
    }

    //TODO -- Validar: si existe Atack, no crearlo (repetir check de UserService/UserDao)
    //TODO -- Error: Porque no añade DamageBase del ataque ????
    public boolean add(Atack atack) {

        return _service.add(atack);
    }

    public boolean updateByName(Atack atack) {
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", atack.getName());
        return _service.updateByConditions(atack, conditions);
    }

    public boolean removeByName(Atack atack) {
        Hashtable<String, String> conditions = new Hashtable<>();
        conditions.put("name", atack.getName());
        return _service.removeByConditions(atack, conditions);
    }

    public Atack getByName(String name) {

        Atack eetakemon = new Atack();
        Hashtable<String, String> condition = new Hashtable<>();
        condition.put("name", name);

        return _service.getByParameters(eetakemon, condition);
    }

    public List<Atack> getAll() {
       return _service.getAll(new Atack());
    }








}
