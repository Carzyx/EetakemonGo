package Dao.Interfaces;


import Model.AtacksEetakemon;
import Model.Eetakemon;
import Model.EetakemonsUser;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonDao extends IBasicDao <Eetakemon> {

    boolean addAtacksToEetakemon(Eetakemon eetakemon);



    /*

    boolean updateEetakemonToUserId(Hashtable<String, Integer> conditions);

    boolean removeEetakemonToUserById(int i);

    List<EetakemonsUser> getAllEetakemonsToUser(int i);

    EetakemonsUser getEetakemonToUserById(int i);
    */

}
