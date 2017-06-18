package Business.Interfaces;

import Model.Eetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonService extends IBasicService<Eetakemon> {

    boolean addAtacksToEetakemon(Eetakemon eetakemon);
    boolean removeAtacksToEetakemon(Eetakemon eetakemon);
    List<Eetakemon> getAllEetackemonsComplete();
}
