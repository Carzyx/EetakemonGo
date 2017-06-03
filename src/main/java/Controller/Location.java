package Controller;

import Controller.Interfaces.ILocation;
import Model.Eetakemon;
import Model.EetakemonType;
import Model.Markers;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignacio on 03/06/2017.
 */
public class Location implements ILocation {
    @Override
    public List<Markers> getMarkers(LatLng latLng) {
        List<Markers>markers=new ArrayList<Markers>();
        Markers marker=new Markers();
        marker.setLat(41.358975);
        marker.setLng( 2.137715);
        Eetakemon eetakemon =new Eetakemon("Charizard",1,10, EetakemonType.FUEGO,"pokemons/charizard.png","El de toda la vida pa que vamos a explicar mas");
        marker.setEetakemon(eetakemon);
        markers.add(marker);
        return markers;
    }
}
