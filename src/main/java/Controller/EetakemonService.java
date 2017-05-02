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

    private static IEetakemonDao _eetakemonDAO;
    private static IAtackDao _atackDao;

    public EetakemonService(IEetakemonDao eetakemonDAO, IAtackDao atackDao) {
        _eetakemonDAO = eetakemonDAO;
        _atackDao = atackDao;
    }

    //Eetakemon
    @Override
    public boolean createEetakemon(Eetakemon eetakemon) {

        return _eetakemonDAO.add(eetakemon);

        //before need to Check null values and input ID?
        //_eetakemonDAO.createEetakemon(eetakemon);
    }

    public List<Eetakemon> getUserEetakemons(int id) {

        List<EetakemonsUser> list = _eetakemonDAO.getAllEetakemonsToUser(id);
        List<Eetakemon> eetakemons = new ArrayList<Eetakemon>();
        for (int i = 0; i < list.size(); i++) {
            Eetakemon eetakemon = new Eetakemon();
            List<Atack> atackList = new ArrayList<Atack>();
            eetakemon = _eetakemonDAO.getById(list.get(i).getIdEetakemon());
            List<AtacksEetakemon> atacksEetakemons = _atackDao
                .getAllAtacksToEetakemon(eetakemon.getId());
            for (int j = 0; j < atacksEetakemons.size(); j++) {
                atackList.add(_atackDao.getAtackTById(atacksEetakemons.get(j).getIdAtack()));
            }
            eetakemon.setEetakemonAtack(atackList);
            eetakemons.add(eetakemon);
        }
        return eetakemons;
    }

    public List<AtacksEetakemon> getAtacks(String name) {
        return null;
    }

    @Override
    public boolean removeEetakemon(int id) {
        return false;
    }

    @Override
    public boolean updateEetakemon(int id, Eetakemon eetakemon) {
        return false;
    }

    @Override
    public Eetakemon getEetakemon(int id) {

        return _eetakemonDAO.getById(id);
    }

    //Eetakemons To User
    public List<AtacksEetakemon> getRandomAtacks(EetakemonType type) {
        return null;
    }

    public boolean addEetakemonToUser(int id_user, Eetakemon eetakemon) {
        //return _eetakemonDAO.addEetakemonToUser(id_user,eetakemon.getId());}
        return _eetakemonDAO.addEetakemonToUser(id_user, 1);
    }//CAMBIAR

    public boolean addAtackToEetakemon(int id_Eetakemon, AtacksEetakemon atacksEetakemon) {
        return _atackDao.addAtackToEetakemon(id_Eetakemon, 1);//CAMBIAR
    }

    //Atakcs
    public boolean addAtack(Atack atack) {
        return _atackDao.addAtack(atack);
    }
}
