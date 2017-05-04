package Controller;

import Model.AtacksEetakemon;
import Model.Eetakemon;
import Model.EetakemonType;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonService extends IBasicService<Eetakemon> {


    boolean addEetakemonToUser(int id_user, Eetakemon eetakemon);

    List<Eetakemon> getUserEetakemons(int id);


    List<AtacksEetakemon> getAtacks(String name);

    List<AtacksEetakemon> getRandomAtacks(EetakemonType type);


}
