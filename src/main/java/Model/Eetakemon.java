package Model;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class Eetakemon {
    int id;
    EetakemonType type;
    String name;
    int level;
    int ps;
    EetakemonAtack [] eetakemonAtack;



    public Eetakemon(EetakemonType type, String name, int level, int ps)
    {
        this.type = type;
        this.name = name;
        this.level = level;
        this.ps = ps;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
