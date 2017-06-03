package Model;

import com.google.maps.model.LatLng;

/**
 * Created by Ignacio on 03/06/2017.
 */

public class Markers {
    LatLng latLng;
    Eetakemon eetakemon;
    public void Markers(){}

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Eetakemon getEetackemon() {
        return eetakemon;
    }

    public void setEetackemon(Eetakemon eetackemon) {
        this.eetakemon = eetackemon;
    }
}
