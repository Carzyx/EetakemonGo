package Dao.Interfaces;


import Dao.Entity.EetakemonsUserDto;
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
    boolean removeAtacksToEetakemon(Eetakemon eetakemon);
    Eetakemon getCompleteEetakemonByName (String name);
    List<Eetakemon> getAllCompleteEetakemonByName (List<EetakemonsUserDto> eetakemonsToUserList);


}
