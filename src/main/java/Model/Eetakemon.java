package Model;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public class Eetakemon {
    private int id;
    private EetakemonType type;
    private String name;
    private int level;
    private int ps;
    private EetakemonAtack [] eetakemonAtack;
    public Eetakemon(){}
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
    public EetakemonType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getPs() {
        return ps*level;
    }
    public EetakemonAtack[] getEetakemonAtack() {
        return eetakemonAtack;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(EetakemonType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setEetakemonAtack(EetakemonAtack[] eetakemonAtack) {
        this.eetakemonAtack = eetakemonAtack;
    }

    public int getDamage(String atack, Eetakemon eetakemon){
        int typeDamage=1;
        EetakemonAtack eetackemonatack=new EetakemonAtack();
        for (int i=0;i<4;i++){
            if(eetakemonAtack[i].atack==atack)
                eetackemonatack=eetakemonAtack[i];
        }
        return eetackemonatack.damageBase*typeDamage*level;
    }

}
