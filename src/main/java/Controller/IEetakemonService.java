package Controller;

import Model.Eetakemon;
import Model.EetakemonAtack;
import Model.EetakemonType;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonService {

    boolean createEetakemon(Eetakemon eetakemon);
    boolean removeEetakemon(int id);
    boolean updateEetakemon(int id, Eetakemon eetakemon);
    Eetakemon getEetakemon(int id);

    List<EetakemonAtack> getRandomAttacks(EetakemonType type);



}
