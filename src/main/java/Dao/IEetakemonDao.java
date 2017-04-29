package Dao;


import Model.AtacksEetakemon;
import Model.Eetakemon;
import Model.EetakemonsUser;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonDao{
    //Acctions for Eetakemon BBDD
    boolean add(Eetakemon eetakemon) throws IllegalAccessException;
    boolean updateById(Eetakemon eetakemon) throws IllegalAccessException;
    boolean remove(Eetakemon eetakemon) throws IllegalAccessException;
    Eetakemon getById(int id) throws IllegalAccessException;
    List<Eetakemon> getAll() throws Exception;
   //Actions for EetakemonsUser
    boolean addEetakemonToUser(int i,int j);
    boolean updateEetakemonToUserId(Hashtable<String,Integer> conditions);
    boolean removeEetakemonToUserById(int i);
    List<EetakemonsUser> getAllEetakemonsToUser(int i) throws Exception;
    EetakemonsUser getEetakemonToUserById(int i);

}
