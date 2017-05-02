package Model;

/**
 * Created by Ignacio on 24/04/2017.
 */
public class AtacksEetakemon {

    private int id;
    private int idEetakemon;
    private int idAtack;

    public AtacksEetakemon() {
    }

    public int getIdEetakemon() {
        return idEetakemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEetakemon(int ideetakemon) {
        this.idEetakemon = ideetakemon;
    }

    public int getIdAtack() {
        return idAtack;
    }

    public void setIdAtack(int idatack) {
        this.idAtack = idatack;
    }
}
