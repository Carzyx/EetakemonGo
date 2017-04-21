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

    public String getName() {
        return name;
    }
    public int getDamage(String atack,Eetakemon eetakemon){
        int typeDamage=1;
        EetakemonAtack eetackemonatack=new EetakemonAtack();
        for (int i=0;i<4;i++){
            if(eetakemonAtack[i].atack==atack)
                eetackemonatack=eetakemonAtack[i];
        }
        return eetackemonatack.damageBase*typeDamage*level;
    }

    public int getPs() {
        return ps*level;
    }

    public int getLevel() {
        return level;
    }
}
