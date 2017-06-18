package DAL.Dao.Interfaces;


import DAL.EntityDataBase.EetakemonsUserDto;
import Model.Eetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonDao extends IBasicDao<Eetakemon> {

    boolean addAtacksToEetakemon(Eetakemon eetakemon);

    boolean removeAtacksToEetakemon(Eetakemon eetakemon);

    Eetakemon getCompleteEetakemonByName(String name);

    List<Eetakemon> getAllCompleteEetakemonByName(List<EetakemonsUserDto> eetakemonsToUserList);


}
