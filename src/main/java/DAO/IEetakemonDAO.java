package DAO;


import Model.Eetakemon;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonDAO {

    boolean createEetakemon(Eetakemon eetakemon);
    boolean removeEetakemon(int id);
    boolean updateEetakemon(int id, Eetakemon eetakemon);

    Eetakemon getEetakemon(int id);
}
