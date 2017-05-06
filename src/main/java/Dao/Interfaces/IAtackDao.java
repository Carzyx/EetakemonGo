package Dao.Interfaces;

import Controller.IBasicService;
import Model.Atack;
import Model.AtacksEetakemon;

import java.util.List;

/**
 * Created by Ignacio on 29/04/2017.
 */
public interface IAtackDao extends IBasicService<Atack> {

    //Actions for Atacks
    boolean addAtack(Atack atack);

    boolean updateAtack(Atack atack);

    boolean removeAtackById(Atack atack);

    List<Atack> getAll();

    Atack getAtackTById(int i);

    //Actions for Atacks Eetakemon
    boolean addAtackToEetakemon(int i, int j);

    boolean updateAtackToEetakemon(int i, int j);

    boolean removeAtackToEetakemonById(int i);

    List<AtacksEetakemon> getAllAtacksToEetakemon(int i);

    AtacksEetakemon getAtackToEetakekonById(int i);
}
