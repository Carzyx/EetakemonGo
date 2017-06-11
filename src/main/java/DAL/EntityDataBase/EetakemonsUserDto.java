package DAL.EntityDataBase;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public class EetakemonsUserDto {

    private String username;
    private String eetakemonName;

    public EetakemonsUserDto(){}
    public EetakemonsUserDto(String username, String eetakemonName) {
        this.username = username;
        this.eetakemonName = eetakemonName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEetakemonName() {
        return eetakemonName;
    }

    public void setEetakemonName(String eetakemonName) {
        this.eetakemonName = eetakemonName;
    }
}

