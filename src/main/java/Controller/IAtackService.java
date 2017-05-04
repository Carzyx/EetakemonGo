package Controller;

import Model.Atack;
import Model.AtacksEetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 03/05/2017.
 */
public interface IAtackService extends IBasicService <Atack>{

    //Actions for Atacks Eetakemon

    boolean addAtackToEetakemon(int i, int j);

    boolean updateAtackToEetakemon(int i, int j);

    boolean removeAtackToEetakemonById(int i);

    List<AtacksEetakemon> getAllAtacksToEetakemon(int i);

    AtacksEetakemon getAtackToEetakekonById(int i);

}
