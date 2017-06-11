package Model;

import com.google.maps.model.LatLng;

/**
 * Created by Ignacio on 03/06/2017.
 */

public class Markers {
    double lat;
    double lng;
    Eetakemon eetakemon;
    public void Markers(){}

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

    public Eetakemon getEetakemon() {
        return eetakemon;
    }

    public void setEetakemon(Eetakemon eetakemon) {
        this.eetakemon = eetakemon;
    }
}
