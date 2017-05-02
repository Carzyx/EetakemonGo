package Model;

/**
 * Created by Ignacio on 24/04/2017.
 */
public class AtacksEetakemon {
    private int id;
    private int ideetakemon;
    private int idatack;
    public AtacksEetakemon(){}
    public int getIdeetakemon() {
        return ideetakemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdeetakemon(int ideetakemon) {
        this.ideetakemon = ideetakemon;
    }

    public int getIdatack() {
        return idatack;
    }

    public void setIdatack(int idatack) {
        this.idatack = idatack;
    }
}
