package Controller;

import Dao.IAtackDao;
import Model.*;
import Dao.IEetakemonDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonService implements IEetakemonService {

    private static IEetakemonDao _serviceEetakemon;
    private static IAtackService _serviceAtack;

    public EetakemonService(IEetakemonDao serviceEetakemonDao, IAtackService serviceAtack) {
        _serviceEetakemon = serviceEetakemonDao;
        _serviceAtack = serviceAtack;
    }

    //Eetakemon
    public boolean create(Eetakemon eetakemon) {

        return _serviceEetakemon.add(eetakemon);

        //before need to Check null values and input ID?
        //_eetakemonDAO.createEetakemon(eetakemon);
    }

    public boolean removeById(int id) {
        return false;
    }

    public boolean updateById(int id, Eetakemon eetakemon) {
        return false;
    }

    public Eetakemon getById(int id) {

        return _serviceEetakemon.getById(id);
    }



    public List<Eetakemon> getUserEetakemons(int id) {

        List<EetakemonsUser> eetakemonsBaseUser = _serviceEetakemon.getAllEetakemonsToUser(id);
        List<Eetakemon> eetakemons = new ArrayList<Eetakemon>();

        for (int i = 0; i < eetakemonsBaseUser.size(); i++) {

            Eetakemon completeEetakemon;
            List<Atack> atacksCompleteEeetakemon = new ArrayList<Atack>();
            completeEetakemon = _serviceEetakemon.getById(eetakemonsBaseUser.get(i).getIdEetakemon());
            List<AtacksEetakemon> atacksEetakemon = _serviceAtack.getAllAtacksToEetakemon(completeEetakemon.getId());

            for (int j = 0; j < atacksEetakemon.size(); j++) {

                atacksCompleteEeetakemon.add(_serviceAtack.getAtackTById(atacksEetakemon.get(j).getIdAtack()));
            }
            completeEetakemon.setEetakemonAtack(atacksCompleteEeetakemon);
            eetakemons.add(completeEetakemon);
        }
        return eetakemons;
    }

    public List<AtacksEetakemon> getAtacks(String name) {
        return null;
    }




    //Eetakemons To User
    public List<AtacksEetakemon> getRandomAtacks(EetakemonType type) {
        return null;
    }

    public boolean addEetakemonToUser(int id_user, Eetakemon eetakemon) {
        //return _eetakemonDAO.addEetakemonToUser(id_user,eetakemon.getId());}
        return _serviceEetakemon.addEetakemonToUser(id_user, 1);
    }//CAMBIAR

    public boolean addAtackToEetakemon(int id_Eetakemon, AtacksEetakemon atacksEetakemon) {
        return _serviceAtack.addAtackToEetakemon(id_Eetakemon, 1);//CAMBIAR
    }

    //Atakcs
    public boolean addAtack(Atack atack) {
        return _serviceAtack.addAtack(atack);
    }
}
