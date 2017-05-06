package Dao.Entity;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public class AtacksEetakemonDto {

    private int idEetakemon;
    private int idAtack;

    public AtacksEetakemonDto(int idEetakemon, int idAtack)
    {
        this.idEetakemon = idEetakemon;
        this.idAtack = idAtack;
    }

    public int getIdEetakemon() {
        return idEetakemon;
    }

    public void setIdEetakemon(int idEetakemon) {
        this.idEetakemon = idEetakemon;
    }

    public int getIdAtack() {
        return idAtack;
    }

    public void setIdAtack(int idAtack) {
        this.idAtack = idAtack;
    }
}
