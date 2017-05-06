package Dao.Entity;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public class EetakemonsUserDto {

    private int idUser;
    private int idEetakemon;

    public EetakemonsUserDto(int idUser, int idEetakemon)
    {
        this.idUser = idUser;
        this.idEetakemon = idEetakemon;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEetakemon() {
        return idEetakemon;
    }

    public void setIdEetakemon(int idEetakemon) {
        this.idEetakemon = idEetakemon;
    }
}
