package DAL.EntityDataBase;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public class AtacksEetakemonDto {

    private String atackName;
    private String eetakemonName;

    public AtacksEetakemonDto() {
    }

    public AtacksEetakemonDto(String eetakemonName, String atackName) {
        this.eetakemonName = eetakemonName;
        this.atackName = atackName;
    }

    public String getAtackName() {
        return atackName;
    }

    public void setAtackName(String atackName) {
        this.atackName = atackName;
    }

    public String getEetakemonName() {
        return eetakemonName;
    }

    public void setEetakemonName(String eetakemonName) {
        this.eetakemonName = eetakemonName;
    }
}
