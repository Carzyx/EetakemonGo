package Dao.Entity;

import Model.Atack;
import Model.EetakemonType;

import java.util.List;

/**
 * Created by Miguel Angel on 06/05/2017.
 */
public class EetakemonDto {

    private int id;
    private String name;
    private int level;
    private int ps;
    private EetakemonType type;
    private String image;
    private String description;

    public EetakemonDto() {
    }

    public EetakemonDto(String name, int level, int ps, EetakemonType type, String image, String descrption) {
        this.type = type;
        this.name = name;
        this.level = level;
        this.ps = ps;
        this.image = image;
        this.description = descrption;
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
        return ps * level;
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
