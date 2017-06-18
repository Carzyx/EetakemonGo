package DAL.Dao.Interfaces;


import DAL.EntityDataBase.PartyDto;
import Model.Party;

import java.util.List;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public interface IPartyDao {
    boolean add(Party item);
    List<PartyDto> getAll();
    List<PartyDto> getAllByConditions(String name);
}
