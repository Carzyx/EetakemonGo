package DAO;

import Model.Eetakemon;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class EetakemonDAO implements IEetakemonDAO{

    private static EetakemonDAO instanceEetakemonDAO = null;

    private EetakemonDAO(){}

    public static EetakemonDAO getInstance() {
        if (instanceEetakemonDAO == null) {
            instanceEetakemonDAO = new EetakemonDAO();
        }
        return instanceEetakemonDAO;
    }

    @Override
    public boolean createEetakemon(Eetakemon eetakemon) {
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
}
