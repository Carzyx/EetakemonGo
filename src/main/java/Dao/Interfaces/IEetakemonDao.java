package Dao.Interfaces;


import Model.AtacksEetakemon;
import Model.Eetakemon;
import Model.EetakemonsUser;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonDao {

    //Acctions for Eetakemon BBDD
    boolean add(Eetakemon eetakemon);

    boolean updateById(Eetakemon eetakemon);

    boolean remove(Eetakemon eetakemon);

    Eetakemon getById(int id);

    List<Eetakemon> getAll();

    //Actions for EetakemonsUser
    boolean addEetakemonToUser(int i, int j);

    boolean updateEetakemonToUserId(Hashtable<String, Integer> conditions);

    boolean removeEetakemonToUserById(int i);

    List<EetakemonsUser> getAllEetakemonsToUser(int i);

    EetakemonsUser getEetakemonToUserById(int i);

}
