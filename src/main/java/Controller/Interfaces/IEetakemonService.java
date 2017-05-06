package Controller.Interfaces;

import Model.AtacksEetakemon;
import Model.Eetakemon;
import Model.EetakemonType;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonService extends IBasicService<Eetakemon> {

    boolean addAtacksToEetakemon(Eetakemon eetakemon);
    boolean removeAtacksToEetakemon(Eetakemon eetakemon);
}
