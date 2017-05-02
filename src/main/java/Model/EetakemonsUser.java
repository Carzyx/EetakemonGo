package Model;

/**
 * Created by Ignacio on 24/04/2017.
 */
public class EetakemonsUser {

    private int id;
    private int iduser;
    private int ideetakemon;

    public EetakemonsUser() {
    }

    public int getIduser() {
        return iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdEetakemon() {
        return ideetakemon;
    }

    public void setIdEetakemon(int ideetakemon) {
        this.ideetakemon = ideetakemon;
    }
}
