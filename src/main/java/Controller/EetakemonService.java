package Controller;



import DAO.EetakemonDAO;
import Model.Eetakemon;
import Model.EetakemonAtack;
import Model.EetakemonType;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonService implements IEetakemonService {

    private static EetakemonDAO _eetakemonDAO = EetakemonDAO.getInstance();

    @Override
    public boolean createEetakemon(Eetakemon eetakemon) {

        //before need to Check null values and input ID?
        _eetakemonDAO.createEetakemon(eetakemon);

        return false;
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
        return null;
    }

    @Override
    public List<EetakemonAtack> getRandomAttacks(EetakemonType type) {
        return null;
    }

    private int generateID()
    {
        return 0;

    }
}
