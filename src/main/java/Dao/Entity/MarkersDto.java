package Dao.Entity;

/**
 * Created by Ignacio on 11/06/2017.
 */
public class MarkersDto {
    double lat;
    double lng;
    public MarkersDto(){

    }
    public MarkersDto(double lat,double lng){
        this.lat=lat;
        this.lng=lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
