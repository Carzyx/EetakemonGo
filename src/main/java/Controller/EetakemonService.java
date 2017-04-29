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

    public EetakemonService(IEetakemonDao service,IAtackDao _extra){
        _eetakemonDAO=service;
        _atackDao=_extra;
    }
    //Eetakemon
    @Override
    public boolean createEetakemon(Eetakemon eetakemon) {
    try {
    return _eetakemonDAO.add(eetakemon);
    }
    catch (Exception e){}
        //before need to Check null values and input ID?
        //_eetakemonDAO.createEetakemon(eetakemon);
    return false;
    }
    public  List<Eetakemon> getUserEetakemons(int id) {
        try {
            List<EetakemonsUser> list = _eetakemonDAO.getAllEetakemonsToUser(id);
            List<Eetakemon> eetakemons = new ArrayList<Eetakemon>();
            for (int i=0;i<list.size();i++){
                Eetakemon eetakemon=new Eetakemon();
                List<Atack> atackList=new ArrayList<Atack>();
                eetakemon=_eetakemonDAO.getById(list.get(i).getIdeetakemon());
                List<AtacksEetakemon> atacksEetakemons=_atackDao.getAllAtacksToEetakemon(eetakemon.getId());
                for(int j=0;j<atacksEetakemons.size();j++){
                atackList.add(_atackDao.getAtackTById(atacksEetakemons.get(j).getIdatack()));}
                eetakemon.setEetakemonAtack(atackList);
                eetakemons.add(eetakemon);
            }
            return eetakemons;
        } catch (Exception e) {
            return null;
        }
    }
    public List<AtacksEetakemon> getAtacks(String name){return null;}
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
       try {
           return _eetakemonDAO.getById(id);
       }
       catch (Exception e){e.printStackTrace();
       return null;}
    }
    //Eetakemons To User
    public List<AtacksEetakemon> getRandomAtacks(EetakemonType type) {
        return null;
    }

    public boolean addEetakemonToUser(int id_user,Eetakemon eetakemon){
        //return _eetakemonDAO.addEetakemonToUser(id_user,eetakemon.getId());}
        return _eetakemonDAO.addEetakemonToUser(id_user,1);}//CAMBIAR
    public boolean addAtackToEetakemon(int id_Eetakemon,AtacksEetakemon atacksEetakemon){
        return _atackDao.addAtackToEetakemon(id_Eetakemon,1);//CAMBIAR
    }
    //Atakcs
    public boolean addAtack(Atack atack){return _atackDao.addAtack(atack);}
}
