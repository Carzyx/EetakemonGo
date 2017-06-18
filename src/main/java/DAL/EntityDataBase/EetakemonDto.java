package DAL.EntityDataBase;

import Model.EetakemonType;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public class EetakemonDto {

    private String name;
    private int ps;
    private EetakemonType type;
    private String image;
    private String description;

    public EetakemonDto() {
    }

    public EetakemonDto(String name, int ps, EetakemonType type, String image, String descrption) {
        this.type = type;
        this.name = name;
        this.ps = ps;
        this.image = image;
        this.description = descrption;
    }

    public EetakemonType getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public int getPs() {
        return ps ;
    }

    public void setType(EetakemonType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
